package com.anyex.exchange.contract.api;

import com.anyex.apps.utils.HttpUtils;
import org.apache.http.client.config.RequestConfig;

import java.util.Map;

public class ContractApi {

    public static RequestConfig requestConfig = null;

    static
    {
        requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(40000)
            .setSocketTimeout(120000)
            .setConnectTimeout(40000)
            .build();
    }

    protected static String post(String url, Map<String, String> map)
    {
        String ret = HttpUtils.post(HttpUtils.getHttpClient(), url, map,"utf-8");
        return ret;
    }

    protected static String postWithJSON(String url, String json)
    {

        String ret = HttpUtils.postWithJSON(HttpUtils.getHttpClient(),requestConfig, url, json);
        return ret;
    }
}
