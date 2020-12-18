package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.jpa.offer.Address;
import com.example.plot.jpa.offer.Surrounding;
import com.example.plot.jpa.User;
import com.example.plot.jpa.UserOffers;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;
import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.jpa.offer.address.Street;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Service
@Transactional
public class DatabaseService {

    @PersistenceContext
    EntityManager entityManager;

    public List<PlotType> getPlotTypes() {
        return entityManager.createQuery("select pt from PlotType pt order by pt.id", PlotType.class).getResultList();
    }

    public List<Surrounding> getSurroundings() {
        return entityManager.createQuery("select s from Surrounding s order by s.id", Surrounding.class).getResultList();
    }

    public List<DriveType> getDriveTypes() {
        return entityManager.createQuery("select dt from DriveType dt order by dt.id", DriveType.class).getResultList();
    }

    public List<Address> getAddresses() { 
        return entityManager.createQuery("select a from Address a order by a.id", Address.class).getResultList();
    }
    public Address getAddressFromDatabase(Address address) {
        try {
            return entityManager.createQuery("select a from Address a where a.street.name = :sn and a.city.name = :cin and a.country.name = :con", Address.class)
                    .setParameter("sn", address.getStreet().getName())
                    .setParameter("cin", address.getCity().getName())
                    .setParameter("con", address.getCountry().getName())
                    .getSingleResult();
        } catch (NoResultException e ) {
            return null;
        }
    }
    public Address addAddress(Address address) {
        entityManager.persist(address);

        return address;
    }

    public List<Street> getStreets() {
        return entityManager.createQuery("select s from Street s order by s.id", Street.class).getResultList();
    }
    public Street getStreetFromDatabase(Street street) {
        try {
            return entityManager.createQuery("select s from Street s where s.name = :sn", Street.class)
                    .setParameter("sn", street.getName())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    public void addStreet(Street street) {
        entityManager.persist(street);
    }

    public List<Country> getCountries() {
        return entityManager.createQuery("select co from Country co order by co.id", Country.class).getResultList();
    }
    public Country getCountryFromDatabase(Country country) {
        try {
            return entityManager.createQuery("select c from Country c where c.name = :cn", Country.class)
                    .setParameter("cn", country.getName()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void addCountry(Country country) {
        entityManager.persist(country);
    }

    public List<City> getCities() {
        return entityManager.createQuery("select ci from City ci order by ci.id", City.class).getResultList();
    }
    public City getCityFromDatabase(City city) {
        try {
            return entityManager.createQuery("select c from City c where c.name = :cn", City.class)
                    .setParameter("cn", city.getName()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void addCity(City city) {
        entityManager.persist(city);
    }

    public List<Offer> getOffers() {
        return entityManager.createQuery("select o from City o order by o.id", Offer.class).getResultList();
    }

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u order by u.id", User.class).getResultList();
    }

    public Address addAddressIfNotExist(Address address) {
//        if such address already exists return address form database
        if ( getAddressFromDatabase(address) != null ){
            return getAddressFromDatabase(address);
        }
//        if there is no such Country add to database
        if ( getCountryFromDatabase(address.getCountry()) == null ) {
            addCountry(address.getCountry());
        }
        address.setCountry(getCountryFromDatabase(address.getCountry()));
//        if there is no such city add to database
        if ( getCityFromDatabase(address.getCity()) == null ) {
            addCity(address.getCity());
        }
        address.setCity(getCityFromDatabase(address.getCity()));
//        if there is no such street add to database
        if ( getStreetFromDatabase(address.getStreet()) == null ) {
            addStreet(address.getStreet());
        }
        address.setStreet(getStreetFromDatabase(address.getStreet()));

        return address;
    }

    public UserOffers getUserOffersById(Integer id) {
        return entityManager.find(UserOffers.class, id);
    }
    public void removeUserOffer(Integer user_id, Integer offer_id){
        entityManager.createQuery("delete from UserOffers uo where uo.user.id = :ui and uo.offer.id = :oi")
                .setParameter("ui", user_id).setParameter("oi", offer_id).executeUpdate();
    }

    public String getUserEmail(Integer id) {
        return entityManager.createQuery("select u.email from User u where u.id = (select uo.user.id from UserOffers uo where uo.offer.id = :id)")
                .setParameter("id", id).getSingleResult().toString();
    }

}
