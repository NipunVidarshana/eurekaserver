package com.logic.springjwt.DTO;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FromToWhomDto {
    @NotNull
    private String ownership;
    private String fromWhom;
    private String toWhom;
    private LocalDate startPeriod;
    private LocalDate endPeriod;

    private String paymentMethod;
    private String incomeOrPayment;

    @Digits(integer = 12, fraction = 2)
    private BigDecimal amount;

    private int states;


    public FromToWhomDto(int states, BigDecimal amount, String incomeOrPayment, String paymentMethod, LocalDate endPeriod, String toWhom, LocalDate startPeriod, String fromWhom, String ownership) {
        this.states = states;
        this.amount = amount;
        this.incomeOrPayment = incomeOrPayment;
        this.paymentMethod = paymentMethod;
        this.endPeriod = endPeriod;
        this.toWhom = toWhom;
        this.startPeriod = startPeriod;
        this.fromWhom = fromWhom;
        this.ownership = ownership;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
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

    public LocalDate getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(LocalDate endPeriod) {
        this.endPeriod = endPeriod;
    }

    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(LocalDate startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
}
