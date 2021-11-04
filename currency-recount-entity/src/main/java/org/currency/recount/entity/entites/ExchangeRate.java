package org.currency.recount.entity.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExchangeRate {

    private Currency from;
    private Currency to;

    private BigDecimal unitPer;
    private BigDecimal perUnit;
}
