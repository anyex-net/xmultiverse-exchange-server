package com.anyex.wallet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.utils.HttpUtils;
import com.anyex.exchange.binance.ApiClient;
import com.anyex.exchange.binance.security.HmacSHA256Signer;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XMWalletApi {
    /**
     * 准备签名字符串，格式如下：
     *    ```
     *    HTTP Method + "\n" +
     *    Request Path + "\n" +
     *    Timestamp + "\n" +
     *    Request-Id + "\n" +
     *    Request Body
     *    ```
     */
    /**
     * ### 请求头示例
     *
     * ```
     * X-Timestamp: 1710907200
     * X-Signature: 7c8f9d2e1b3a4c5f6e7d8a9b0c1d2e3f4a5b6c7d8e9f0a1b2c3d4e5f6a7b8c9
     * X-Request-Id: req_202403201234567890
     * Content-Type: application/json
     * ```
     */
    static HttpClient client;
    static String baseUrl = "http://54.254.88.22:8855";
    static String api_id = "0010d110-20d8-11f0-8ac4-f66ba33253c4";
    static String api_secret = "HMmW7RbogT9eoTHLrawaTgOAVyN5ICmEqi-1Tpcv91o=";

    static {
        client = HttpUtils.getHttpClient();
    }

    public static String sign(String path, String secretKey)
    {
        return HmacSHA256Signer.sign(path, secretKey);
    }

    /**
     * 获取支持的链列表
     *
     * @return
     */
    public static JSONObject get_support_chain_list()
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        String signStr =    "POST"+ "\n" +
                            "/api/v1/get_support_chain_list" + "\n" +
                            timestamp/1000+ "\n" +
                            "req_"+timestamp + "\n" +
                            jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/get_support_chain_list", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 根据链名称获取该链支持的代币列表
     *
     * @param chainCode
     * @return
     */
    public static JSONObject get_coin_list_by_chain_code(String chainCode)
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        jsonParam.put("chain_code", chainCode);
        String signStr =    "POST"+ "\n" +
                "/api/v1/get_coin_list_by_chain_code" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/get_coin_list_by_chain_code", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 注册用户
     *
     * @return
     */
    public static JSONObject register_user()
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        String signStr =    "POST"+ "\n" +
                "/api/v1/register_user" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/register_user", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 获取钱包地址
     *
     * @param userNo
     * @param coinCode
     * @param chainCode
     * @return
     */
    public static JSONObject get_address(String userNo, String coinCode, String chainCode)
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        jsonParam.put("user_no", userNo);
        jsonParam.put("coin_code", coinCode);
        jsonParam.put("chain_code", chainCode);
        String signStr =    "POST"+ "\n" +
                "/api/v1/get_address" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/get_address", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 获取交易列表 tx_type的值：DEPOSIT传回充值列表，WITHDRAW传回提现列表，其他值传回所有列表
     *
     * @return
     */
    public static JSONObject get_tx_list(String txType)
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        jsonParam.put("tx_type", txType);
        String signStr =    "POST"+ "\n" +
                "/api/v1/get_tx_list" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/get_tx_list", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 发送交易
     *
     * @param userNo
     * @param coinCode
     * @param chainCode
     * @param amount
     * @param toAddress
     * @return
     */
    public static JSONObject create_transaction(String userNo, String coinCode, String chainCode, String amount, String toAddress)
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        jsonParam.put("user_no", userNo);
        jsonParam.put("coin_code", coinCode);
        jsonParam.put("chain_code", chainCode);
        jsonParam.put("amount", amount);
        jsonParam.put("to_address", toAddress);
        String signStr =    "POST"+ "\n" +
                "/api/v1/create_transaction" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/create_transaction", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    /**
     * 推送交易状态
     *
     * @param requestNo
     * @return
     */
    public static JSONObject push_tx_status(String requestNo)
    {
        Long timestamp = System.currentTimeMillis();
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("merchant_no", "52a99509-5e42-4dac-9876-f5a1518f8e8d");
        jsonParam.put("request_no", requestNo);
        String signStr =    "POST"+ "\n" +
                "/api/v1/push_tx_status" + "\n" +
                timestamp/1000+ "\n" +
                "req_"+timestamp + "\n" +
                jsonParam.toJSONString();
        log.info("signStr:{}", signStr);
        String sign = ApiClient.sign(signStr, api_secret);
        log.info("sign:{}", sign);
        //
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("X-Timestamp", timestamp/1000);
        headerMap.put("X-Signature", sign);
        headerMap.put("X-Request-Id", "req_"+timestamp);
        headerMap.put("Content-Type", "application/json");
        String jsonStr = ApiClient.httpPostWithJSON(client,baseUrl+"/api/v1/push_tx_status", headerMap, jsonParam,null);
        log.info("jsonStr:{}", jsonStr);
        //
        return JSONObject.parseObject(jsonStr);
    }

    public static void main(String[] args) {
        // 3. 获取支持的链列表
//        JSONObject jsonObjectResp = get_support_chain_list();
//        log.info("get_support_chain_list jsonObjectResp:{}", jsonObjectResp);
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONArray jsonArray = jsonObjectResp.getJSONArray("data");
//            if(null != jsonArray && jsonArray.size() > 0)
//            {
//                for(int i=0; i<jsonArray.size(); i++) {
//                    log.info("get_support_chain_list jsonArray index {} : {}", i+1, jsonArray.get(i));
//                    // get_support_chain_list jsonArray index 1 : {"chain_code":"ETH","chain_name":"Ethereum"}
//                }
//            }
//        }

        // 4. 根据链名称获取该链支持的代币列表
//        JSONObject jsonObjectResp = get_coin_list_by_chain_code("ETH");
//        log.info("get_coin_list_by_chain_code jsonObjectResp:{}", jsonObjectResp);
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONArray jsonArray = jsonObjectResp.getJSONArray("data");
//            if(null != jsonArray && jsonArray.size() > 0)
//            {
//                for(int i=0; i<jsonArray.size(); i++) {
//                    log.info("get_coin_list_by_chain_code jsonArray index {} : {}", i+1, jsonArray.get(i));
//                    // get_coin_list_by_chain_code jsonArray index 1 : {"coin_no":"c2577bd5-9043-4bfd-ae88-177673a533e4","coin_name":"Ethereum","coin_code":"ETH"}
//                    // get_coin_list_by_chain_code jsonArray index 2 : {"coin_no":"b5c70cd1-5bdc-4783-beba-f515bd3581ad","coin_name":"USDT test","coin_code":"USDTF"}
//                }
//            }
//        }

        // 1. 注册用户
//        jsonObjectResp = register_user();
//        log.info("register_user jsonObjectResp:{}", jsonObjectResp);
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
//            log.info("register_user jsonObjectData user_no: {}", jsonObjectData.getString("user_no"));
//            // user_no: e2a58b28-db67-4fc6-a27f-5d11d6de322e
//        }

        // 2. 获取钱包地址
//        String userNo = "e2a58b28-db67-4fc6-a27f-5d11d6de322e";
//        JSONObject jsonObjectResp = get_address(userNo, "ETH", "ETH");
//        log.info("get_address jsonObjectResp:{}", jsonObjectResp);
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
//            log.info("get_address jsonObjectData address: {}", jsonObjectData.getString("address"));
//            // address: 0x39f03686b2d673f94f0b555e42bf33167cf033e2
//        }

        // 6. 获取交易列表
        // tx_type的值：DEPOSIT传回充值列表，WITHDRAW传回提现列表，其他值传回所有列表
//        JSONObject jsonObjectResp = get_tx_list("WITHDRAW");
//        log.info("get_tx_list jsonObjectResp:{}", jsonObjectResp);
//        // get_tx_list jsonObjectResp:{"code":0,"data":[],"signature":"c9f1687f554e37ddea67fe6efdf651687036afc9e99a7043abd8e9ea7c36f11c","message":"Success"}
//        // get_tx_list jsonObjectResp:{"code":0,"data":[{"tx_status":"completed","coin_no":"b5c70cd1-5bdc-4783-beba-f515bd3581ad","amount":"1.100000","request_no":"0f61a212-86ff-4e2a-a543-4a0c796608bd","chain_code":"ETH","tx_hash":"0xb1e9ee372e44124a806bb0a84450d2d306f7c8474e9b854446a654ac8458b9e5","from":"","to":"0xDb6B16F3381CC3482bE7ca2EDCC465d0c0aCD6e1","tx_type":"WITHDRAW","coin_code":"USDTF","user_no":"85d94e47-157d-4f57-bd60-ce07d9b6ac35"}],
//        //                          "signature":"791cc120f32be32f84f99024048025a98f8d8b1706f3ba49567575106ff24b0a","message":"Success"}
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONArray jsonArray = jsonObjectResp.getJSONArray("data");
//            if(null != jsonArray && jsonArray.size() > 0)
//            {
//                for(int i=0; i<jsonArray.size(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    log.info("get_tx_list jsonArray index {} : {}", i+1, jsonObject);
//                    // get_tx_list jsonArray index 1 : {"tx_status":"completed","coin_no":"b5c70cd1-5bdc-4783-beba-f515bd3581ad","amount":"1.100000","request_no":"0f61a212-86ff-4e2a-a543-4a0c796608bd","chain_code":"ETH","tx_hash":"0xb1e9ee372e44124a806bb0a84450d2d306f7c8474e9b854446a654ac8458b9e5","from":"","to":"0xDb6B16F3381CC3482bE7ca2EDCC465d0c0aCD6e1","tx_type":"WITHDRAW","coin_code":"USDTF","user_no":"85d94e47-157d-4f57-bd60-ce07d9b6ac35"}
//                }
//            }
//        }

        // 5.发送交易
        String userNo = "e2a58b28-db67-4fc6-a27f-5d11d6de322e1";
        JSONObject jsonObjectResp = create_transaction(userNo, "USDT", "ETH","0.01234", "0x39f03686b2d673f94f0b555e42bf33167cf033e2");
        log.info("create_transaction jsonObjectResp:{}", jsonObjectResp);
        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
            log.info("create_transaction jsonObjectData request_no: {}", jsonObjectData.getString("request_no"));
            // request_no: 771d4761-8dc0-4e6e-a35a-30f8d07f85c0
        } else {
            log.error("error create_transaction jsonObjectResp:{}", jsonObjectResp);
        }

        // 7. 推送交易状态
//        jsonObjectResp = push_tx_status("771d4761-8dc0-4e6e-a35a-30f8d07f85c0");
//        log.info("push_tx_status jsonObjectResp:{}", jsonObjectResp);
//        // push_tx_status jsonObjectResp:{"code":0,"signature":"ea19a979f4afcaafafef882a7a6e546ef6f8947565550437d2644c819ae47cb3","message":"Success"}
//        if(null != jsonObjectResp && "0".equals(jsonObjectResp.getString("code"))){
//            JSONObject jsonObjectData = jsonObjectResp.getJSONObject("data");
//            log.info("push_tx_status jsonObjectData data: {}", jsonObjectData);
//        }

    }
}
