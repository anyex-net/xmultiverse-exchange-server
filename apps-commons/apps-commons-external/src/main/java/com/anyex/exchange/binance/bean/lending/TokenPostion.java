package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class TokenPostion {

    private String annualInterestRate;
    private String asset;
    private String avgAnnualInterestRate;
    private String canRedeem;
    private String dailyInterestRate;
    private String freeAmount;
    private String freezeAmount;
    private String lockedAmount;
    private String productId;
    private String productName;
    private String redeemingAmount;
    private String totalAmount;
    private String totalInterest;
}
