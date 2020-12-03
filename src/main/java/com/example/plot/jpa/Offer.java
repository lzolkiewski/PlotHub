package com.example.plot.jpa;

import com.example.plot.jpa.offer.Address;
import com.example.plot.jpa.offer.DriveType;
import com.example.plot.jpa.offer.PlotType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Offer")
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
    private Double length;

    @Min(5)
    @NotNull
    @Column(name = "width")
    private Double width;

    @Min(25)
    @NotNull
    @Column(name = "area")
    private Double area;

    @Lob
    @Size(max = 60000, min = 5)
    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @Min(0)
    @NotNull
    private Double price;

    @Column(name = "fence")
    @NotNull
    private Boolean fence;

    @Column(name = "building")
    @NotNull
    private Boolean building;

    @JoinColumn(name = "drive_type_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private DriveType drive_type;

    @JoinColumn(name = "plot_type_id", referencedColumnName = "id")
    @ManyToOne
    @NotNull
    private PlotType plot_type;

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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public DriveType getDrive_type() {
        return drive_type;
    }

    public void setDrive_type(DriveType drive_type) {
        this.drive_type = drive_type;
    }

    public PlotType getPlot_type() {
        return plot_type;
    }

    public void setPlot_type(PlotType plot_type) {
        this.plot_type = plot_type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
