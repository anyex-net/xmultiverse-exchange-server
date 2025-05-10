package com.anyex.exchange.binance.bean.account;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
public class Insurance {

 private String asset;

 private BigDecimal marginBalance;

 private Timestamp  insertTime;

}
