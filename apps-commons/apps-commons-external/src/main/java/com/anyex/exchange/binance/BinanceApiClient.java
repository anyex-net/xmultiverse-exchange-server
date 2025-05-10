package com.anyex.exchange.binance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.HttpUtils;
import com.anyex.apps.utils.PropertiesUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.exchange.binance.bean.account.*;
import com.anyex.exchange.binance.bean.lending.*;
import com.anyex.exchange.binance.bean.market.TickerPrice;
import org.apache.commons.collections.MapUtils;
import org.apache.http.client.HttpClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class BinanceApiClient
{

    static HttpClient client ;
    static BinanceApiClient apiClient ;
    static PropertiesUtils propertiesUtils = new PropertiesUtils("binance.properties");
    static String baseUrl = propertiesUtils.getProperty("binance.api.base.url");
    static String fapiUrl = propertiesUtils.getProperty("binance.fapi.base.url");

    static String dapiUrl = propertiesUtils.getProperty("binance.dapi.base.url");

    static String siteUrl = propertiesUtils.getProperty("binance.assetinfo.api.base.url");
    static {
        client = HttpUtils.getHttpClient();
        apiClient = new BinanceApiClient();
    }

    public static JSONArray getAllCurrency(String  apiKey,String  apiSecret)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/capital/config/getall?"+path,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }


    public static Account getAccount(String  apiKey, String  apiSecret, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/account?"+path,headerMap,null,null);
        Account account = JSONObject.parseObject(jsonStr, Account.class);
        return account;
    }

    public static JSONObject getSpotExchangeInfo(String symbol)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String url = StringUtils.isBlank(symbol)?baseUrl+"/api/v3/exchangeInfo":baseUrl+"/api/v3/exchangeInfo?symbol="+symbol;
        String jsonStr = ApiClient.httpGetWithJSON(client,url,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONArray getSpotFeeRate(String  apiKey,String  apiSecret,String symbol)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/asset/tradeFee?"+path,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONObject spotCancelAllOrder(String  apiKey,String  apiSecret,String symbol)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,baseUrl+"/api/v3/openOrders?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject spotCancelOrder(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,baseUrl+"/api/v3/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject spotOrderInfo(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject spotPlaceOrder(String  apiKey,String  apiSecret,Map<String,String> params)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if (MapUtils.isNotEmpty(params))
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                str.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path= str.toString()+"&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v3/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject marginPlaceOrder(String  apiKey,String  apiSecret,Map<String,String> params)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if (MapUtils.isNotEmpty(params))
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                str.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path= str.toString()+"&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/margin/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject marginOrderInfo(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/margin/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject marginCancelOrder(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,baseUrl+"/sapi/v1/margin/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getSpotDepth(String symbol,String limit)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path = "symbol="+symbol.toUpperCase()+"&limit="+limit;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/depth?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONArray getSpotAllTicker()
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/ticker/price",headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }
    public static JSONArray getSpotTickers(String symbols)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/ticker/price?symbols="+symbols,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONArray getCoinContractAllTicker()
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/ticker/price",headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONArray getUsdtContractTicker(String symbol)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,fapiUrl+"/fapi/v1/ticker/price?symbol="+symbol,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONArray getCoinContractTicker(String symbol)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/ticker/price?symbol="+symbol,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONArray getUsdtContractAllTicker()
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,fapiUrl+"/fapi/v1/ticker/price",headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    /**
     * 万向划转
     * @param apiKey
     * @param apiSecret
     * @param type
     * MAIN_UMFUTURE 现货钱包转向U本位合约钱包
     * MAIN_CMFUTURE 现货钱包转向币本位合约钱包
     * MAIN_MARGIN 现货钱包转向杠杆全仓钱包
     * MAIN_MINING 现货钱包转向矿池钱包
     * UMFUTURE_MAIN U本位合约钱包转向现货钱包
     * UMFUTURE_MARGIN U本位合约钱包转向杠杆全仓钱包
     * CMFUTURE_MAIN 币本位合约钱包转向现货钱包
     * MARGIN_MAIN 杠杆全仓钱包转向现货钱包
     * MARGIN_UMFUTURE 杠杆全仓钱包转向U本位合约钱包
     * MINING_MAIN 矿池钱包转向现货钱包
     * MINING_UMFUTURE 矿池钱包转向U本位合约钱包
     * MARGIN_CMFUTURE 杠杆全仓钱包转向币本位合约钱包
     * CMFUTURE_MARGIN 币本位合约钱包转向杠杆全仓钱包
     * MARGIN_MINING 杠杆全仓钱包转向矿池钱包
     * MINING_MARGIN 矿池钱包转向杠杆全仓钱包
     * ISOLATEDMARGIN_MARGIN 杠杆逐仓钱包转向杠杆全仓钱包
     * MARGIN_ISOLATEDMARGIN 杠杆全仓钱包转向杠杆逐仓钱包
     * ISOLATEDMARGIN_ISOLATEDMARGIN 杠杆逐仓钱包转向杠杆逐仓钱包
     * MAIN_FUNDING 现货钱包转向资金钱包
     * FUNDING_MAIN 资金钱包转向现货钱包
     * FUNDING_UMFUTURE 资金钱包转向U本位合约钱包
     * UMFUTURE_FUNDING U本位合约钱包转向资金钱包
     * MARGIN_FUNDING 杠杆全仓钱包转向资金钱包
     * FUNDING_MARGIN 资金钱包转向杠杆全仓钱包
     * FUNDING_CMFUTURE 资金钱包转向币本位合约钱包
     * CMFUTURE_FUNDING 币本位合约钱包转向资金钱包
     *
     * MAIN_CMFUTURE 现货钱包转向币本位合约钱包
     * MAIN_UMFUTURE 现货钱包转向U本位合约钱包
     * CMFUTURE_MAIN 币本位合约钱包转向现货钱包
     * UMFUTURE_MAIN U本位合约钱包转向现货钱包
     * @param asset
     * @param amount
     * @param fromSymbol
     * @param toSymbol
     * @return {"tranId":82684336377}
     */
    public static JSONObject transfer(String  apiKey,String  apiSecret,String type,String asset,BigDecimal amount,String fromSymbol,String toSymbol)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        StringBuffer path = new StringBuffer("recvWindow="+recvWindow+"&timestamp="+timestamp);
        path.append("&type=").append(type);
        path.append("&asset=").append(asset);
        path.append("&amount=").append(amount);
        if(StringUtils.isNotBlank(fromSymbol)) {
            path.append("&fromSymbol=").append(fromSymbol);
        }
        if(StringUtils.isNotBlank(toSymbol)) {
            path.append("&toSymbol=").append(toSymbol);
        }
        String sign = ApiClient.sign(path.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path.append("&signature="+sign);
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/asset/transfer?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static MarginAccount getMarginAccount(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/margin/account?"+path,headerMap,null,null);
        MarginAccount account = JSONObject.parseObject(jsonStr, MarginAccount.class);
        return account;
    }

    public static JSONObject getSwapAccount(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client, fapiUrl +"/fapi/v2/account?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getCoinContractAccount(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/account?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONArray getCoinContractBalance(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/balance?"+path,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONObject getCoinContractExchangeInfo()
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/exchangeInfo",headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getUsdtContractExchangeInfo()
    {
        Map<String, Object> headerMap = new HashMap<>();
        String jsonStr = ApiClient.httpGetWithJSON(client,fapiUrl+"/fapi/v1/exchangeInfo",headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONArray getCoinContractLastTrade(String symbol,String limit)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path = "symbol="+symbol+"&limit="+limit;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/trades?"+path,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONObject getCoinContractDepth(String symbol,String limit)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path = "symbol="+symbol+"&limit="+limit;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/depth?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getUsdtContractDepth(String symbol,String limit)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path = "symbol="+symbol+"&limit="+limit;
        String jsonStr = ApiClient.httpGetWithJSON(client,fapiUrl+"/fapi/v1/depth?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONArray getCoinContractLastPrice(String symbol,String pair)
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path = "symbol="+symbol+"&pair="+pair;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/ticker/price?"+path,headerMap,null,null);
        return (JSONArray) JSON.parse(jsonStr);
    }

    public static JSONObject getCoinContractCommissionRate(String  apiKey,String  apiSecret,String symbol,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/commissionRate?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject coinContractCancelAllOrder(String  apiKey,String  apiSecret,String symbol,Long recvWindow,Long timestamp)
    {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,dapiUrl+"/dapi/v1/allOpenOrders?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject coinContractCancelOrder(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId,Long recvWindow,Long timestamp)
    {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,dapiUrl+"/dapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getCoinContractOrderInfo(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject coinContractPlaceOrder(String  apiKey,String  apiSecret,Map<String,String> params)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if (MapUtils.isNotEmpty(params))
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                str.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path = str.toString();
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,dapiUrl+"/dapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject coinContracPositionSide(String  apiKey,String  apiSecret,boolean dualSidePosition)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp+"&dualSidePosition="+(dualSidePosition?"true":"false");
        StringBuffer str = new StringBuffer(path);
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path = str.toString();
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,dapiUrl+"/dapi/v1/positionSide/dual?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject coinContracPositionLever(String  apiKey,String  apiSecret,String symbol,int leverage)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp+"&symbol="+symbol+"&leverage="+leverage;
        StringBuffer str = new StringBuffer(path);
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path = str.toString();
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,dapiUrl+"/dapi/v1/leverage?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject usdtContractPlaceOrder(String  apiKey,String  apiSecret,Map<String,String> params)
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if (MapUtils.isNotEmpty(params))
        {
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                str.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        String sign = ApiClient.sign(str.toString(),apiSecret);
        headerMap.put("api-signature",sign);
        path = str.toString();
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client, fapiUrl +"/fapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }


    public static JSONObject usdtContractCancelOrder(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId,Long recvWindow,Long timestamp)
    {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpDeleteWithJSON(client,fapiUrl+"/fapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getUsdtContractOrderInfo(String  apiKey,String  apiSecret,String symbol,Long orderId,String origClientOrderId,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+symbol+"&recvWindow="+recvWindow+"&timestamp="+timestamp;
        if(StringUtils.isNotBlank(origClientOrderId))
        {
            path += "&origClientOrderId="+origClientOrderId;
        }
        if(orderId!=null)
        {
            path += "&orderId="+orderId;
        }
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,fapiUrl+"/fapi/v1/order?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getUsdtContractAccount(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client, fapiUrl +"/fapi/v2/account?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static JSONObject getIsolatedMarginAccount(String  apiKey,String  apiSecret,Long recvWindow,Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/margin/isolated/account?"+path,headerMap,null,null);
        return (JSONObject) JSON.parse(jsonStr);
    }

    public static WithdrawResult withdraw(String  apiKey,String  apiSecret,String asset, String address, String amount, String name, String addressTag,
                                   Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "asset="+asset
                +"&address="+address
                +"&amount="+amount
                +"&name="+name
                +"&addressTag="+addressTag
                +"&recvWindow="+recvWindow
                +"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/capital/withdraw/apply?"+path,headerMap,null,null);
        WithdrawResult withdrawResult = JSONObject.parseObject(jsonStr, WithdrawResult.class);
        return withdrawResult;
    }

    public static NewOrderResponse newOrder(String  apiKey,String  apiSecret, NewOrder order) throws BusinessException
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "symbol="+order.getSymbol()
                +"&side="+order.getSide().name()
                +"&type="+order.getType().name()
                +"&timeInForce="+order.getTimeInForce().name()
                +"&quantity="+order.getQuantity()
                +"&price="+order.getPrice()
                +"&recvWindow="+order.getRecvWindow()
                +"&timestamp="+order.getTimestamp();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v3/order?"+path,headerMap,null,null);
        NewOrderResponse newOrderResponse = JSONObject.parseObject(jsonStr, NewOrderResponse.class);
        if(newOrderResponse.getCode() != null)
        {
            throw new BusinessException(jsonStr);
        }
        return newOrderResponse;
    }

    public static List<Insurance> insurance() throws BusinessException
    {
        String jsonStr = ApiClient.httpPostWithJSON(client,siteUrl+"/gateway-api/v1/public/future/common/insuranceFundBalanceLogs?asset=USDT&page=1&rows=14&symbol=BTCUSDT",null,null,null);
        List<Insurance> list = JSONObject.parseObject(jsonStr).getJSONArray("data").toJavaList(Insurance.class);
        return list;
    }


    /**
     * 现货 最新成交价
     * @return
     * @throws BusinessException
     */
    public static List<TickerPrice> tickerPrice() throws BusinessException
    {
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/ticker/price",null,null,null);
        JSONArray array = (JSONArray) JSON.parse(jsonStr);
        List<TickerPrice> list = array.toJavaList(TickerPrice.class);
        return list;
    }

    public static String kline(String symbol,String interval,Long timeStart) throws BusinessException
    {
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/api/v3/klines?symbol="+symbol+"&interval="+interval+"&startTime="+timeStart,null,null,null);
        return jsonStr;
    }


    /**
     * 获取活期产品列表 (USER_DATA)
     * GET /sapi/v1/lending/daily/product/list (HMAC SHA256)
     *
     * 名称	类型	是否必需	描述
     * status	ENUM	NO	"ALL", "SUBSCRIBABLE", "UNSUBSCRIBABLE"; default "ALL"
     * featured	STRING	NO	“ALL”, "TRUE"; default "ALL"
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     *
     * @param apiKey
     * @param apiSecret
     * @param status
     * @param featured
     * @param recvWindow
     * @param timestamp
     * @return
     */
    public static List<Product> lendingDailyProduct(String  apiKey, String  apiSecret, String status, String featured, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if(StringUtils.isNotBlank(status))
        {
            str.append("&status=").append(status);
        }
        if(StringUtils.isNotBlank(featured))
        {
            str.append("&featured=").append(featured);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/daily/product/list?"+path,headerMap,null,null);
        List<Product> list = JSONObject.parseArray(jsonStr, Product.class);
        return list;
    }


    /**
     * 获取用户当日剩余活期可申购余额 (USER_DATA)
     * 响应:
     *
     * {
     *     "asset": "BUSD",
     *     "leftQuota': "50000.00000000"
     * }
     * GET /sapi/v1/lending/daily/userLeftQuota (HMAC SHA256)
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * productId	STRING	YES
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */

    public static UserLeftQuota lendingDailyUserLeftQuota(String  apiKey, String  apiSecret, String productId, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if(StringUtils.isNotBlank(productId))
        {
            str.append("&productId=").append(productId);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/daily/userLeftQuota?"+path,headerMap,null,null);
        UserLeftQuota list = JSONObject.parseObject(jsonStr, UserLeftQuota.class);
        return list;
    }

    /**
     * 申购活期产品 (USER_DATA)
     * 响应:
     *
     * {
     *     "purchaseId': 40607
     * }
     * POST /sapi/v1/lending/daily/purchase (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * productId	STRING	YES
     * amount	DECIMAL	YES
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static JSONObject lendingDailyPurchase(String  apiKey, String  apiSecret, String productId, BigDecimal amount,Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&productId=").append(productId);
        str.append("&amount=").append(amount.toPlainString());
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/lending/daily/purchase?"+path,headerMap,null,null);
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 获取用户当日活期可赎回余额 (USER_DATA)
     * 响应:
     *
     * {
     *     "asset": "USDT",
     *     "dailyQuota": "10000000.00000000",
     *     "leftQuota": "0.00000000',
     *     "minRedemptionAmount': "0.10000000"
     * }
     * GET /sapi/v1/lending/daily/userRedemptionQuota (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * productId	STRING	YES
     * type	ENUM	YES	"FAST", “NORMAL“
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static UserRedemptionQuota lendingDailyUserRedemptionQuota(String  apiKey, String  apiSecret, String productId,String type, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&productId=").append(productId);
        str.append("&type=").append(type);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/daily/userRedemptionQuota?"+path,headerMap,null,null);
        UserRedemptionQuota list = JSONObject.parseObject(jsonStr, UserRedemptionQuota.class);
        return list;
    }

    /**
     * 赎回活期产品 (USER_DATA)
     * 响应:
     *
     * {}
     * POST /sapi/v1/lending/daily/redeem (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * productId	STRING	YES
     * amount	DECIMAL	YES
     * type	ENUM	YES	"FAST", “NORMAL“
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static String lendingDailyRedeem(String  apiKey, String  apiSecret, String productId,BigDecimal amount,String type, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&productId=").append(productId);
        str.append("&type=").append(type);
        str.append("&amount=").append(amount.toPlainString());
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/lending/daily/redeem?"+path,headerMap,null,null);
        return jsonStr;
    }

    /**
     * 用户活期产品持仓 (USER_DATA)
     * 响应:
     *
     * [
     *     {
     *         "annualInterestRate": "0.02600000",
     *         "asset": "USDT",
     *         "avgAnnualInterestRate": "0.02599895",
     *         "canRedeem": True,
     *         "dailyInterestRate": "0.00007123",
     *         "freeAmount": "75.46000000",
     *         "freezeAmount": "0.00000000",
     *         "lockedAmount": "0.00000000",
     *         "productId": "USDT001",
     *         "productName": "USDT",
     *         "redeemingAmount": "0.00000000",
     *         "totalAmount": "75.46000000",
     *         "totalInterest": "0.22759183"
     *     }
     * ]
     * GET /sapi/v1/lending/daily/token/position (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * asset	STRING	YES
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */

    public static List<TokenPostion> lendingDailyTokenPosition(String  apiKey, String  apiSecret, String asset, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&asset=").append(asset);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/daily/token/position?"+path,headerMap,null,null);
        List<TokenPostion> list = JSONObject.parseArray(jsonStr, TokenPostion.class);
        return list;
    }

    /**
     * 币安宝账户信息 (USER_DATA)
     * 响应:
     *
     * {
     *     "positionAmountVos": [
     *         {
     *             "amount": "75.46000000",
     *             "amountInBTC": "0.01044819",
     *             "amountInUSDT": "75.46000000",
     *             "asset": "USDT"
     *         },
     *         {
     *             "amount": "1.67072036",
     *             "amountInBTC": "0.00023163",
     *             "amountInUSDT": "1.67289230",
     *             "asset": "BUSD"
     *         }
     *     ],
     *     "totalAmountInBTC": "0.01067982",
     *     "totalAmountInUSDT": "77.13289230",
     *     "totalFixedAmountInBTC": "0.00000000",
     *     "totalFixedAmountInUSDT": "0.00000000",
     *     "totalFlexibleInBTC": "0.01067982",
     *     "totalFlexibleInUSDT": "77.13289230"
     *  }
     * GET /sapi/v1/lending/union/account (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */

    public static UnoinAccount lendingUnionAccount(String  apiKey, String  apiSecret, Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/union/account?"+path,headerMap,null,null);
        UnoinAccount list = JSONObject.parseObject(jsonStr, UnoinAccount.class);
        return list;
    }

    /**
     * 获取申购记录 (USER_DATA)
     * 响应:
     *
     * 活期产品
     *
     * [
     *     {
     *         "amount": "100.00000000",
     *         "asset": "USDT",
     *         "createTime": 1575018510000,
     *         "lendingType": "DAILY",
     *         "productName": "USDT",
     *         "purchaseId": 26055,
     *         "status": "SUCCESS"
     *     }
     * ]
     * 定期产品
     *
     * [
     *     {
     *         "amount": "100.00000000",
     *         "asset": "USDT",
     *         "createTime": 1575018453000,
     *         "lendingType": "REGULAR",
     *         "lot": 1,
     *         "productName": "【Special】USDT 7D (8%)",
     *         "purchaseId": 36857,
     *         "status": "SUCCESS"
     *     }
     * ]
     * GET /sapi/v1/lending/union/purchaseRecord (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * lendingType	ENUM	YES	"DAILY" 表示活期, "REGULAR" 表示定期
     * asset	STRING	NO
     * startTime	LONG	NO
     * endTime	LONG	NO
     * current	LONG	NO	Currently querying page. Start from 1. Default:1
     * size	LONG	NO	Default:10, Max:100
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static List<PurchaseRecord> lendingUnionPurchaseRecord(String  apiKey, String  apiSecret, String lendingType, String asset,
                                                                       Long  startTime , Long  endTime , Long  current , Long  size ,
                                                                       Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&lendingType=").append(lendingType);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(startTime != null)
        {
            str.append("&startTime=").append(startTime);
        }
        if(endTime != null)
        {
            str.append("&endTime=").append(endTime);
        }
        if(current != null)
        {
            str.append("&current=").append(current);
        }
        if(size != null)
        {
            str.append("&size=").append(size);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/union/purchaseRecord?"+path,headerMap,null,null);
        List<PurchaseRecord> list = JSONObject.parseArray(jsonStr, PurchaseRecord.class);
        return list;
    }

    /**
     * 获取赎回记录 (USER_DATA)
     * 响应:
     *
     * 活期产品
     *
     * [
     *     {
     *         "amount": "10.54000000",
     *         "asset": "USDT",
     *         "createTime": 1577257222000,
     *         "principal": "10.54000000",
     *         "projectId": "USDT001",
     *         "projectName": "USDT",
     *         "status": "PAID",
     *         "type": "FAST"
     *      }
     * ]
     * 定期产品
     *
     * [
     *     {
     *         "amount": "0.07070000",
     *         "asset": "USDT",
     *         "createTime": 1566200161000,
     *         "interest": "0.00070000",
     *         "principal": "0.07000000",
     *         "projectId": "test06",
     *         "projectName": "USDT 1 day (10% anniualized)",
     *         "startTime": 1566198000000,
     *         "status": "PAID"
     *      }
     * ]
     * GET /sapi/v1/lending/union/redemptionRecord (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * lendingType	ENUM	YES	"DAILY" 表示活期, "REGULAR" 表示定期
     * asset	STRING	NO
     * startTime	LONG	NO
     * endTime	LONG	NO
     * current	LONG	NO	Currently querying page. Start from 1. Default:1
     * size	LONG	NO	Default:10, Max:100
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static List<RedemptionRecord> lendingUnionRedemptionRecord(String  apiKey, String  apiSecret, String lendingType, String asset,
                                                                           Long  startTime , Long  endTime , Long  current , Long  size ,
                                                                           Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&lendingType=").append(lendingType);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(startTime != null)
        {
            str.append("&startTime=").append(startTime);
        }
        if(endTime != null)
        {
            str.append("&endTime=").append(endTime);
        }
        if(current != null)
        {
            str.append("&current=").append(current);
        }
        if(size != null)
        {
            str.append("&size=").append(size);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/union/redemptionRecord?"+path,headerMap,null,null);
        List<RedemptionRecord> list = JSONObject.parseArray(jsonStr, RedemptionRecord.class);
        return list;
    }

    /**
     * 获取利息历史 (USER_DATA)
     * 响应:
     *
     * [
     *     {
     *         "asset": "BUSD",
     *         "interest": "0.00006408",
     *         "lendingType": "DAILY",
     *         "productName": "BUSD",
     *         "time": 1577233578000},
     *     {
     *         "asset": "USDT",
     *         "interest": "0.00687654",
     *         "lendingType": "DAILY",
     *         "productName": "USDT",
     *         "time": 1577233562000
     *     }
     * ]
     * GET /sapi/v1/lending/union/interestHistory (HMAC SHA256)
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * lendingType	ENUM	YES	"DAILY" 表示活期, "REGULAR" 表示定期
     * asset	STRING	NO
     * startTime	LONG	NO
     * endTime	LONG	NO
     * current	LONG	NO	Currently querying page. Start from 1. Default:1
     * size	LONG	NO	Default:10, Max:100
     * recvWindow	LONG	NO	The value cannot be greater than 60000
     * timestamp	LONG	YES
     */
    public static List<InterestHistoryRecord> lendingUnionInterestHistoryRecord(String  apiKey, String  apiSecret, String lendingType, String asset,
                                                                                     Long  startTime , Long  endTime , Long  current , Long  size ,
                                                                                     Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        str.append("&lendingType=").append(lendingType);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(startTime != null)
        {
            str.append("&startTime=").append(startTime);
        }
        if(endTime != null)
        {
            str.append("&endTime=").append(endTime);
        }
        if(current != null)
        {
            str.append("&current=").append(current);
        }
        if(size != null)
        {
            str.append("&size=").append(size);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/union/interestHistory?"+path,headerMap,null,null);
        List<InterestHistoryRecord> list = JSONObject.parseArray(jsonStr, InterestHistoryRecord.class);
        return list;
    }

    public static void print(JSONObject object)
    {
        String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        System.out.println(pretty);
        System.out.println("==========================================================================================");
    }


    public static JSONArray lendingProjectPostionList(String  apiKey, String  apiSecret, String asset,
                                                                  String  status,
                                                                  Long recvWindow, Long timestamp)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(status != null)
        {
            str.append("&status=").append(status);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/project/position/list?"+path,headerMap,null,null);
        return (JSONArray)JSON.parse(jsonStr);
    }

    public static void print(JSONArray array)
    {
        String pretty = JSON.toJSONString(array, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        System.out.println(pretty);
        System.out.println("==========================================================================================");
    }

    public static void checkResult(JSONObject object) throws BusinessException
    {
        if(object.get("code") != null)
        {
            throw new BusinessException(object.toJSONString());
        }
    }

    /**
     * 划转
     * 响应:
     * {
     *     "tranId": 100000001    // 划转 ID
     * }
     * POST https://api.binance.com/sapi/v1/futures/transfer (HMAC SHA256) * 请注意：这里不适用dapi base url
     *
     * 执行现货账户与合约账户之间的划转
     * Weight: 1
     *
     * Parameters:
     * 名称	类型	是否必需	描述
     * asset	STRING	YES	The asset being transferred, e.g., BTC
     * amount	DECIMAL	YES	The amount to be transferred
     * type	INT	YES	3: 现货账户向交割合约账户划转 4: 交割合约账户向现货账户划转
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     */
    public static String futuresTransfer(String  apiKey, String  apiSecret, String asset,BigDecimal amount,int type) throws BusinessException
    {

        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(amount != null)
        {
            str.append("&amount=").append(amount);
        }
        str.append("&type=").append(type);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/futures/transfer?"+path,headerMap,null,null);
        JSONObject obj = (JSONObject)JSONObject.parse(jsonStr);
        checkResult(obj);
        return obj.getString("tranId");
    }

    /**
     * 更改持仓模式（TRADE）
     * 响应:
     *
     * {
     *     "code": 200,
     *     "msg": "success"
     * }
     * POST /dapi/v1/positionSide/dual (HMAC SHA256)
     *
     * 变换用户在 所有symbol 合约上的持仓模式：双向持仓或单向持仓。
     *
     * 权重: 1
     *
     * 参数:
     *
     * 名称	类型	是否必需	描述
     * dualSidePosition	STRING	YES	"true": 双向持仓模式；"false": 单向持仓模式
     * recvWindow	LONG	NO
     * timestamp	LONG	YES
     */
    public static String futuresPositionSideUpdate(String  apiKey, String  apiSecret, String dualSidePosition) throws BusinessException
    {

        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        if(StringUtils.isNotBlank(dualSidePosition))
        {
            str.append("&dualSidePosition=").append(dualSidePosition);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,dapiUrl+"/dapi/v1/positionSide/dual?"+path,headerMap,null,null);
        JSONObject obj = (JSONObject)JSONObject.parse(jsonStr);
        checkResult(obj);
        return "ok";
    }

    /** "true": 双向持仓模式；"false": 单向持仓模式 */
    public static String futuresPositionSide(String  apiKey, String  apiSecret) throws BusinessException
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/positionSide/dual?"+path,headerMap,null,null);
        JSONObject obj = (JSONObject)JSONObject.parse(jsonStr);
        checkResult(obj);
        return obj.getString("dualSidePosition");
    }

    /**
     * 账户余额 (USER_DATA)
     */
    public static JSONArray futuresBalance(String  apiKey, String  apiSecret) throws BusinessException
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/balance?"+path,headerMap,null,null);
        JSONArray obj = (JSONArray)JSONArray.parse(jsonStr);
        return obj;
    }

    /**
     * 账户
     */
    public static JSONObject futuresAccount(String  apiKey, String  apiSecret) throws BusinessException
    {
        Long recvWindow = 60000L;
        Long timestamp = System.currentTimeMillis();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow="+recvWindow+"&timestamp="+timestamp;
        StringBuffer str = new StringBuffer(path);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/account?"+path,headerMap,null,null);
        JSONObject obj = (JSONObject)JSONObject.parse(jsonStr);
        return obj;
    }


    public static BigDecimal futuresLastPrice(String  symbol) throws BusinessException
    {
        Map<String, Object> headerMap = new HashMap<>();
        String path="symbol="+symbol;
        String jsonStr = ApiClient.httpGetWithJSON(client,dapiUrl+"/dapi/v1/ticker/price?"+path,headerMap,null,null);
        JSONObject obj = ((JSONArray)JSONArray.parse(jsonStr)).getJSONObject(0);
        return obj.getBigDecimal("price");
    }

    // 查询定期产品
    public static JSONArray lendingProjectList(String  apiKey, String  apiSecret, String asset,String type,
                                                      String  status)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&type=").append(type);
        str.append("&size=100");
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(status != null)
        {
            str.append("&status=").append(status);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/project/list?"+path,headerMap,null,null);
        return (JSONArray)JSON.parse(jsonStr);
    }

    // 定期产品下单
    public static JSONObject lendingProjectPurchase(String  apiKey, String  apiSecret, String projectId, String lot)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&projectId=").append(projectId);
        str.append("&lot=").append(lot);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/lending/customizedFixed/purchase?"+path,headerMap,null,null);
        return JSONObject.parseObject(jsonStr);
    }

    // 定期产品申购记录
    public static JSONArray lendingProjectRecord(String  apiKey, String  apiSecret, String projectId)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&projectId=").append(projectId);
        str.append("&lendingType=CUSTOMIZED_FIXED");
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/union/purchaseRecord?"+path,headerMap,null,null);
        return JSONArray.parseArray(jsonStr);
    }

    // 定期产品持仓
    public static JSONArray lendingProjectPosition(String  apiKey, String  apiSecret, String asset,int page,int pageSize)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&asset=").append(asset);
        str.append("&size=").append(pageSize);
        str.append("&current=").append(page);
        str.append("&status=HOLDING");
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/lending/project/position/list?"+path,headerMap,null,null);
        return JSONArray.parseArray(jsonStr);
    }

    // --------------------------------------staking 接口--------------------------------

    // staking产品持仓
    public static JSONArray stakingProjectList(String  apiKey, String  apiSecret, String asset,int page,int pageSize)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&product=").append("STAKING");
        str.append("&size=").append(pageSize);
        str.append("&current=").append(page);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/staking/productList?"+path,headerMap,null,null);
        return JSONArray.parseArray(jsonStr);
    }

    // staking产品锁仓
    public static JSONObject stakingProjectPurchase(String  apiKey, String  apiSecret, String productId,BigDecimal amount)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&product=").append("STAKING");
        str.append("&productId=").append(productId);
        str.append("&amount=").append(amount);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/staking/purchase?"+path,headerMap,null,null);
        return JSONObject.parseObject(jsonStr);
    }

    // staking产品赎回
    public static JSONObject stakingProjectRedeem(String  apiKey, String  apiSecret,String positionId, String productId,BigDecimal amount)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&product=").append("STAKING");
        str.append("&productId=").append(productId);
        str.append("&positionId=").append(positionId);
        str.append("&amount=").append(amount);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/sapi/v1/staking/redeem?"+path,headerMap,null,null);
        return JSONObject.parseObject(jsonStr);
    }

    // staking产品持仓
    public static JSONArray stakingProjectPosition(String  apiKey, String  apiSecret,String productId, String asset,int page,int pageSize)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&product=").append("STAKING");
        str.append("&size=").append(pageSize);
        str.append("&current=").append(page);
        if(StringUtils.isNotBlank(asset))
        {
            str.append("&asset=").append(asset);
        }
        if(StringUtils.isNotBlank(productId))
        {
            str.append("&productId=").append(productId);
        }
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/staking/position?"+path,headerMap,null,null);
        return JSONArray.parseArray(jsonStr);
    }

    // staking剩余额度
    public static BigDecimal stakingPersonalLeftQuota(String  apiKey, String  apiSecret,String productId)
    {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-MBX-APIKEY",apiKey);
        String path = "recvWindow=60000&timestamp="+System.currentTimeMillis();
        StringBuffer str = new StringBuffer(path);
        str.append("&product=").append("STAKING");
        str.append("&productId=").append(productId);
        path = str.toString();
        String sign = ApiClient.sign(path,apiSecret);
        headerMap.put("api-signature",sign);
        path+="&signature="+sign;
        String jsonStr = ApiClient.httpGetWithJSON(client,baseUrl+"/sapi/v1/staking/personalLeftQuota?"+path,headerMap,null,null);
        return JSONObject.parseObject(jsonStr).getBigDecimal("leftPersonalQuota");
    }
}
