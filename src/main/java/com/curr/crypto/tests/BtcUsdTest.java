package com.curr.crypto.tests;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.curr.crypto.api.BtcApi;
import com.curr.crypto.service.CurrencyService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BtcUsdTest {
    private final CurrencyService currencyService = new CurrencyService();

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        Assertions.assertThat(BtcApi.apiResponse().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Epic("E1")
    @Feature("F1")
    @Story("S1")
    @Test
    @Description("BTC is within certain limits")
    public void testCurrency() {
        final var currencyPrice = currencyService.getCurrencyPrice();
        Assertions.assertThat(currencyPrice.getBpi().getUsd().getRate_float()).isLessThan(70_000.00);
    }
}
