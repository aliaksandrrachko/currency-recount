package org.currency.recount.controller;

import org.currency.recount.api.services.IHistoricalExchangeRatesProvider;
import org.currency.recount.entity.entites.ExchangeRateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class ExchangeRateController {

    @Autowired
    private IHistoricalExchangeRatesProvider exchangeRatesProvider;

    @GetMapping("/exchange_rates/from/{from}/date/{localDate}")
    public ExchangeRateTable getExchangeRateTable(String from, LocalDate localDate){
        return exchangeRatesProvider.get(from, localDate);
    }
}
