package com.javaguru.currencyservice.service;

import com.javaguru.currencyservice.dto.CurrencyExchangeRateResponse;
import com.javaguru.currencyservice.dto.CurrencyRateDto;
import com.javaguru.currencyservice.repository.CurrencyRateEntity;
import com.javaguru.currencyservice.repository.CurrencyRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CurrencyRateService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyRateService.class);

    private final RestTemplate restTemplate;
    private final CurrencyRateRepository currencyRateRepository;
    private final BeanConverter converter;

    CurrencyRateService(RestTemplate restTemplate,
                        CurrencyRateRepository currencyRateRepository, BeanConverter converter) {
        this.restTemplate = restTemplate;
        this.currencyRateRepository = currencyRateRepository;
        this.converter = converter;
    }

    public CurrencyRateDto createRate(CurrencyRateDto dto) {
        CurrencyRateEntity entity = converter.from(dto);
        currencyRateRepository.save(entity);
        return converter.from(entity);
    }

    public CurrencyRateDto findCurrencyRate(String baseCurrency, String toCurrency) {

        Optional<CurrencyRateEntity> existingCurrencyRate = currencyRateRepository
                .findByBaseCurrencyAndToCurrency(baseCurrency, toCurrency);

        if (existingCurrencyRate.isPresent()) {
            CurrencyRateEntity entity = existingCurrencyRate.get();
            return converter.from(entity);
        }

        String url = UriComponentsBuilder.fromHttpUrl("https://api.exchangeratesapi.io/latest")
                .queryParam("base", baseCurrency)
                .queryParam("symbols", toCurrency)
                .build()
                .toUriString();

        log.info("Sending request to {}", url);
        ResponseEntity<CurrencyExchangeRateResponse> currencyRates = restTemplate.getForEntity(url, CurrencyExchangeRateResponse.class);
        log.info("Received response {}", currencyRates);

        BigDecimal rate = getRateFromResponse(currencyRates, toCurrency);

        CurrencyRateDto rateDto = new CurrencyRateDto();
        rateDto.setBase(baseCurrency);
        rateDto.setTo(toCurrency);
        rateDto.setRate(rate);

        return rateDto;
    }

    private BigDecimal getRateFromResponse(ResponseEntity<CurrencyExchangeRateResponse> currencyRates, String currency) {
        if (currencyRates.getBody() != null) {
            return currencyRates.getBody().getRates().get(currency);
        } else {
            throw new IllegalArgumentException("Response body not found.");
        }
    }
}
