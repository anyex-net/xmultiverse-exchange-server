package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class PurchaseRecord {

    private String amount;
    private String asset;
    private long createTime;
    private String lendingType;
    private Integer lot;
    private String productName;
    private int purchaseId;
    private String status;
}
