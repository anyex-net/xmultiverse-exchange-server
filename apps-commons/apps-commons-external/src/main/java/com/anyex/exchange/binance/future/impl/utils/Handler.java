package com.anyex.exchange.binance.future.impl.utils;

@FunctionalInterface
public interface Handler<T> {

  void handle(T t);
}
