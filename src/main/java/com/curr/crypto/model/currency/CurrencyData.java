package com.curr.crypto.model.currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CurrencyData {
    protected String code;
    protected String symbol;
    protected String rate;
    protected String description;
    protected double rate_float;
}
