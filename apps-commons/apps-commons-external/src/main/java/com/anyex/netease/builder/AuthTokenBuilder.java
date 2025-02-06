package com.anyex.netease.builder;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class AuthTokenBuilder {
    /**
     * #先获取当前时间戳，单位毫秒
     * curTime = 1614764611561
     * #设置过期时间，单位秒，如600
     * ttl = 600
     * #生成signature，将appkey、accid、curTime、ttl、appsecret五个字段拼成一个字符串，进行sha1编码
     * signature = sha1(appkey + accid + curTime + ttl + appsecret)
     * #组装成json
     * json = {"signature": "xx", "curTime":1614764611561, "ttl": 600}
     * #将json转成字符串后进行base64编码，生成最终的token
     * token=base64(json)
     */

    public static String getToken(String appKey,String appSecret, String accid) {
        String ct = System.currentTimeMillis()+"";
        String ttl = "600";
        JSONObject obj = new JSONObject();
        obj.put("curTime",ct);
        obj.put("ttl",ttl);
        obj.put("signature",encode("sha1", appKey + accid + ct+ttl+appSecret));
        String base64String = Base64.getEncoder().encodeToString((obj.toJSONString()).getBytes(StandardCharsets.UTF_8));
        return base64String;
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    
}
