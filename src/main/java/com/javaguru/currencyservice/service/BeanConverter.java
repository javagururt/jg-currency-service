package com.javaguru.currencyservice.service;

import com.javaguru.currencyservice.dto.CurrencyRateDto;
import com.javaguru.currencyservice.repository.CurrencyRateEntity;
import org.springframework.stereotype.Component;

@Component
class BeanConverter {

    CurrencyRateDto from(CurrencyRateEntity entity) {
        CurrencyRateDto dto = new CurrencyRateDto();
        dto.setId(entity.getId());
        dto.setBase(entity.getBaseCurrency());
        dto.setRate(entity.getRate());
        dto.setTo(entity.getToCurrency());
        return dto;
    }

    CurrencyRateEntity from(CurrencyRateDto dto) {
        CurrencyRateEntity entity = new CurrencyRateEntity();
        entity.setBaseCurrency(dto.getBase());
        entity.setRate(dto.getRate());
        entity.setToCurrency(dto.getTo());
        entity.setId(dto.getId());
        return entity;
    }
}
