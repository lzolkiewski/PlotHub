package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.management.OffersFilter;
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

    public List<Offer> getOffers(OffersFilter offersFilter){
        String jpql = "select o from Offer o";

        if ( offersFilter.getPlotTypeId()!=null || (offersFilter.getSurroundingId()!=null)
                || (offersFilter.getBuilding()!=null && offersFilter.getBuilding())
                || (offersFilter.getFence()!=null && offersFilter.getFence())
                || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                || (offersFilter.getCity()!=null && offersFilter.getCity().compareTo("")!=0)
                || (offersFilter.getCountry()!=null && offersFilter.getCountry().compareTo("")!=0)
                || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null) {
            jpql+= " where";

            if (offersFilter.getPlotTypeId() != null) {
                jpql += " o.plotType.id = :pti";
            }
            if (offersFilter.getSurroundingId() != null) {
                if (offersFilter.getPlotTypeId() != null) {
                    jpql += " and";
                }

                jpql += " o.id = (select os.offer.id from OfferSurrounding os where os.surrounding.id = :si)";
            }
            if (offersFilter.getBuilding() != null && offersFilter.getBuilding()) {
                if (offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null) {
                    jpql += " and";
                }

                jpql += " o.building = :bui";
            }
            if (offersFilter.getFence() != null && offersFilter.getFence()) {
                if (offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                        || (offersFilter.getBuilding() != null && offersFilter.getBuilding())) {
                    jpql += " and";
                }

                jpql += " o.fence = :fen";
            }
            if (offersFilter.getAreaFrom() != null || offersFilter.getAreaTo() != null) {
                if (offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null) {
//                    || offersFilter.getCity()!=null || offersFilter.getCountry()!=null
//                    || offersFilter.getBuilding() ){
                    jpql += " and";
                }

                if (offersFilter.getAreaFrom() != null) {
                    jpql += " o.area >= :afr";
                }
                if (offersFilter.getAreaTo() != null) {
                    if (offersFilter.getAreaFrom() != null) {
                        jpql += " and";
                    }
                    jpql += " o.area <= :ato";
                }
            }
            if (offersFilter.getPriceFrom() != null || offersFilter.getPriceTo() != null) {
                if (offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                        || offersFilter.getCity() != null || offersFilter.getCountry() != null
                        || offersFilter.getBuilding() || offersFilter.getAreaFrom() != null
                        || offersFilter.getAreaTo() != null) {
                    jpql += " and";
                }
                if (offersFilter.getPriceFrom() != null) {
                    jpql += " o.price >= :pfr";
                }
                if (offersFilter.getPriceTo() != null) {
                    if (offersFilter.getPriceFrom() != null) {
                        jpql += " and";
                    }
                    jpql += " o.price <= :pto";
                }
            }
            if( offersFilter.getCity()!=null && offersFilter.getCity().compareTo("")!=0){
                if ( offersFilter.getPlotTypeId()!=null || offersFilter.getSurroundingId()!=null
                        || (offersFilter.getBuilding()!=null && offersFilter.getBuilding())
                        || (offersFilter.getFence()!=null && offersFilter.getFence())
                        || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                        || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null) {
                    jpql+=" and";
                }

                jpql += " o.address.city.name = :cin";
            }
            if( offersFilter.getCountry()!=null && offersFilter.getCountry().compareTo("")!=0 ){
                if ( offersFilter.getPlotTypeId()!=null || offersFilter.getSurroundingId()!=null
                        || (offersFilter.getBuilding()!=null && offersFilter.getBuilding())
                        || (offersFilter.getFence()!=null && offersFilter.getFence())
                        || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                        || offersFilter.getCity()!=null && offersFilter.getCity().compareTo("")!=0
                        || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null){
                    jpql+=" and";
                }

                jpql += " o.address.country.name = :con";
            }
        }
            jpql+=" order by o.id";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);

        if (offersFilter.getPlotTypeId()!=null){
            query.setParameter("pti", offersFilter.getPlotTypeId());
        }
        if (offersFilter.getSurroundingId()!=null) {
            query.setParameter("si", offersFilter.getSurroundingId());
        }
        if(offersFilter.getPriceFrom()!=null) {
            query.setParameter("pfr", offersFilter.getPriceFrom());
        }
        if (offersFilter.getPriceTo()!=null) {
            query.setParameter("pto", offersFilter.getPriceTo());
        }
        if(offersFilter.getAreaFrom()!=null) {
            query.setParameter("afr", offersFilter.getAreaFrom());
        }
        if (offersFilter.getAreaTo()!=null) {
            query.setParameter("ato", offersFilter.getAreaTo());
        }
        if (offersFilter.getFence()!=null && offersFilter.getFence() ) {
            query.setParameter("fen", offersFilter.getFence());
        }
        if (offersFilter.getBuilding()!=null && offersFilter.getBuilding() ) {
            query.setParameter("bui", offersFilter.getBuilding());
        }
        if (offersFilter.getCountry()!=null){
            query.setParameter("con", offersFilter.getCountry());
        }
        if(offersFilter.getCity()!=null && offersFilter.getCity().compareTo("")!=0) {
            query.setParameter("cin", offersFilter.getCity());
        }

        return query.getResultList();
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
    public List<Offer> getOffersInOrder(Offer offersSorter) {
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
