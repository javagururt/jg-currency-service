package com.javaguru.currencyservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rates")
class CurrencyRateController {

    private final CurrencyRateService service;

    CurrencyRateController(CurrencyRateService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CurrencyRateDto> getRates() {
        return service.getCurrencyRates();
    }

    @GetMapping(params = {"from", "to"})
    public ResponseEntity<CurrencyRateDto> getRate(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {

        return service.getCurrencyRate(from, to);
    }
}
