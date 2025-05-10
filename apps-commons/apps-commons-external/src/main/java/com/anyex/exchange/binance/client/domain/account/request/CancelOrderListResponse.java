package com.anyex.exchange.binance.client.domain.account.request;

import com.anyex.exchange.binance.client.domain.account.NewOCOResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelOrderListResponse extends NewOCOResponse {

}
