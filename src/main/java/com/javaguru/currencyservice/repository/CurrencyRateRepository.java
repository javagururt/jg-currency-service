package com.javaguru.currencyservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRateEntity, Long> {

  Optional<CurrencyRateEntity> findByBaseCurrencyAndToCurrency(String baseCurrency, String toCurrency);

}
