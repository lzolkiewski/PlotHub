package com.example.plot.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.plot.jpa.User;
import com.example.plot.management.LoginRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @PersistenceContext
    EntityManager entityManager;

    public User findMatchingEmailUser(LoginRegister user){
        String jpql = "select u from User u order by u.id";

        for ( User usr : entityManager.createQuery(jpql, User.class).getResultList() ) {
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
