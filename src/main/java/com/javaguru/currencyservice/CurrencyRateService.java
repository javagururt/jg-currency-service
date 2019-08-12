package com.javaguru.currencyservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
class CurrencyRateService {

    private final RestTemplate restTemplate;

    CurrencyRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    ResponseEntity<CurrencyRateDto> getCurrencyRates() {
        ResponseEntity<CurrencyRateDto> rate = restTemplate.getForEntity("https://api.exchangeratesapi.io/latest", CurrencyRateDto.class);
        return rate;
    }

    ResponseEntity<CurrencyRateDto> getCurrencyRate(String from, String to) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.exchangeratesapi.io/latest")
                .queryParam("base", from)
                .queryParam("symbols", to)
                .build()
                .toUriString();

        System.out.println("Sending request to " + url);
        ResponseEntity<CurrencyRateDto> rate = restTemplate.getForEntity(url, CurrencyRateDto.class);
        return rate;
    }
}
