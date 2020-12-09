package com.example.plot.management;

import com.example.plot.PlotHub;

import java.math.BigInteger;

public class Planer {
//    home section
    private Integer homeLength;
    private Integer homeWidth;
//    parking section
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

//    checking data
    public Boolean checkTheNeedToFindCountry(){
        return getCountry()!=null && getCountry().compareTo("")!=0;
    }
    public Boolean checkTheNeedToFindCity(){
        return getCity()!=null && getCity().compareTo("")!=0;
    }
    public Boolean checkTheNeedToCalculateSurface() {
        return ( getWell()!=null && getWell() ) || ( getGarbageLength()!=null && getGarbageWidth()!=null )
                 || ( getSewage()!=null && getSewage() ) || ( getHomeLength()!=null && getHomeWidth()!=null )
                 || ( getParkingLength()!=null && getParkingWidth()!=null )
                 || ( getGardenLength()!=null && getGardenWidth()!=null);
    }
    //    checking whether there is the object
    public Boolean isHome() {
        return getHomeLength()!=null && getHomeWidth()!=null;
    }
    public Boolean isParking() {
        return getParkingLength()!=null && getParkingWidth()!=null;
    }
    public Boolean isGarbage() { return getGarbageLength()!=null && getGarbageWidth()!=null; }
    public Boolean isGarden() { return getGardenLength()!=null && getGardenWidth()!=null; }
//    calculating the needed surface
    public Integer calculateSurface(){
        Integer surface = 0;

        surface = calculateHomeSurface(surface);

        return surface;
    }
//    single plot object's surface calculations
    public Integer calculateHomeSurface(Integer surface) {
        //        if user chosen only home then special condition can be applied
        if ( !isGarbage() && !isGarden() && isParking() && getSewage() && getWell() && isHome() ){
            if ( getHomeWidth()<= 10 ){
//                exceptional distance of ~ 2 m on narrow plot where width is 16 at most
                surface += ( getHomeWidth() + PlotHub.requirements.getHomeBorderDistEx() )
                        * ( getHomeLength() + PlotHub.requirements.getHomeBorderDist() );

                return surface;
            } else {
                return 0;
            }
        } else {
//                here no longer exceptional distance is applied
            surface += ( getHomeWidth() + PlotHub.requirements.getHomeBorderDist() )
                    * ( getHomeLength() + PlotHub.requirements.getHomeBorderDist() );
        }

        return surface;
    }
    public Integer calculateGreenerySurface(Integer surface) {
//        that is how much the greenery has to take place on the plot 25% of total surface
//        75 / 25 is 3 so after dividing the needed surface by 3 and adding the result we should be left with
//        total surface needed
        return surface += surface / 3;
    }

}
