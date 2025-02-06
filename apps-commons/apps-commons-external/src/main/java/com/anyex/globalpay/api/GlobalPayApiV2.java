package com.anyex.globalpay.api;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;


@Slf4j
public class GlobalPayApiV2 {

    protected static RestTemplate httpsRestTemplate = null;
    static {
        httpsRestTemplate = new RestTemplate();
    }

    public static void main(String[] args) {

        /*Map<String, Object> payInMap = new HashMap<>();
        payInMap.put("appId", GlobalPayConfig.appid);
        payInMap.put("mchOrderId", System.currentTimeMillis()+"");
        payInMap.put("amount", "100");
        payInMap.put("notifyUrl", "https://ckjqr.xiaomy.net/gate-pay/king/pay/in/callback");
        payInMap.put("returnUrl", "https://ckjqr.xiaomy.net/api/payment/globalpay/out/return");
        payInMap.put("customerName", "SHI ZHENLING"); //客户姓名
        payInMap.put("customerCert", "1234567890123"); //客户身份证
        payInMap.put("customerEmail", "1@1.com"); //客户邮箱
        payInMap.put("customerPhone", "0311234567"); //客户邮箱
        payInMap.put("accountProvider", "easypaisa");//渠道名称（easypaisa / jazzcash）
        payInMap.put("fillCheckoutFlag", "1"); // 是否填充收银台信息（1=是，0=否）
        payInMap.put("sign",com.anyex.globalpay.util.GlobalPayUtil.getSign(payInMap,GlobalPayConfig.key));
        // post(GlobalPayConfig.ROOT_IN,payInMap);

        Map<String, Object> payInQueryMap = new HashMap<>();
        payInQueryMap.put("appId", GlobalPayConfig.appid);
        payInQueryMap.put("orderId", "P1801520476837003266");
        payInQueryMap.put("sign",com.anyex.globalpay.util.GlobalPayUtil.getSign(payInQueryMap,GlobalPayConfig.key));
        // get(GlobalPayConfig.ROOT_IN_QUERY,payInQueryMap);

        Map<String, Object> payOutMap = new HashMap<>();
        payOutMap.put("appId", GlobalPayConfig.appid);
        payOutMap.put("mchOrderId", System.currentTimeMillis());
        payOutMap.put("amount", "100");
        payOutMap.put("notifyUrl", "https://ckjqr.xiaomy.net/gate-pay/king/pay/out/callback");
        payOutMap.put("accountNo", "3211234567");
        payOutMap.put("accountType", "WALLET");
        payOutMap.put("accountProvider", "jazzcash");
        payOutMap.put("customerName", "example");
        payOutMap.put("customerCert", "1712345678901");
        payOutMap.put("customerEmail", "example@test.com");
        payOutMap.put("customerPhone", "1111111111");
        payOutMap.put("customerIBAN", "PK5115115115115");
        payOutMap.put("sign",com.anyex.globalpay.util.GlobalPayUtil.getSign(payOutMap,GlobalPayConfig.key));
        // post(GlobalPayConfig.ROOT_OUT,payOutMap);

        Map<String, Object> payOutQueryMap = new HashMap<>();
        payOutQueryMap.put("appId", GlobalPayConfig.appid);
        payOutQueryMap.put("orderId", "P1801520476837003266");
        payOutQueryMap.put("sign",com.anyex.globalpay.util.GlobalPayUtil.getSign(payOutQueryMap,GlobalPayConfig.key));
        get(GlobalPayConfig.ROOT_OUT_QUERY,payOutQueryMap);
        // doPost(payOutQueryMap,GlobalPayConfig.ROOT_OUT_QUERY);*/
    }

    public static JSONObject get(String url,Map<String, Object> newMap) {
        try {
            log.info("GP-V2 URL : {}",url);
            url = url+"?"+convertToUrl(newMap);
            log.info("GP-V2 REQUEST :{} ",url);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            ResponseEntity<JSONObject> rsp = httpsRestTemplate.exchange(url, HttpMethod.GET,  new HttpEntity<>(null, headers), JSONObject.class);
            log.info("GP-V2 RESPONSE :{} ",rsp.getBody());
            return rsp.getBody();
        } catch (Exception e) {
            log.error("GP-V2 ERROR :{} ",e.getLocalizedMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static JSONObject post(String url,Map<String, Object> newMap) {
        try {
            log.info("GP-V2 URL : {}",url);
            log.info("REQUEST : {} ",JSONObject.toJSONString(newMap));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            HttpEntity<?> entity = new HttpEntity<>(newMap, headers);
            ResponseEntity<JSONObject> rsp = httpsRestTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
            log.info("GP-V2 RESPONSE :{} ",rsp.getBody());
            return rsp.getBody();
        } catch (Exception e) {
            log.error("GP-V2 ERROR :{} ",e.getLocalizedMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    protected static String convertToUrl(Map<String, Object> map) {
        StringBuilder urlBuilder = new StringBuilder();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            String value = (String)entry.getValue();
            urlBuilder.append(key).append("=").append(value);
            if (iterator.hasNext()) {
                urlBuilder.append("&");
            }
        }
        return urlBuilder.toString();
    }


}
