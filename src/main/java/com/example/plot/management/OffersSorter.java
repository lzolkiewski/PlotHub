package com.example.plot.management;

import com.example.plot.jpa.Offer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OffersSorter {
    private Integer price;
    private Integer area;
    private Integer year;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Offer> sortOffers(List<Offer>offers){
        if(getPrice()!=null){
            Comparator<Offer> compareByPrice = (Offer o1, Offer o2) -> o1.getPrice().compareTo(o2.getPrice());
            offers.sort(compareByPrice);
        }else{
            Comparator<Offer> compareByArea = (Offer o1, Offer o2) -> o1.getArea().compareTo(o2.getArea());
            offers.sort(compareByArea);
        }
        return offers;
    }
}
