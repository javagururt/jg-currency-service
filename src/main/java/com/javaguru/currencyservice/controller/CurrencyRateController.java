package com.javaguru.currencyservice.controller;

import com.javaguru.currencyservice.dto.CurrencyRateDto;
import com.javaguru.currencyservice.service.CurrencyRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/rates")
class CurrencyRateController {

    private final CurrencyRateService service;

    CurrencyRateController(CurrencyRateService service) {
        this.service = service;
    }

    @GetMapping(params = {"from", "to"})
    public CurrencyRateDto getRate(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {
        return service.findCurrencyRate(from, to);
    }


    @PostMapping
    public ResponseEntity<CurrencyRateDto> create(@RequestBody CurrencyRateDto dto, UriComponentsBuilder builder) {
        CurrencyRateDto response = service.createRate(dto);
        return ResponseEntity.created(builder
                .path("/api/v1/rates/{id}")
                .buildAndExpand(response.getId()).toUri()).build();
    }
}
