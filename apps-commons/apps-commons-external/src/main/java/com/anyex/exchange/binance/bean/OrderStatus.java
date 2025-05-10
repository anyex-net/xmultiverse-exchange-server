package com.anyex.exchange.binance.bean;

/**
 * Status of a submitted order.
 */
public enum OrderStatus {
  NEW,
  PARTIALLY_FILLED,
  FILLED,
  CANCELED,
  PENDING_CANCEL,
  REJECTED,
  EXPIRED
}
