package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class RedemptionRecord {

    private String amount;
    private String asset;
    private Long createTime;
    private String principal;
    private String projectId;
    private String projectName;
    private String status;
    private String type;
    private String interest;
    private Long startTime;
}
