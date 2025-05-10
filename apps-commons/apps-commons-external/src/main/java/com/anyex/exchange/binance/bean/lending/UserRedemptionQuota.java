package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

@Data
public class UserRedemptionQuota {

    String asset;

    String dailyQuota;

    String leftQuota;

    String minRedemptionAmount;
}
