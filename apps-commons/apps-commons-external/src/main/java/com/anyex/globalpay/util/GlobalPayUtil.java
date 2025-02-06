package com.anyex.globalpay.util;


import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.HttpUtils;
import com.anyex.globalpay.config.GlobalPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GlobalPayUtil {

    static DefaultHttpClient client = null;
    static {
        client = new DefaultHttpClient();
    }
    public static JSONObject doPostWithJson(JSONObject json,String url) throws BusinessException{

        log.info("global pay url = {} \n global pay body = {}  ",url,json.toString());
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.parseObject(result);
                log.info("global pay response = {} ",response.toString());
            }
        } catch (Exception e) {
            log.info("global pay exception = {} ",url,e.getMessage());
            throw new BusinessException(e.getMessage());
        }
        return response;
    }


    public static JSONObject doPost(Map<String, Object> map, String url) throws BusinessException{

        log.info("global pay url = {} \n global pay body = {}  ",url,map.toString());
        Map<String,String> rquest = new HashMap<>();
        for (Map.Entry entry : map.entrySet()) {
            rquest.put(entry.getKey().toString(),entry.getValue().toString());
        }
        String r = HttpUtils.post(client,url,rquest);
        JSONObject response = null;
        response = JSONObject.parseObject(r);
        log.info("global pay response = {} ",response.toString());
        return response;
    }

    // 代收下单
    public static JSONObject pay(GlobalPayConfig config,Map<String, Object> map)  throws BusinessException{
        map.put("notifyUrl", config.payInNotifyUrl);
        map.put("mchNo", config.mchno);
        map.put("appId", config.appid);
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign", getSign(map, config.key));
        JSONObject resultJson = doPost(map, config.getRootUrl()+config.rootUrlUnifiedorder);
        return resultJson;
    }

    // 代收订单查询
    public static JSONObject payOrderQuery(GlobalPayConfig config,Map<String, Object> map)  throws BusinessException{
        map.put("mchNo", config.mchno);
        map.put("appId", config.appid);
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign", getSign(map, config.key));
        JSONObject resultJson = doPost(map, config.getRootUrl()+config.rootUrlUnifiedorderQuery);
        return resultJson;
    }

    // 代付下单
    public static JSONObject transaction(GlobalPayConfig config,Map<String, Object> map)  throws BusinessException{
       // JSONObject resultJson = doPost(JSONObject.parseObject(JSON.toJSONString(map)), config.getRootUrl()+config.ROOT_URL_TRANSFERORDER);
        JSONObject resultJson = doPost(map, config.getRootUrl()+config.rootUrlTransferorder);
        return resultJson;
    }

    // 代付订单查询
    public static JSONObject transactionOrderQuery(GlobalPayConfig config,Map<String, Object> map)  throws BusinessException{
        map.put("mchNo", config.mchno);
        map.put("appId", config.appid);
        map.put("reqTime", System.currentTimeMillis());
        map.put("version", "1.0");
        map.put("signType", "MD5");
        map.put("sign", getSign(map, config.key));
        JSONObject resultJson = doPost(map, config.getRootUrl()+config.rootUrlTransferorderQuery);
        return resultJson;
    }


    /**
     * Description: 计算签名摘要
     *
     * @param map 参数Map
     * @param key 商户秘钥
     * @return
     */
    public static String getSign(Map<String, Object> map, String key) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (null != entry.getValue() && !"".equals(entry.getValue())) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        System.out.println("signStr:" + result);
        result = md5(result, "UTF-8").toUpperCase();
        System.out.println("sign:" + result);
        return result;
    }


    /**
     * Description: MD5
     *
     * @param value
     * @param charset
     * @return
     */
    public static String md5(String value, String charset) {
        MessageDigest md = null;
        try {
            byte[] data = value.getBytes(charset);
            md = MessageDigest.getInstance("MD5");
            byte[] digestData = md.digest(data);
            return toHex(digestData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toHex(byte input[]) {
        if (input == null) {
            return null;
        }
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16) {
                output.append("0");
            }
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

}
