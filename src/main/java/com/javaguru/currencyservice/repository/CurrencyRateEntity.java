package com.javaguru.currencyservice.repository;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cs_currency_rates", uniqueConstraints = {
    @UniqueConstraint(name = "cr_base_to_uq", columnNames = {"base_currency", "to_currency"})
}
)
public class CurrencyRateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "base_currency", nullable = false, length = 3)
  private String baseCurrency;

  @Column(name = "to_currency", nullable = false, length = 3)
  private String toCurrency;

  @Column(name = "rate", precision = 8, scale = 4)
  private BigDecimal rate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBaseCurrency() {
    return baseCurrency;
  }

  public void setBaseCurrency(String baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  public String getToCurrency() {
    return toCurrency;
  }

  public void setToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrencyRateEntity that = (CurrencyRateEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(baseCurrency, that.baseCurrency) &&
        Objects.equals(toCurrency, that.toCurrency) &&
        Objects.equals(rate, that.rate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, baseCurrency, toCurrency, rate);
  }

  @Override
  public String toString() {
    return "CurrencyRateEntity{" +
        "id=" + id +
        ", base='" + baseCurrency + '\'' +
        ", to='" + toCurrency + '\'' +
        ", rate=" + rate +
        '}';
  }
}
