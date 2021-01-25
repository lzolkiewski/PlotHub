package com.example.plot.models.services;

import com.example.plot.models.jpa.Offer;
import com.example.plot.models.jpa.User;
import com.example.plot.models.jpa.UserOffers;
import com.example.plot.controllers.management.OffersFilter;
import com.example.plot.controllers.management.Planer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    public Offer getOfferById(Integer id){
        return entityManager.find(Offer.class, id);
    }

    public List<Offer> getFilteredOffers(OffersFilter offersFilter) {
        String jpql = "select o from Offer o";

        if ( offersFilter.getPlotTypeId()!=null || (offersFilter.getSurroundingId()!=null)
             || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
             || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null
             || offersFilter.checkTheNeedToFindFence() || offersFilter.checkTheNeedToFindBuilding()
             || offersFilter.checkTheNeedToFindCity() || offersFilter.checkTheNeedToFindCountry() ) {
        jpql+= " where";

        if ( offersFilter.getPlotTypeId() != null ) {
            jpql += " o.plotType.id = :pti";
        }
        if ( offersFilter.getSurroundingId() != null ) {
            if ( offersFilter.getPlotTypeId() != null ) {
                jpql += " and";
            }

            jpql += " o.id = :si";
//            jpql += " o.id = (select os.offer.id from OfferSurrounding os where os.surrounding.id = :si)";
        }
        if ( offersFilter.checkTheNeedToFindBuilding() ) {
            if ( offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null ) {
                jpql += " and";
            }

            jpql += " o.building = :bui";
        }
        if ( offersFilter.checkTheNeedToFindFence() ) {
            if ( offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                 || offersFilter.checkTheNeedToFindBuilding() ) {
                jpql += " and";
            }

            jpql += " o.fence = :fen";
        }
        if ( offersFilter.getAreaFrom() != null || offersFilter.getAreaTo() != null ) {
            if ( offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                 || offersFilter.checkTheNeedToFindBuilding() || offersFilter.checkTheNeedToFindFence() ){
                jpql += " and";
            }

            if ( offersFilter.getAreaFrom() != null ) {
                jpql += " o.area >= :afr";
            }

            if ( offersFilter.getAreaTo() != null ) {
                if ( offersFilter.getAreaFrom() != null ) {
                    jpql += " and";
                }

                jpql += " o.area <= :ato";
            }
        }
        if ( offersFilter.getPriceFrom() != null || offersFilter.getPriceTo() != null ) {
            if ( offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                 || offersFilter.getAreaFrom() != null || offersFilter.getAreaTo() != null
                 || offersFilter.checkTheNeedToFindBuilding() || offersFilter.checkTheNeedToFindFence() ) {
                jpql += " and";
            }

            if ( offersFilter.getPriceFrom() != null ) {
                jpql += " o.price >= :pfr";
            }

            if ( offersFilter.getPriceTo() != null ) {
                if ( offersFilter.getPriceFrom() != null ) {
                    jpql += " and";
                }

                jpql += " o.price <= :pto";
            }

        }
        if ( offersFilter.checkTheNeedToFindCity() ) {
            if ( offersFilter.getPlotTypeId()!=null || offersFilter.getSurroundingId()!=null
                 || offersFilter.checkTheNeedToFindBuilding() || offersFilter.checkTheNeedToFindFence()
                 || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                 || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null ) {
                jpql+=" and";
            }

            jpql += " o.address.city.name = :cin";
        }
        if ( offersFilter.checkTheNeedToFindCountry() ){
            if ( offersFilter.getPlotTypeId()!=null || offersFilter.getSurroundingId()!=null
                 || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                 || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null
                 || offersFilter.checkTheNeedToFindBuilding() || offersFilter.checkTheNeedToFindFence()
                 || offersFilter.checkTheNeedToFindCity() ) {
                jpql+=" and";
            }

            jpql += " o.address.country.name = :con";
        }
    }

        jpql+=" order by o.id";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);

        if ( offersFilter.getPlotTypeId()!=null ) {
            query.setParameter("pti", offersFilter.getPlotTypeId());
        }
        if ( offersFilter.getSurroundingId()!=null ) {
            query.setParameter("si", offersFilter.getSurroundingId());
        }
        if ( offersFilter.getPriceFrom()!=null ) {
            query.setParameter("pfr", offersFilter.getPriceFrom());
        }
        if ( offersFilter.getPriceTo()!=null ) {
            query.setParameter("pto", offersFilter.getPriceTo());
        }
        if ( offersFilter.getAreaFrom()!=null ) {
            query.setParameter("afr", offersFilter.getAreaFrom());
        }
        if ( offersFilter.getAreaTo()!=null ) {
            query.setParameter("ato", offersFilter.getAreaTo());
        }
        if ( offersFilter.checkTheNeedToFindFence() ) {
            query.setParameter("fen", offersFilter.getFence());
        }
        if ( offersFilter.checkTheNeedToFindBuilding() ) {
            query.setParameter("bui", offersFilter.getBuilding());
        }
        if ( offersFilter.checkTheNeedToFindCountry() ){
            query.setParameter("con", offersFilter.getCountry());
        }
        if ( offersFilter.checkTheNeedToFindCity() ) {
            query.setParameter("cin", offersFilter.getCity());
        }

        

        return query.getResultList();
    }

    public List<Offer> getPlanerOffers(Planer planer) {
        String jpql = "select o from Offer o";

        if ( (planer.getSurroundingId()!=null) || planer.checkTheNeedToFindCity()
              || planer.checkTheNeedToFindCountry() || planer.checkTheNeedToCalculateSurface() ) {
            jpql+= " where";

            if ( planer.getSurroundingId() != null ) {
                jpql += " o.id = :si";
//                jpql += " o.id = (select os.offer.id from OfferSurrounding os where os.surrounding.id = :si)";
            }
            if ( planer.checkTheNeedToFindCity() ) {
                if ( planer.getSurroundingId()!=null ) {
                    jpql+=" and";
                }

                jpql += " o.address.city.name = :cin";
            }
            if ( planer.checkTheNeedToFindCountry() ) {
                if ( planer.getSurroundingId()!=null || planer.checkTheNeedToFindCity() ){
                    jpql+=" and";
                }

                jpql += " o.address.country.name = :con";
            }
            if ( planer.checkTheNeedToCalculateSurface() ) {
                if ( planer.getSurroundingId()!=null || planer.checkTheNeedToFindCity()
                     || planer.checkTheNeedToFindCountry() ) {
                    jpql+=" and";
                }

//                jpql += " o.area >= :sur";
                jpql += " o.length >= :totlen";

                jpql += " and o.width >= :wid";
            }
        }

        jpql+=" order by o.area";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);

        if ( planer.getSurroundingId()!=null ) {
            query.setParameter("si", planer.getSurroundingId());
        }
        if ( planer.getCity()!=null && planer.getCity().compareTo("")!=0 ) {
            query.setParameter("cin", planer.getCity());
        }
        if ( planer.getCountry()!=null && planer.getCountry().compareTo("")!=0 ){
            query.setParameter("con", planer.getCountry());
        }
        if ( planer.checkTheNeedToCalculateSurface() ) {
            planer.swapLengthsWidths();

            query.setParameter("totlen", (int)(planer.calculateTotalLength()*0.9))
                .setParameter("wid", planer.getHighestWidth());
        }
        return query.getResultList();
    }

    public Offer addOffer(Offer offer) {
        entityManager.persist(offer);

        return offer;
    }

    public void deleteOffer(Integer id) {
        entityManager.createQuery("delete from Offer o where o.id = :id").setParameter("id", id).executeUpdate();
    }

    public Offer saveOffer(Offer offer) {
        return entityManager.merge(offer);
    }

//    sort by price / area
    public List<Offer> getOffersInOrder(Offer offersSorter) {
        String jpql;

        if( offersSorter.getArea()!=null ){
            jpql = "select offer from Offer offer order by offer.area";
        }else{
            jpql = "select offer from Offer offer order by offer.price";
        }

        return entityManager.createQuery(jpql, Offer.class).getResultList();
    }

    public Boolean addressHasErrors(Offer offer){
        return  ( offer.getAddress().getCity().getName().compareTo("") == 0
                || offer.getAddress().getStreet().getName().compareTo("") ==0
                || offer.getAddress().getCountry().getName().compareTo("") == 0 );
    }

    public void linkUserWithOffer(Offer offer, User user) {
        UserOffers userOffers = new UserOffers();

        userOffers.setUser(user);
        userOffers.setOffer(offer);
        userOffers.setDate(new Date());

        entityManager.persist(userOffers);
    }

    public List<Offer>getUserOffers(Integer id){
        try {
            String jpql = "select offer from Offer offer join UserOffers uo on uo.offer.id = offer.id where uo.user.id = :id";

            TypedQuery<Offer>query = entityManager.createQuery(jpql, Offer.class);

            return query.setParameter("id", id).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
