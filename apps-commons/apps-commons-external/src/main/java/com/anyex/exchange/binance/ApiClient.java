package com.anyex.exchange.binance;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.exchange.binance.security.HmacSHA256Signer;
import com.anyex.exchange.okex.enums.AlgorithmEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.NoRouteToHostException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 */
@Slf4j
public class ApiClient
{
    public static final RequestConfig defultConfig = RequestConfig.custom().setConnectionRequestTimeout(8000).setSocketTimeout(8000).setConnectTimeout(8000).build();

    private static boolean proxy = false;// 实际提交应为false
    private static String  proxyIp = "127.0.0.1";
    private static int     proxyPort = 7890;

    private static String redisKey = "binance:api:request:stop:status";

    private static String redisQuestKey = "binance:api:request:cnt";

    private static String redisOrderKey = "binance:api:order:cnt";

    public static Mac                 MAC;
    static
    {
        try
        {
            MAC = Mac.getInstance(AlgorithmEnum.HMAC_SHA256.algorithm());
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeErrorException(new Error("Can't get Mac's instance."));
        }
    }

    /**
     * 16进制加密
     * @param a
     * @return
     */
    protected static String byteArrayToHex(byte[] a)
    {
        int hn, ln, cx;
        String hexDigitChars = "0123456789abcdef";
        StringBuffer buf = new StringBuffer(a.length * 2);
        for (cx = 0; cx < a.length; cx++)
        {
            hn = ((int) (a[cx]) & 0x00ff) / 16;
            ln = ((int) (a[cx]) & 0x000f);
            buf.append(hexDigitChars.charAt(hn));
            buf.append(hexDigitChars.charAt(ln));
        }
        return buf.toString();
    }

    public static String sign(String queryString, String secretKey)
    {
        return HmacSHA256Signer.sign(queryString, secretKey);
    }

