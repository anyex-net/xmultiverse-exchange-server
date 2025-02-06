package com.anyex.apps.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author 短信发送
 * @Date 2023/6/27 9:54
 */
@Slf4j
public class SendNoteUtils {
    private static String ak = "BCDSGA_03bb331140f71b70e7544b3f9b9251fe";
    private static String sk = "BCDSGS_73464a262d2d921062d438edde679b82";
    private static final String url = "https://ibcdsg.zj.gov.cn:8443/restapi/prod/IC33000020220322000001/rest/sop-sms/sms/post/Send?";

    /**
     *
     * @param content 短信内容
     * @param phone 手机号
     * @return
     * 您好！XX小区消防通道被占用，请您及时处理处置，消防安全，人人参与。
     */
    public static JSONObject sendNotePhone(String content,String phone){
        String sendUrl=url;
        TreeMap<String, String> map = new TreeMap<>();
        map.put("MessageContent", content);
        map.put("UserNumber", phone);
        map.put("f","1");
        map.put("signCode","");
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> kv : map.entrySet()) {
                sendUrl += "&" + kv.getKey() + "=" + kv.getValue();
            }
        }
        if (StringUtils.isNoneBlank(sendUrl)) {
            sendUrl = RegExUtils.replaceFirst(sendUrl, "&", "");
        }
        Map<String, String> header = HmacAuthUtil.generateHeader(sendUrl, "POST", ak, sk);
        return requestToken(sendUrl, header.get("X-BG-HMAC-SIGNATURE"), header.get("X-BG-HMAC-ACCESS-KEY"),header.get("X-BG-HMAC-ALGORITHM"), header.get("X-BG-DATE-TIME"));
    }

    @SneakyThrows
    public static JSONObject requestToken(String url, String signature, String ak, String algorithm, String time) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("X-BG-HMAC-SIGNATURE", signature)
                .addHeader("X-BG-HMAC-ACCESS-KEY", ak)
                .addHeader("X-BG-HMAC-ALGORITHM", algorithm)
                .addHeader("X-BG-DATE-TIME", time)
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        log.info("短信请求结果：{}", responseBody);
        return JSONObject.parseObject(responseBody);
    }
}
