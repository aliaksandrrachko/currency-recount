package org.currency.recount.api.services;

import org.currency.recount.entity.entites.ExchangeRate;
import org.currency.recount.entity.entites.ExchangeRateTable;

import java.time.LocalDate;

public interface IHistoricalExchangeRatesProvider {

    ExchangeRate get(String from, String to, LocalDate localDate);

    ExchangeRateTable get(String from, LocalDate localDate);
}
