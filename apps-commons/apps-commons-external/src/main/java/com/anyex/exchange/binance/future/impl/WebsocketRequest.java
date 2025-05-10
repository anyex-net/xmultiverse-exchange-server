package com.anyex.exchange.binance.future.impl;


import com.anyex.exchange.binance.future.SubscriptionErrorHandler;
import com.anyex.exchange.binance.future.SubscriptionListener;
import com.anyex.exchange.binance.future.impl.utils.Handler;

class WebsocketRequest<T> {

    WebsocketRequest(SubscriptionListener<T> listener, SubscriptionErrorHandler errorHandler) {
        this.updateCallback = listener;
        this.errorHandler = errorHandler;
    }

    String signatureVersion = "2";
    String name;
    Handler<WebSocketConnection> connectionHandler;
    Handler<WebSocketConnection> authHandler = null;
    final SubscriptionListener<T> updateCallback;
    RestApiJsonParser<T> jsonParser;
    final SubscriptionErrorHandler errorHandler;
}
