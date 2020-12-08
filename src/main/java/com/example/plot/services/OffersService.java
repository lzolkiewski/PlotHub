package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.management.OffersSorter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    public Offer getOffer(Integer id){
        return entityManager.find(Offer.class, id);
    }

    public List<Offer> getOffers(){
        String jpql = "select offer from Offer offer order by offer.id";
        return entityManager.createQuery(jpql, Offer.class).getResultList();
    }

    public Offer createOffer(Offer offer){
        entityManager.persist(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id){
        Offer offer = entityManager.find(Offer.class, id);
        entityManager.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer){
        return entityManager.merge(offer);
    }

//    sort by price / area
    public List<Offer> getOffersInOrder(OffersSorter offersSorter) {
        String jpql;
        if(offersSorter.getArea()!=null){
            jpql = "select offer from Offer offer order by offer.area";
        }else{
            jpql = "select offer from Offer offer order by offer.price";
        }

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);

        return query.getResultList();
    }
}
