package com.logic.springjwt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "institute_name")
public class InstituteSectorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_id")
    private Long id;

    private String name;

    public InstituteSectorModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}