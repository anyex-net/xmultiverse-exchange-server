package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class Product {

    private String asset;

    private String avgAnnualInterestRate;

    private boolean canPurchase;

    private boolean canRedeem;

    private String dailyInterestPerThousand;

    private boolean featured;

    private String minPurchaseAmount;

    private String productId;

    private String purchasedAmount;

    private String status;

    private String upLimit;

    private String upLimitPerUser;
}
