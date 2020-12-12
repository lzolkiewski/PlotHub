package com.example.plot.jpa.offer;

import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.jpa.offer.address.Street;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private City city;

    @JoinColumn(name = "street_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Street street;

    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
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
