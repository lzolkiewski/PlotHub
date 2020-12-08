package com.example.plot.management;

public class Planer {
//    home section
    private Integer homeLength;
    private Integer homeWidth;
//    garage section
    private Integer parkingLength;
    private Integer parkingWidth;
    private Integer parkingSpots;
//    garbage section
    private Integer garbageLength;
    private Integer garbageWidth;
//    garden section
    private Integer gardenLength;
    private Integer gardenWidth;
//    well section
    private Boolean well;
//    sewage section
    private Boolean sewage;
//just some options
    private Integer plotTypeId;
    private Integer surroundingId;
    private String city;
    private String country;

    public Integer getHomeLength() {
        return homeLength;
    }

    public void setHomeLength(Integer homeLength) {
        this.homeLength = homeLength;
    }

    public Integer getHomeWidth() {
        return homeWidth;
    }

    public void setHomeWidth(Integer homeWidth) {
        this.homeWidth = homeWidth;
    }

    public Integer getPlotTypeId() {
        return plotTypeId;
    }

    public void setPlotTypeId(Integer plotTypeId) {
        this.plotTypeId = plotTypeId;
    }

    public Integer getSurroundingId() {
        return surroundingId;
    }

    public void setSurroundingId(Integer surroundingId) {
        this.surroundingId = surroundingId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getParkingLength() {
        return parkingLength;
    }

    public void setParkingLength(Integer parkingLength) {
        this.parkingLength = parkingLength;
    }

    public Integer getParkingWidth() {
        return parkingWidth;
    }

    public void setParkingWidth(Integer parkingWidth) {
        this.parkingWidth = parkingWidth;
    }

    public Integer getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(Integer parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public Integer getGarbageLength() {
        return garbageLength;
    }

    public void setGarbageLength(Integer garbageLength) {
        this.garbageLength = garbageLength;
    }

    public Integer getGarbageWidth() {
        return garbageWidth;
    }

    public void setGarbageWidth(Integer garbageWidth) {
        this.garbageWidth = garbageWidth;
    }

    public Boolean getWell() {
        return well;
    }

    public void setWell(Boolean well) {
        this.well = well;
    }

    public Boolean getSewage() {
        return sewage;
    }

    public void setSewage(Boolean sewage) {
        this.sewage = sewage;
    }

    public Integer getGardenLength() {
        return gardenLength;
    }

    public void setGardenLength(Integer gardenLength) {
        this.gardenLength = gardenLength;
    }

    public Integer getGardenWidth() {
        return gardenWidth;
    }

    public void setGardenWidth(Integer gardenWidth) {
        this.gardenWidth = gardenWidth;
    }
}
