package com.anyex.apps.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DingdingUtils {

    // HttpClient高并发下性能优化 开始
    private static RequestConfig requestConfig = null;

    private static CloseableHttpClient httpClient = null;

    private static PoolingHttpClientConnectionManager cm;

    static {
        //初始化HTTP请求配置，设置连接池请求、获取数据和建立连接超时时间
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(4000)
                .setSocketTimeout(4000)
                .setConnectTimeout(4000)
                .build();

        //连接池配置
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);//最大连接数
        cm.setDefaultMaxPerRoute(20);//每个路由最大连接数
        //创建定制http客户端
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))//设置http client的重试次数，默认是3次；当前是禁用掉（如果项目量不到，这个默认即可）
                .build();

    }
    // HttpClient高并发下性能优化 结束

    /**
     * 发送钉钉消息
     * @param url 钉钉自定义机器人webhook
     * @param jsonString 消息内容
     * @return
     */
    public static String sendToDingding(String url, String jsonString) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = jsonString;
        //System.out.println("发送钉钉消息textMsg:" + textMsg);
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httpPost.setEntity(se);
        String result = null;
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("sendToDingding result钉钉消息发送结果:{}", result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        return result;
    }

    /**
     * 选择加签方式下的加签方法
     *
     * @param secret 密钥，机器人安全设置页面，加签一栏下面显示的SEC开头的字符串
     * @return
     */
    private static Map<String, String> dingDingSec(String secret) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        Map<String, String> map = new HashMap();
        map.put("sign", sign);
        map.put("timestamp", timestamp.toString());
        return map;
    }

    /**
     * 加签机器人实现，这里需要注意的是：timestamp和sign需要保持一致
     *
     * @param message 要发送的信息
     *
     * @return
     * @throws Exception
     */
    public static String sendSecRobot(String webhook, String sec, String message) throws Exception {
        Map<String, String> map = dingDingSec(sec);
        String sign = map.get("sign");
        String timestamp = map.get("timestamp");
        StringBuffer stringBuffer = new StringBuffer();
        String robotUrl = stringBuffer.append(webhook).append("&timestamp=").append(timestamp).append("&sign=").append(sign).toString();
        return sendToDingding(robotUrl, message);
    }

    /**
     * 关键字机器人：发送消息中需要有对应的关键字才能发送成功
     *
     * @param message 封装的消息
     * @return
     * @throws Exception
     */
    public static String sendKeyRobot(String webhook, String message) throws Exception {
        return sendToDingding(webhook, message);
    }
}
