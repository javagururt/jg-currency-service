package com.javaguru.currencyservice;

import com.javaguru.currencyservice.repository.CurrencyRateEntity;
import com.javaguru.currencyservice.repository.CurrencyRateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class CurrencyRateControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRateRepository rateRepository;

    @Test
    public void shouldCreateTask() throws Exception {
        when(rateRepository.findByBaseCurrencyAndToCurrency("USD", "EUR"))
                .thenReturn(Optional.ofNullable(createEntity()));

        mockMvc.perform(get("/api/v1/rates")
                .param("from", "USD")
                .param("to", "EUR")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.base").value("USD"))
                .andExpect(jsonPath("$.to").value("EUR"))
                .andExpect(jsonPath("$.rate").value(1));
    }


    private CurrencyRateEntity createEntity() {
        CurrencyRateEntity entity = new CurrencyRateEntity();
        entity.setId(1L);
        entity.setBaseCurrency("USD");
        entity.setToCurrency("EUR");
        entity.setRate(BigDecimal.ONE);
        return entity;
    }

}