    public static String httpPostWithJSON(HttpClient client, String url, Map<String, Object> headerMap, JSONObject jsonParam, String charsetName) throws BusinessException
    {
//        checkLimit();
        try
        {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(defultConfig);
            if (MapUtils.isNotEmpty(headerMap))
            {
                for (Map.Entry<String, Object> entry : headerMap.entrySet())
                {
                    httpPost.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            String respContent = null;
            if (jsonParam != null && StringUtils.isNotEmpty(jsonParam.toJSONString()))
            {
                String params = jsonParam.toString();
                StringEntity entity = new StringEntity(params, "utf-8");
                if (StringUtils.isNotBlank(charsetName))
                {
                    entity.setContentEncoding(charsetName);
                }
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            if(proxy) {
                HttpHost proxy = new HttpHost(proxyIp, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setProxy(proxy)
                        .setConnectTimeout(100000)
                        .setSocketTimeout(100000)
                        .setConnectionRequestTimeout(60000)
                        .build();
                httpPost.setConfig(requestConfig);
            }
            HttpResponse resp = client.execute(httpPost);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200)
            {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            else
            {
                HttpEntity he = resp.getEntity();
                String error = EntityUtils.toString(he, "UTF-8");
                throw new BusinessException(error);
            }
//            checkResponse(resp);
            return respContent;
        }
        catch (BusinessException e)
        {
            throw e;
        }
        catch (NoRouteToHostException e)
        {
            throw new BusinessException("没有到主机的路由 (Host unreachable)");
        }
        catch (Exception e)
        {
            throw new BusinessException(e.getMessage());
        }
    }

    protected static void checkLimit() throws BusinessException
    {
        if (RedisUtils.getObject(redisKey) != null) {
            String status = (String) RedisUtils.getObject(redisKey);
            if (StringUtils.equalsIgnoreCase(status, "true")) {
                throw new BusinessException("币安API访问次数风控：[" + RedisUtils.getObject(redisQuestKey) + "," + RedisUtils.getObject(redisOrderKey) + "]");
            }
        }
    }
    protected static void checkResponse(HttpResponse resp)
    {
        String cnt = "0";
        if (resp.getHeaders("X-MBX-USED-WEIGHT-1M").length > 0) {
            cnt = resp.getHeaders("X-MBX-USED-WEIGHT-1M")[0].getValue();
            if (StringUtils.isBlank(cnt)) {
                cnt = "0";
            }
        }
        String ordercnt = "0";
        if (resp.getHeaders("X-MBX-ORDER-COUNT-1M").length > 0) {
            ordercnt = resp.getHeaders("X-MBX-ORDER-COUNT-1M")[0].getValue();
            if (StringUtils.isBlank(ordercnt)) {
                ordercnt = "0";
            }
        }

        String status = "";// (String)RedisUtils.getObject(redisKey);

        RedisUtils.putObject(redisQuestKey, Long.parseLong(cnt));

        RedisUtils.putObject(redisOrderKey, Long.parseLong(ordercnt));

        if (Long.parseLong(cnt) >= 2395 || Long.parseLong(ordercnt) >= 1199) {
            status = "true";
        } else {
            status = "false";
        }
        RedisUtils.putObject(redisKey, status, 60);
    }

    public static String httpGetWithJSON(HttpClient client, String url, Map<String, Object> headerMap, JSONObject jsonParam, String charsetName) throws BusinessException
    {
//        checkLimit();
        try
        {
            HttpGet httpPost = new HttpGet(url);
            if (MapUtils.isNotEmpty(headerMap))
            {
                for (Map.Entry<String, Object> entry : headerMap.entrySet())
                {
                    log.info("header key:{}, value:{}", entry.getKey(), entry.getValue().toString());
                    httpPost.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            String respContent = null;
            if (jsonParam != null && StringUtils.isNotEmpty(jsonParam.toJSONString()))
            {
                String params = jsonParam.toString();
                log.info("params:{}", params);
                StringEntity entity = new StringEntity(params, "utf-8");
                if (StringUtils.isNotBlank(charsetName))
                {
                    entity.setContentEncoding(charsetName);
                }
                entity.setContentType("application/json");
            }
            if(proxy) {
                HttpHost proxy = new HttpHost(proxyIp, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setProxy(proxy)
                        .setConnectTimeout(100000)
                        .setSocketTimeout(100000)
                        .setConnectionRequestTimeout(60000)
                        .build();
                httpPost.setConfig(requestConfig);
            }
            else {
                httpPost.setConfig(defultConfig);
            }
            HttpResponse resp = client.execute(httpPost);
//            checkResponse(resp);
            int statusCode = resp.getStatusLine().getStatusCode();
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
            log.info("respContent:{}", respContent);
            return respContent;
        }
        catch (BusinessException e)
        {
            log.error(e.getLocalizedMessage());
            throw e;
        }
        catch (Exception e)
        {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    public static String httpDeleteWithJSON(HttpClient client, String url, Map<String, String> headerMap, JSONObject jsonParam, String charsetName) throws BusinessException
    {
        checkLimit();
        /**
         * 没有现成的delete可以带json的，自己实现一个，参考HttpPost的实现
         */
        class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase
        {
            public static final String METHOD_NAME = "DELETE";

            @SuppressWarnings("unused")
            public HttpDeleteWithBody()
            {
            }

            @SuppressWarnings("unused")
            public HttpDeleteWithBody(URI uri)
            {
                setURI(uri);
            }

            public HttpDeleteWithBody(String uri)
            {
                setURI(URI.create(uri));
            }

            public String getMethod()
            {
                return METHOD_NAME;
            }
        }
        try
        {
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
            if (MapUtils.isNotEmpty(headerMap))
            {
                for (Map.Entry<String, String> entry : headerMap.entrySet())
                {
                    httpDelete.setHeader(entry.getKey(), entry.getValue());
                }
            }
            if(proxy) {
                HttpHost proxy = new HttpHost(proxyIp, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setProxy(proxy)
                        .setConnectTimeout(100000)
                        .setSocketTimeout(100000)
                        .setConnectionRequestTimeout(60000)
                        .build();
                httpDelete.setConfig(requestConfig);
            }
            else {
                httpDelete.setConfig(defultConfig);
            }
            String respContent = null;
            if(jsonParam != null)
            {
                String params = jsonParam.toString();
                StringEntity entity = new StringEntity(params, "utf-8");
                if (StringUtils.isNotBlank(charsetName))
                {
                    entity.setContentEncoding(charsetName);
                }
                entity.setContentType("application/json");
                httpDelete.setEntity(entity);
            }
            HttpResponse resp = client.execute(httpDelete);
            checkResponse(resp);
            int statusCode = resp.getStatusLine().getStatusCode();
            HttpEntity he = resp.getEntity();
            respContent = EntityUtils.toString(he, "UTF-8");
            return respContent;
        }
        catch (BusinessException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new BusinessException(e.getMessage());
        }
    }

    public static String httpPutWithJSON(HttpClient client, String url, Map<String, String> headerMap, JSONObject jsonParam, String charsetName) throws BusinessException
    {
        checkLimit();
        try
        {
            HttpPut httpPut = new HttpPut(url);
            if (MapUtils.isNotEmpty(headerMap))
            {
                for (Map.Entry<String, String> entry : headerMap.entrySet())
                {
                    httpPut.setHeader(entry.getKey(), entry.getValue());
                }
            }
            String respContent = null;
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            if(proxy) {
                HttpHost proxy = new HttpHost(proxyIp, proxyPort);
                RequestConfig requestConfig = RequestConfig.custom()
                        .setProxy(proxy)
                        .setConnectTimeout(100000)
                        .setSocketTimeout(100000)
                        .setConnectionRequestTimeout(60000)
                        .build();
                httpPut.setConfig(requestConfig);
            }
            else {
                httpPut.setConfig(defultConfig);
            }
            if (StringUtils.isNotBlank(charsetName))
            {
                entity.setContentEncoding(charsetName);
            }
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
            HttpResponse resp = client.execute(httpPut);
            checkResponse(resp);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200)
            {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he, "UTF-8");
            }
            else
            {
                HttpEntity he = resp.getEntity();
                String error = EntityUtils.toString(he, "UTF-8");
                throw new BusinessException("请求失败：" + error);
            }
            return respContent;
        }
        catch (BusinessException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new BusinessException(e.getMessage());
        }
    }


    public static String httpPostWithJSONSite(String url) throws BusinessException
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpGet = new HttpPost(url);
        if(proxy) {
            HttpHost proxy = new HttpHost(proxyIp, proxyPort);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(100000)
                    .setSocketTimeout(100000)
                    .setConnectionRequestTimeout(60000)
                    .build();
            httpGet.setConfig(requestConfig);
        }
        BigDecimal ret = BigDecimal.ZERO;
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String retStr = EntityUtils.toString(responseEntity);
                return retStr;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
