package com.anyex.exchange.binance.future.impl;

import okhttp3.Request;

public class RestApiRequest<T> {

  public Request request;
  RestApiJsonParser<T> jsonParser;
}
