package com.javaguru.currencyservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class CurrencyExchangeRateResponse {

    private String base;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Map<String, BigDecimal> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyExchangeRateResponse that = (CurrencyExchangeRateResponse) o;
        return Objects.equals(base, that.base) &&
                Objects.equals(date, that.date) &&
                Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, date, rates);
    }

    @Override
    public String toString() {
        return "CurrencyRateDto{" +
                "base='" + base + '\'' +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }
}
