package com.javaguru.currencyservice;

import com.javaguru.currencyservice.repository.CurrencyRateEntity;
import com.javaguru.currencyservice.repository.CurrencyRateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CurrencyRateRepositoryIT {

    @Autowired
    private CurrencyRateRepository victim;

    @Test
    public void shouldInsert() {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setRate(BigDecimal.ONE);
        currencyRateEntity.setBaseCurrency("USD");
        currencyRateEntity.setToCurrency("EUR");

        CurrencyRateEntity result = victim.save(currencyRateEntity);

        assertThat(result).isEqualTo(expectedEntity());
    }

    private CurrencyRateEntity expectedEntity() {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setRate(BigDecimal.ONE);
        currencyRateEntity.setBaseCurrency("USD");
        currencyRateEntity.setToCurrency("EUR");
        currencyRateEntity.setId(1L);
        return currencyRateEntity;
    }
}