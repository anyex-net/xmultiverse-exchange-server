package com.anyex.exchange.binance.future.impl;


import com.anyex.exchange.binance.future.impl.utils.JsonWrapper;

@FunctionalInterface
public interface RestApiJsonParser<T> {

  T parseJson(JsonWrapper json);
}
