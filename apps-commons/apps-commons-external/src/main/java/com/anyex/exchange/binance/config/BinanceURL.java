package com.anyex.exchange.binance.config;

import com.anyex.apps.utils.PropertiesUtils;

public class BinanceURL {

    public String API_BASE_URL;

    public String WS_API_BASE_URL;

    public String ASSET_INFO_API_BASE_URL;

    public boolean PROXY_IS_NEED ;

    public String  PROXY_HOST;

    public int     PROXY_PORT;


    public BinanceURL(String props) {
        PropertiesUtils propertiesUtils = new PropertiesUtils(props);
        API_BASE_URL = propertiesUtils.getProperty("binance.api.base.url");
        WS_API_BASE_URL = propertiesUtils.getProperty("binance.ws.api.base.url");
        ASSET_INFO_API_BASE_URL = propertiesUtils.getProperty("binance.assetinfo.api.base.url");
        PROXY_IS_NEED = propertiesUtils.getBoolean("binance.proxy.isneed");
        PROXY_HOST = propertiesUtils.getProperty("binance.proxy.host");
        PROXY_PORT = propertiesUtils.getInteger("binance.proxy.port");
    }
}
