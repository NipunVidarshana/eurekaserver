package com.logic.springjwt.DTO;


public class InstituteSectorDTO {
    private Long id;
    private String name;

    public InstituteSectorDTO() {
    }

    public InstituteSectorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
