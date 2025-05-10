package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class InterestHistoryRecord {

    private String asset;
    private String interest;
    private String lendingType;
    private String productName;
    private long time;
}
