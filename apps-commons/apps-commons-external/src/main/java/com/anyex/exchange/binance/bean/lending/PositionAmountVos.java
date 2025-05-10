package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class PositionAmountVos {

    private String amount;
    private String amountInBTC;
    private String amountInUSDT;
    private String asset;
}
