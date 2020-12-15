package com.example.plot.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_offers")
public class UserOffers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private User user;

    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Offer offer;

    @Column(name = "date")
    private Date date;

    public UserOffers() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
