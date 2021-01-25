package com.example.plot.models.jpa.offer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.plot.models.jpa.offer.address.City;
import com.example.plot.models.jpa.offer.address.Country;
import com.example.plot.models.jpa.offer.address.Street;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(cascade=CascadeType.ALL)
    @NotNull
    private City city;

    @JoinColumn(name = "street_id", referencedColumnName = "id")
    @ManyToOne(cascade=CascadeType.ALL)
    @NotNull
    private Street street;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(cascade=CascadeType.ALL)
    @NotNull
    private Country country;

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
