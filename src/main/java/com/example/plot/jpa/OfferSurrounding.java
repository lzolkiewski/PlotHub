package com.example.plot.jpa;

import javax.persistence.*;

@Entity
@Table(name = "OfferSurrounding")
public class OfferSurrounding {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    @ManyToOne
    private Offer offer;

    @JoinColumn(name = "surrounding_id", referencedColumnName = "id")
    @ManyToOne
    private Surrounding surrounding;

    public OfferSurrounding() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Surrounding getSurrounding() {
        return surrounding;
    }

    public void setSurrounding(Surrounding surrounding) {
        this.surrounding = surrounding;
    }
}
