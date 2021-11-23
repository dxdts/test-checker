package com.curr.crypto.service;

import com.curr.crypto.api.BtcApi;
import com.curr.crypto.model.currency.CurrentPrice;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

public class CurrencyService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public CurrentPrice getCurrencyPrice() {
        return objectMapper.readValue(BtcApi.getCurrentPrice(), CurrentPrice.class);
    }
}
