package com.anyex.exchange.binance.future;

import com.anyex.exchange.binance.future.exception.BinanceApiException;

/**
 * The error handler for the subscription.
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

  void onError(BinanceApiException exception);
}
