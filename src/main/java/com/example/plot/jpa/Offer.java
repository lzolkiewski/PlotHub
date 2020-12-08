package com.example.plot.jpa;

import com.example.plot.jpa.offer.Address;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(max = 45, min = 5)
    @Column(name = "title")
    @NotNull
    private String title;

    @Min(5)
    @NotNull
    @Column(name = "length")
    private Integer length;

    @Min(5)
    @NotNull
    @Column(name = "width")
    private Integer width;

    @Min(25)
    @NotNull
    @Column(name = "area")
    private Integer area;

    @Lob
    @Size(max = 60000, min = 5)
    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @Min(0)
    @NotNull
    private Integer price;

    @Column(name = "fence")
    @NotNull
    private Boolean fence;

    @Column(name = "building")
    @NotNull
    private Boolean building;

    @JoinColumn(name = "drive_type_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private DriveType driveType;

    @JoinColumn(name = "plot_type_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private PlotType plotType;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private Address address;

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public DriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    public PlotType getPlotType() {
        return plotType;
    }

    public void setPlotType(PlotType plotType) {
        this.plotType = plotType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
