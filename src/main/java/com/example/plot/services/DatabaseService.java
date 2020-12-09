package com.example.plot.services;

import com.example.plot.jpa.Offer;
import com.example.plot.jpa.Surrounding;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;
import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.management.OffersFilter;
import com.example.plot.management.Planer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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

    public List<DriveType> getDriveType() {
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

    public List<Offer> getPlanerOffers(Planer planer) {
        String jpql = "select o from Offer o";

        if ( planer.getPlotTypeId()!=null || (planer.getSurroundingId()!=null)
             || planer.checkTheNeedToFindCity() || planer.checkTheNeedToFindCountry()
             || planer.checkTheNeedToCalculateSurface() ) {
            jpql+= " where";

            if ( planer.getPlotTypeId() != null ) {
                jpql += " o.plotType.id = :pti";
            }
            if ( planer.getSurroundingId() != null ) {
                if ( planer.getPlotTypeId() != null ) {
                    jpql += " and";
                }

                jpql += " o.id = (select os.offer.id from OfferSurrounding os where os.surrounding.id = :si)";
            }
            if ( planer.checkTheNeedToFindCity() ) {
                if ( planer.getPlotTypeId()!=null || planer.getSurroundingId()!=null ) {
                    jpql+=" and";
                }

                jpql += " o.address.city.name = :cin";
            }
            if ( planer.checkTheNeedToFindCountry() ) {
                if ( planer.getPlotTypeId()!=null || planer.getSurroundingId()!=null
                     || planer.checkTheNeedToFindCity() ){
                    jpql+=" and";
                }

                jpql += " o.address.country.name = :con";
            }
            if ( planer.checkTheNeedToCalculateSurface() ) {
                if ( planer.getPlotTypeId()!=null || planer.getSurroundingId()!=null
                     || planer.checkTheNeedToFindCity() || planer.checkTheNeedToFindCountry() ) {
                    jpql+=" and";
                }

                jpql += " o.area >= :sur";
            }
        }
        jpql+=" order by o.id";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);

        if ( planer.getPlotTypeId()!=null ) {
            query.setParameter("pti", planer.getPlotTypeId());
        }
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
            query.setParameter("sur", planer.calculateSurface());
        }

        return query.getResultList();
    }
}
