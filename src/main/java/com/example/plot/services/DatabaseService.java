package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.jpa.Surrounding;
import com.example.plot.jpa.User;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;
import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.management.LoginRegister;
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

    public User findMatchingEmailUser(LoginRegister user){
        for ( User usr : getUsers() ) {
            if ( usr.getEmail().compareTo(user.getEmail()) == 0 ) {
                return usr;
            }
        }

        return null;
    }

    public Boolean userExists(LoginRegister user) {
        return findMatchingEmailUser(user) != null;
    }
    public Boolean matchingPassword(User userDB, LoginRegister user){
        return userDB.getPassword().compareTo(user.getPassword()) == 0;
    }

    public User getUser(LoginRegister user) {
        User userDB = findMatchingEmailUser(user);

        if ( userDB != null && matchingPassword(userDB, user) ){
            return userDB;
        }

        return null;
    }
    public User getUserByEmail(String email) {
        String jpql = "select u from User u where u.email = :em";

        return entityManager.createQuery(jpql, User.class).getSingleResult();
    }

    public User addUser(User user) {
        entityManager.persist(user);

        return user;
    }





}
