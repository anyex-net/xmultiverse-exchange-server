package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class UserLeftQuota {

    private String asset;

    private String leftQuota;
}
