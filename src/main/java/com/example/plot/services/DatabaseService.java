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
        String jpql = "select pt from PlotType pt order by pt.id";

        return entityManager.createQuery(jpql, PlotType.class).getResultList();
    }

    public List<Surrounding> getSurroundings() {
        String jpql = "select s from Surrounding s order by s.id";

        return entityManager.createQuery(jpql, Surrounding.class).getResultList();
    }

    public List<DriveType> getDriveTypes() {
        String jpql = "select dt from DriveType dt order by dt.id";

        return entityManager.createQuery(jpql, DriveType.class).getResultList();
    }

    public List<Address> getAddresses() {
        String jpql = "select a from Address a order by a.id";

        return entityManager.createQuery(jpql, Address.class).getResultList();
    }
    public Address getAddressFromDatabase(Address address) {
        try {
            String jpql = "select a from Address a where a.street.name = :sn and a.city.name = :cin and a.country.name = :con";

            TypedQuery<Address> query = entityManager.createQuery(jpql, Address.class);

            return query.setParameter("sn", address.getStreet().getName())
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
        String jpql = "select s from Street s order by s.id";

        return entityManager.createQuery(jpql, Street.class).getResultList();
    }
    public Street getStreetFromDatabase(Street street) {
        try {
            String jpql = "select s from Street s where s.name = :sn";

            TypedQuery<Street> query = entityManager.createQuery(jpql, Street.class);

            return query.setParameter("sn", street.getName()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
    public void addStreet(Street street) {
        entityManager.persist(street);

    }

    public List<Country> getCountries() {
        String jpql = "select co from Country co order by co.id";

        return entityManager.createQuery(jpql, Country.class).getResultList();
    }
    public Country getCountryFromDatabase(Country country) {
        try {
            String jpql = "select c from Country c where c.name = :cn";

            TypedQuery<Country> query = entityManager.createQuery(jpql, Country.class);

            return query.setParameter("cn", country.getName()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void addCountry(Country country) {
        entityManager.persist(country);

    }

    public List<City> getCities() {
        String jpql = "select ci from City ci order by ci.id";

        return entityManager.createQuery(jpql, City.class).getResultList();
    }
    public City getCityFromDatabase(City city) {
        try {
            String jpql = "select c from City c where c.name = :cn";

            TypedQuery<City> query = entityManager.createQuery(jpql, City.class);

            return query.setParameter("cn", city.getName()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public void addCity(City city) {
        entityManager.persist(city);

    }

    public List<Offer> getOffers() {
        String jpql = "select o from City o order by o.id";

        return entityManager.createQuery(jpql, Offer.class).getResultList();
    }

    public List<User> getUsers() {
        String jpql = "select u from User u order by u.id";

        return entityManager.createQuery(jpql, User.class).getResultList();
    }

    public Address addAddressIfNotExist(Address address) {
        System.out.println(address.getId() + address.getStreet().getName() + address.getCity().getName() + address.getCountry().getName());
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
    public UserOffers getUserOffer(Integer user_id, Integer offer_id) {
        String jpql = "select uo from UserOffers uo where uo.user.id = :ui and uo.offer.id = :oi";

        TypedQuery<UserOffers>query = entityManager.createQuery(jpql, UserOffers.class);

        return query.setParameter("ui", user_id).setParameter("oi", offer_id).getSingleResult();
    }
    public void removeUserOffer(Integer user_id, Integer offer_id){
        entityManager.createQuery("delete from UserOffers uo where uo.user.id = :ui and uo.offer.id = :oi").
                setParameter("ui", user_id).setParameter("oi", offer_id).executeUpdate();
    }

}
