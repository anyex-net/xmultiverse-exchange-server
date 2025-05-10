package com.anyex.exchange.binance.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ContingencyType {
    OCO
}
