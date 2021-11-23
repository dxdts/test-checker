package com.curr.crypto.model.currency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bpi {
    @JsonProperty("USD")
    private USD usd;
    @JsonProperty("GBP")
    private GBP gpb;
    @JsonProperty("EUR")
    private EUR eur;
}
