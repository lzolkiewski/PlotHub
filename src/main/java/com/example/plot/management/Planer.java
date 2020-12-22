package com.example.plot.management;

import com.example.plot.PlotHub;

import java.io.Serializable;


public class Planer implements Serializable {
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
    private Integer surroundingId;
    private String city;
    private String country;

    private Integer surface;

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

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
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
    public Boolean isGarden() { return getGardenLength()!=null && getGardenWidth()!=null; }

//    calculating the needed surface
    //    swap values if length < width
    public void swapLengthsWidths() {
        Integer tmpLen=0;
//        home
        if ( isHome() && ( getHomeWidth() > getHomeLength() ) ) {
            tmpLen = getHomeWidth();
            setHomeWidth(getHomeLength());
            setHomeLength(tmpLen);
        }
//        parking
        if ( isParking() && ( getParkingWidth() > getParkingLength() ) ) {
            tmpLen = getParkingWidth();
            setParkingWidth(getParkingLength());
            setParkingLength(tmpLen);
        }
//        garden
        if ( isGarden() && ( getGardenWidth() > getGardenLength() ) ) {
            tmpLen = getGardenWidth();
            setGardenWidth(getGardenLength());
            setGardenLength(tmpLen);
        }
    }
    //    get highest width
    public Integer getHighestWidth() {
        int maxWidth = 0;
        if ( isHome() ) {
            if ( getHomeWidth() <= 10 ){
                maxWidth = Math.max( getHomeWidth() + PlotHub.requirements.getHomeBorderDist() + PlotHub.requirements.getHomeBorderDistEx(), maxWidth );
            }else {
                maxWidth = Math.max( getHomeWidth() + ( 2 * PlotHub.requirements.getHomeBorderDist() ), maxWidth );
            }
        }
        if ( isParking() ) {
            maxWidth = Math.max( getParkingWidth(), maxWidth );
        }
        if ( getSewage() ) {
            maxWidth = Math.max( 2 * PlotHub.requirements.getSewageBorderDist(), maxWidth );
        }
        if ( isGarden() ) {
            maxWidth = Math.max( getGardenWidth(), maxWidth );
        }
//        also means that the maxWidth is at least larger then the demanded
        if ( getWell() ) {
            maxWidth = Math.max( ( 2 * PlotHub.requirements.getWellBorderDist() ) + PlotHub.requirements.getWellLength(), maxWidth );
        }
        return Math.max( PlotHub.requirements.getGarbageLength() + ( 2 * PlotHub.requirements.getGarbageBorderDist() ), maxWidth );

    }
    //    total calculation
    public Integer calculateSurface() {
        Integer totalLen = 0;
        Integer garbageHomeDistance = PlotHub.requirements.getGarbageHomeDist(),
                sewageHomeDistance = PlotHub.requirements.getSewageHomeDist();
        Boolean counted = false;

//        well has no home distance and can be used to somehow merge home border distance with well's
        if ( getWell() ) {
            totalLen += PlotHub.requirements.getWellLength() + PlotHub.requirements.getWellBorderDist();
        }
//        add home to total distance
        if ( isHome() ) {
//            if there is well the border distance can be skipped
            if ( getWell() ) {
                totalLen += getHomeLength();
            } else {
                totalLen += getHomeLength() + PlotHub.requirements.getHomeBorderDist();
            }
        }
//        add garden to total distance, it's 3rd as it has no requirements for distances from home or border
//        it's also place where we start caring about the distance from home for sewage and garbage
        if ( isGarden() ) {
            totalLen += gardenLength;

            garbageHomeDistance -= gardenLength;
            sewageHomeDistance -= gardenLength;
//            let us also check whether the garden length is enough for garbage to be placed
            if ( garbageHomeDistance <= 0 && isHome() ) {
                totalLen += PlotHub.requirements.getGarbageLength();

                counted = true;
                sewageHomeDistance -= PlotHub.requirements.getGarbageLength();
            }
        }
//        add garage to total distance
        if ( isParking() ) {
//            if there is garbage between garden and parking or garden length is higher than required just add parking length
            if ( isGarden() && (getGardenLength() >= PlotHub.requirements.getGarbageHomeDist()
                 || getGardenLength() >= PlotHub.requirements.getParkingHomeDist()) 
                 || !isHome() ){
                totalLen += getParkingLength();

                sewageHomeDistance -= getParkingLength();
                if ( isGarden() && (getGardenLength() >= PlotHub.requirements.getParkingHomeDist()) ) {
                    garbageHomeDistance -= getParkingLength();
                }
            } else {
//                if there is no garden add full distance to parking required
                totalLen += PlotHub.requirements.getParkingHomeDist() + getParkingLength();

                sewageHomeDistance -= PlotHub.requirements.getParkingHomeDist() + getParkingLength();
                garbageHomeDistance -= PlotHub.requirements.getParkingHomeDist() + getParkingLength();
//                if there is garden and the length of it is less than required length for parking
                if ( isGarden() && getGardenLength() < PlotHub.requirements.getParkingHomeDist() ) {
                    totalLen -= getGardenLength();

                    sewageHomeDistance += getGardenLength();
                    garbageHomeDistance += getGardenLength();
                }
            }
        } else if ( counted ) {
//            there is need to add garbage border distance in case there is no garage
            totalLen += PlotHub.requirements.getGarbageBorderDist();

            sewageHomeDistance -= PlotHub.requirements.getGarbageBorderDist();
        }
//        garbage behind the parking if there is home
        if ( isHome() && !counted ){
//            if distance is too small it needs to be taken into account
            if ( garbageHomeDistance > 0 ) {
                totalLen += garbageHomeDistance;

                sewageHomeDistance -= garbageHomeDistance;
            }
//            add the garbage length and garbage required distance from border
            totalLen += PlotHub.requirements.getGarbageLength() + PlotHub.requirements.getGarbageBorderDist();
            sewageHomeDistance -= PlotHub.requirements.getGarbageLength() + PlotHub.requirements.getGarbageBorderDist();
        }
//        add sewage to total distance
        if ( getSewage() && isHome() ) {
            if ( sewageHomeDistance == 0 ) {
                totalLen += PlotHub.requirements.getSewageBorderDist();
            } else if ( sewageHomeDistance < 0 && -sewageHomeDistance > PlotHub.requirements.getGarbageBorderDist() ) {
                totalLen += PlotHub.requirements.getSewageBorderDist() - PlotHub.requirements.getGarbageBorderDist();
            } else {
                totalLen += PlotHub.requirements.getSewageBorderDist() + sewageHomeDistance;
            }
        }

        setSurface(totalLen*getHighestWidth());

        return getSurface();
    }
    //    single plot object's surface calculations
    public Integer calculateHomeSurfaceWithGarbage() {
        if ( getHomeWidth()<= 10 ){
//                exceptional distance of ~ 2 m on narrow plot where width is 16 at most
            return  ( getHomeWidth() + ( PlotHub.requirements.getHomeBorderDistEx() + PlotHub.requirements.getHomeBorderDist() ) )
                    * ( getHomeLength() + ( PlotHub.requirements.getHomeBorderDist() )
                    + PlotHub.requirements.getGarbageBorderDist() + PlotHub.requirements.getGarbageHomeDist()
                    + PlotHub.requirements.getGarbageLength() );

        } else {
            //                here no longer exceptional distance is applied
            return  ( getHomeWidth() + ( 2 * PlotHub.requirements.getHomeBorderDist() ) )
                    * ( getHomeLength() + PlotHub.requirements.getHomeBorderDist()
                    + PlotHub.requirements.getGarbageBorderDist() + PlotHub.requirements.getGarbageHomeDist()
                    + PlotHub.requirements.getGarbageLength() );
        }
    }
//    not gonna use it
    public Integer calculateMinGreenerySurface(Integer surface) {
//        that is how much the greenery has to take place on the plot 25% of total surface
        int surfaceUsed = 0;
        if ( isGarden() ) {
            surfaceUsed += calculateGardenSurface();
        }
        if ( isHome() ) {
            surfaceUsed += calculateHomeSurface();
        }
        if ( isParking() ) {
            surfaceUsed += calculateParkingSurface();
        }
//        if surface - surface used is less than 1/3 of total surface additional greenery is needed
//        surfaceUsed / 3; // needed greenery

        return surfaceUsed / 3;
    }
    
    public Integer calculateHomeSurface() {
        return getHomeLength() * getHomeWidth();
    }
    public Integer calculateGardenSurface() {
        return getGardenWidth() * getGardenLength();
    }
    public Integer calculateParkingSurface() { return getParkingWidth() * getParkingLength(); }

}
