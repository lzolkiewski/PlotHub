package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.jpa.offer.Surrounding;
import com.example.plot.jpa.User;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;
import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.jpa.offer.address.Street;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<Street> getStreets(){
        String jpql = "select s from Street s order by s.id";

        return entityManager.createQuery(jpql, Street.class).getResultList();
    }

    public List<Country> getCountries() {
        String jpql = "select co from Country co order by co.id";

        return entityManager.createQuery(jpql, Country.class).getResultList();
    }

    public List<City> getCities() {
        String jpql = "select ci from City ci order by ci.id";

        return entityManager.createQuery(jpql, City.class).getResultList();
    }

    public List<Offer> getOffers() {
        String jpql = "select o from City o order by o.id";

        return entityManager.createQuery(jpql, Offer.class).getResultList();
    }

    public List<User> getUsers() {
        String jpql = "select u from User u order by u.id";

        return entityManager.createQuery(jpql, User.class).getResultList();
    }

}
