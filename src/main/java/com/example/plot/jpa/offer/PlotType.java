package com.example.plot.jpa.offer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PlotType")
public class PlotType {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(max = 45, min = 4)
    @Column(name = "name")
    @NotNull
    private String name;

    public PlotType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
