package com.example.plot.services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.plot.jpa.User;
import com.example.plot.management.LoginRegister;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @PersistenceContext
    EntityManager entityManager;

    public User getUserById(LoginRegister user){
        return entityManager.find(User.class, user.getId());
    }

    public User getUserByEmail(LoginRegister user){
        try {
            String jpql = "select u from User u where u.email = :em";

            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);

            if ( user.getEmail() != null ){
                query.setParameter("em", user.getEmail());
            } else {
                return null;
            }

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public User getUser(LoginRegister user) {
        User userDB = getUserByEmail(user);

        if ( userDB != null && checkPassword(userDB, user) ){
            return userDB;
        }

        return null;
    }

    public User addUser(User user) {
        entityManager.persist(user);

        return user;
    }

    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    public Boolean userExists(LoginRegister user) {
        return getUserByEmail(user) != null;
    }

    public Boolean checkPassword(User userDB, LoginRegister user){
        return userDB.getPassword().compareTo(user.getPassword()) == 0;
    }



}
