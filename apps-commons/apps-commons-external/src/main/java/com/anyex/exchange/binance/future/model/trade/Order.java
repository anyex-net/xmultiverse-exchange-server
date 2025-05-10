package com.anyex.exchange.binance.future.model.trade;

import java.math.BigDecimal;

import com.anyex.exchange.binance.future.constant.BinanceApiConstants;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
public class Order
{
    private String     clientOrderId;
    
    private BigDecimal cumQuote;
    
    private BigDecimal executedQty;
    
    private Long       orderId;
    
    private BigDecimal origQty;
    
    private BigDecimal price;
    
    private BigDecimal avgPrice;
    
    private Boolean    reduceOnly;
    
    private String     side;
    
    private String     positionSide;
    
    private String     status;
    
    private BigDecimal stopPrice;
    
    private String     symbol;
    
    private String     timeInForce;
    
    private String     type;
    
    private Long       updateTime;
    
    private String     workingType;
}
