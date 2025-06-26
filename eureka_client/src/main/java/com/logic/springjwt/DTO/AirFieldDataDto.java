package com.logic.springjwt.DTO;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AirFieldDataDto {

    @NotNull(message = "sin number is required")
    @Digits(integer = 12, fraction = 0)
    private Long sinNumber;


    @NotNull(message = "Institution name is required")
    @Size(min = 2, max = 100)
    private String institutionName;

    @NotNull(message = "Institution sector is required")
    private String institutionSector;

    @NotNull
    private String assetCode;

    @NotNull
    private String type;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String landArea;



    @NotNull
    private String ownership;

    @NotNull
    private String landOwnership;




    private Integer aircraftCount;
    private Integer passengerCount;
    private Integer freightMT;


    public AirFieldDataDto(Long sinNumber, String landOwnership, String location, String institutionSector, String institutionName, String assetCode, String type, String name, String landArea, String ownership, Integer aircraftCount, Integer passengerCount, Integer freightMT) {
        this.sinNumber = sinNumber;
        this.landOwnership = landOwnership;
        this.location = location;
        this.institutionSector = institutionSector;
        this.institutionName = institutionName;
        this.assetCode = assetCode;
        this.type = type;
        this.name = name;
        this.landArea = landArea;
        this.ownership = ownership;

        this.aircraftCount = aircraftCount;
        this.passengerCount = passengerCount;
        this.freightMT = freightMT;
    }

    public String getLandOwnership() {
        return landOwnership;
    }

    public void setLandOwnership(String landOwnership) {
        this.landOwnership = landOwnership;
    }



    public AirFieldDataDto(Long sinNumber) {
        this.sinNumber = sinNumber;
    }

    public Long getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(Long sinNumber) {
        this.sinNumber = sinNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstitutionSector() {
        return institutionSector;
    }

    public void setInstitutionSector(String institutionSector) {
        this.institutionSector = institutionSector;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }


    public Integer getAircraftCount() {
        return aircraftCount;
    }

    public void setAircraftCount(Integer aircraftCount) {
        this.aircraftCount = aircraftCount;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Integer getFreightMT() {
        return freightMT;
    }

    public void setFreightMT(Integer freightMT) {
        this.freightMT = freightMT;
    }
}
