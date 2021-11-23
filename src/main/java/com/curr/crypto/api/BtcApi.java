package com.curr.crypto.api;

import static org.apache.http.HttpStatus.SC_OK;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class BtcApi {
    public static final String API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    public static String getCurrentPrice() {
        return apiResponse()
                .then()
                .log().ifError()
                .statusCode(SC_OK)
                .extract()
                .asString();
    }

    public static Response apiResponse() {
        return RestAssured.given()
                .get(API_URL);
    }
}