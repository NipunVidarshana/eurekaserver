package com.logic.springjwt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Air_Field_Runway")
public class AirFieldDataModel {

    @Id
    @Digits(integer = 12,  message = "sinNumber must be a 12-digit number", fraction = 0)
    private long sinNumber;

    @NotNull(message = "Institution name is required")
    @Size(min = 2, max = 100, message = "Institution name must be between 2 and 100 characters")
    private String institutionName;

    @NotNull(message = "Institution sector is required")
    private String institutionSector;

    @NotNull(message = "Asset code is required")
    private String assetCode;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Location is required")
    private String location;

    @NotNull(message = "Land area is required")
    private String landArea;

    @NotNull(message = "LandOwnership is required")
    private String landOwnership;


    @Column(name = "ownership", length = 50)
    @NotNull(message = "Ownership type is required")
    private String ownership;

    @Column(name = "fromWhom", length = 50)
    private String fromWhom;

    @Column(name = "toWhom", length = 50)
    private String toWhom;

    private LocalDate startPeriod;

    private LocalDate endPeriod;


    private String paymentMethod; // One Time / Monthly / Yearly

    private String incomeOrPayment;


    @Digits(integer = 12, fraction = 2, message = "Amount must be a valid decimal number with up to 2 decimal places")
    private BigDecimal amount;


    private Integer aircraftCount;


    private Integer passengerCount;


    private Integer freightMT;


    @Column(name = "states", length = 100)
    private int states = 0; // 0: Not Verified;

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getLandOwnership() {
        return landOwnership;
    }

    public void setLandOwnership(String landOwnership) {
        this.landOwnership = landOwnership;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(long sinNumber) {
        this.sinNumber = sinNumber;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFromWhom() {
        return fromWhom;
    }

    public void setFromWhom(String fromWhom) {
        this.fromWhom = fromWhom;
    }

    public String getToWhom() {
        return toWhom;
    }

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDate startPeriod) {
        this.startPeriod = startPeriod;
    }

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDate endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getIncomeOrPayment() {
        return incomeOrPayment;
    }

    public void setIncomeOrPayment(String incomeOrPayment) {
        this.incomeOrPayment = incomeOrPayment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

