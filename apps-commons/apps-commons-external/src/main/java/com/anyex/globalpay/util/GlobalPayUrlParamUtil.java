package com.anyex.globalpay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class GlobalPayUrlParamUtil {
    static {}

    public static Map<String,String> getParams(String path) throws UnsupportedEncodingException {
        String[] params = path.split("&");
        Map<String, String> paramMap = new HashMap<>();

        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], "UTF-8");
            String value = URLDecoder.decode(keyValue[1], "UTF-8");
            paramMap.put(key, value);
        }
        return paramMap;
    }

    public static void test(String[] args) throws Exception {
        System.out.println(getParams("amount=10000&accountName=xiaochen&mchOrderNo=1665656198266&errMsg=Invalid+Account&sign=FDC40EC61E0A6C9EE9F2F1834CAC785D&transferDesc=%E8%BD%AC%E8%B4%A6%E6%B5%8B%E8%AF%95&reqTime=1665656203514&transferId=T1580502771496767490&createdAt=1665656199718&errCode=BAPI000309&accountNo=15175657915&appId=6254ec3cbd519d4a17d2b64f&currency=NGN&state=2&mchNo=M1649225437"));
    }

}
