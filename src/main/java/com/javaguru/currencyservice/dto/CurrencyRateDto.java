package com.javaguru.currencyservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyRateDto {

    @JsonInclude(Include.NON_NULL)
    private Long id;
    private String base;
    private String to;
    private BigDecimal rate;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyRateDto{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRateDto that = (CurrencyRateDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(base, that.base) &&
                Objects.equals(to, that.to) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, base, to, rate);
    }

}
