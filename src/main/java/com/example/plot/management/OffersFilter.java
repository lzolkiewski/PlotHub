package com.example.plot.management;

public class OffersFilter {
    private Double area;
    private Double length;
    private Double width;
    private Double price;
    private Integer driveTypeId;
    private Integer plotTypeId;
    private Boolean isFence;
    private Boolean isBuilding;
    private String countryId;
    private String cityId;

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDriveTypeId() {
        return driveTypeId;
    }

    public void setDriveTypeId(Integer driveTypeId) {
        this.driveTypeId = driveTypeId;
    }

    public Integer getPlotTypeId() {
        return plotTypeId;
    }

    public void setPlotTypeId(Integer plotTypeId) {
        this.plotTypeId = plotTypeId;
    }

    public Boolean getFence() {
        return isFence;
    }

    public void setFence(Boolean fence) {
        isFence = fence;
    }

    public Boolean getBuilding() {
        return isBuilding;
    }

    public void setBuilding(Boolean building) {
        isBuilding = building;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
