package com.anyex.netease.api;

import com.anyex.apps.utils.HttpUtils;
import com.anyex.netease.config.NeteaseConfig;

import java.util.Map;

public class NeteaseApi {
    static
    {

    }
    protected static String post(String url,Map<String, String> map)
    {
        String ret = HttpUtils.post(HttpUtils.getHttpClient(),url,map,"utf-8", NeteaseConfig.getHeadder());
        return ret;
    }

}
