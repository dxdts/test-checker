package com.curr.crypto.model.currency;

import lombok.Data;

@Data
public class CurrentPrice {

    private Bpi bpi;
    private Time time;
    private String disclaimer;
    private String chartName;
}
