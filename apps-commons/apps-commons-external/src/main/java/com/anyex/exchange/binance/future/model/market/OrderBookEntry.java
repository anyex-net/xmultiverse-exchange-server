package com.anyex.exchange.binance.future.model.market;

import java.math.BigDecimal;

import com.anyex.exchange.binance.future.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrderBookEntry {

    private BigDecimal price;

    private BigDecimal qty;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("price", price)
                .append("qty", qty).toString();
    }
}
