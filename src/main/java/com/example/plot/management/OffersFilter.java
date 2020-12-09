package com.example.plot.management;

import com.example.plot.jpa.Offer;

import javax.persistence.TypedQuery;
import java.util.List;

public class OffersFilter {
    private Integer areaFrom;
    private Integer areaTo;
    private Integer priceFrom;
    private Integer priceTo;
    private Integer plotTypeId;
    private Integer surroundingId;
    private Boolean fence;
    private Boolean building;
    private String country;
    private String city;

    public Integer getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(Integer areaFrom) {
        this.areaFrom = areaFrom;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPlotTypeId() {
        return plotTypeId;
    }

    public void setPlotTypeId(Integer plotTypeId) {
        this.plotTypeId = plotTypeId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getFence() {
        return fence;
    }

    public void setFence(Boolean fence) {
        this.fence = fence;
    }

    public Boolean getBuilding() {
        return building;
    }

    public void setBuilding(Boolean building) {
        this.building = building;
    }

    public Integer getSurroundingId() {
        return surroundingId;
    }

    public void setSurroundingId(Integer surroundingId) {
        this.surroundingId = surroundingId;
    }

    public Integer getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(Integer areaTo) {
        this.areaTo = areaTo;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public Boolean checkTheNeedToFindCountry(){
        return getCountry()!=null && getCountry().compareTo("")!=0;
    }
    public Boolean checkTheNeedToFindCity(){
        return getCity()!=null && getCity().compareTo("")!=0;
    }
    public Boolean checkTheNeedToFindFence(){ return getFence()!=null && getFence(); }
    public Boolean checkTheNeedToFindBuilding() {
        return getBuilding()!=null && getBuilding();
    }
}
