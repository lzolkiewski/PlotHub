package com.example.plot.jpa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "surrounding")
public class Surrounding {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(max = 45, min = 5)
    @Column(name = "name")
    @NotNull
    private String name;

    public Surrounding() {
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
