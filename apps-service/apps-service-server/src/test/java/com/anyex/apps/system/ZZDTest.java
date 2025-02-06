package com.anyex.apps.system;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

/**
 * @Author
 * @Date 2023/6/26 9:21
 */
@Slf4j
public class ZZDTest {

    private static final String ak = "BCDSGA_03bb331140f71b70e7544b3f9b9251fe";
    private static final String sk = "BCDSGS_73464a262d2d921062d438edde679b82";

//    public static void main(String[] args) {
//        String url = "https://ibcdsg.zj.gov.cn:8443/restapi/prod/IC33000020220322000001/rest/sop-sms/sms/post/Send?";
////        String ak = "BCDSGA_03bb331140f71b70e7544b3f9b9251fe";
////        String sk = "BCDSGS_73464a262d2d921062d438edde679b82";
//        TreeMap<String, String> map = new TreeMap<>();
//        map.put("MessageContent", "XXX小区XX位置车牌XXX（核实）消防通道被占用。");
//        map.put("UserNumber", "13454704354");
//        map.put("f","1");
//        map.put("signCode","ba940378-dbe4-4973-b963-f392f6286c89");
//
//        if (map != null && !map.isEmpty()) {
//            for (Map.Entry<String, String> kv : map.entrySet()) {
//                url += "&" + kv.getKey() + "=" + kv.getValue();
//            }
//        }
//        if (StringUtils.isNoneBlank(url)) {
//            url = RegExUtils.replaceFirst(url, "&", "");
//        }
//        Map<String, String> header = HmacAuthUtil.generateHeader(url, "POST", ak, sk);
//        JSONObject token = requestToken(url, header.get("X-BG-HMAC-SIGNATURE"), header.get("X-BG-HMAC-ACCESS-KEY"),header.get("X-BG-HMAC-ALGORITHM"), header.get("X-BG-DATE-TIME"));
//        System.out.println(token);
//    }

    @SneakyThrows
    public static JSONObject requestToken(String url, String signature, String ak, String algorithm, String time) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
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
