package org.currency.recount.entity.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExchangeRateTable {

    private Currency from;

    private Map<Currency, Rate> rates;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    static class Rate {
        private BigDecimal unitPer;
        private BigDecimal perUnit;
    }
}
