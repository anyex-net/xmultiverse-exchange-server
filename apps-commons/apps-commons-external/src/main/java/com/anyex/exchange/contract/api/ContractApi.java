package com.anyex.exchange.contract.api;

import com.anyex.apps.utils.HttpUtils;

import java.util.Map;

public class ContractApi {

    static
    {
    }

    protected static String post(String url, Map<String, String> map)
    {
        String ret = HttpUtils.post(HttpUtils.getHttpClient(), url, map,"utf-8");
        return ret;
    }

    protected static String postWithJSON(String url, String json)
    {
        String ret = HttpUtils.postWithJSON(HttpUtils.getHttpClient(), url, json);
        return ret;
    }
}
