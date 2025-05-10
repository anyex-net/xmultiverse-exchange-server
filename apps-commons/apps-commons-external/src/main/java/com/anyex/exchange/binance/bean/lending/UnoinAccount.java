package com.anyex.exchange.binance.bean.lending;

import lombok.Data;

import java.util.List;

@Data
public class UnoinAccount {

    private List<PositionAmountVos> positionAmountVos;
    private String totalAmountInBTC;
    private String totalAmountInUSDT;
    private String totalFixedAmountInBTC;
    private String totalFixedAmountInUSDT;
    private String totalFlexibleInBTC;
    private String totalFlexibleInUSDT;
}
