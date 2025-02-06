package com.anyex.apps;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.mapdb.*;
import org.openjsse.net.ssl.OpenJSSE;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/**
 *
 * https://mapdb.org/ 官网
 *
 * https://juejin.cn/post/7362757977735036979
 *
 * 探索Map<D,B>：Java中的高性能内存数据存储解决方案
 *
 * MapDB 是一个快速、易用的嵌入式 Java 数据库引擎，它提供了基于磁盘或者堆外（off-heap 允许Java 直接操作内存空间,
 * 类似于C 的malloc 和free）存储的并发的Maps、Sets、Queues。
 * MapDB 的前身是JDBM，已经有15 年的历史。
 * MapDB 支持ACID 事务、MVCC 隔离，它的jar 包只有200KB，且无其它依赖，非常轻量。
 * MapDB 目前的版本是3.1.0，相对来说功能已经稳定，并有全职的开发者支持开发。
 *
 */
@Slf4j
public class TestMapDB {
    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6LcHUs0qAAAAANB2TBcbKpOKyEEzyaQKW-vt6CNN";
    private static class RecaptchaResponse {
        boolean success;
        double score;
        String action;
        String challenge_ts;
        String hostname;
        // 其他字段根据需要添加
    }

    private static void configureTLS13() {
        // 安装OpenJSSE provider
        //Security.addProvider(new OpenJSSE());

        // 您可以在这里设置其他系统属性，例如：
        // System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
//        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    public static void main(String[] args) throws Exception {
        String token = "123456";

//        // 发送验证请求到 Google reCAPTCHA API
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(RECAPTCHA_URL + "?secret=" + SECRET_KEY + "&response=" + token);
//
//        HttpResponse response = httpClient.execute(httpPost);
//        HttpUtils.post()
//        String responseBody = EntityUtils.toString(response.getEntity());
//
//        // 解析响应
//        Gson gson = new Gson();
//        RecaptchaResponse recaptchaResponse = gson.fromJson(responseBody, RecaptchaResponse.class);
//
//        // 根据评分判断是否为机器人
//        boolean isHuman = recaptchaResponse.success && recaptchaResponse.score >= 0.5;
//
//        // 返回验证结果
//        log.info("verifyRecaptcha recaptchaResponse:{}", recaptchaResponse.toString());
////        resp.setContentType("application/json");
////        Map<String, Object> result = new HashMap<>();
////        result.put("success", isHuman);
////        result.put("score", recaptchaResponse.score);
////        resp.getWriter().write(gson.toJson(result));


        // 创建忽略SSL验证的HttpClient实例
//        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

//        // 首先调用configureTLS13方法
//        configureTLS13();
//        // 创建一个支持TLS 1.3的SSLContext实例
//        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
//
//        // SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
//        sslContext.init(null, null, new java.security.SecureRandom());
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
//                new String[]{"TLSv1.2"}, // 强制使用TLSv1.2
//                null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .build();

//        SSLContext sslContext = SSLContextBuilder.create()
//                .loadTrustMaterial((chain, authType) -> true) // 注意：仅用于测试环境
//                .build();
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLContext(sslContext)
//                .setSSLHostnameVerifier((hostname, session) -> true) // 注意：仅用于测试环境
//                .build();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(RECAPTCHA_URL);
        String json = "{\"secret\":\"" + SECRET_KEY + "\",\"response\":\"" + token + "\"}";
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");
        // post.setHeader("Accept","*/*");

        CloseableHttpResponse response = httpClient.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        log.info("verifyRecaptcha result:{}", result.toString());

        httpClient.close();

        // 解析响应
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(result.toString(), Map.class);

        if ((boolean) map.get("success")) {
            log.info("Verification successful!");
        } else {
            log.info("Verification failed.");
        }
    }

    public static void main2(String[] args) {
        //test1();
        //test2();
        test3();
        //test4();
    }

    public static void test1() {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        System.out.println(map.get("something"));
    }

    public static void test2(){
        DB db = DBMaker.fileDB("file.db").make();
        ConcurrentMap map = db.hashMap("map").createOrOpen();
        map.put("something", "here");
        System.out.println(map.get("something"));
        db.close();
    }

    /**
     * 默认情况下，MapDB 使用通用序列化，可以序列化任何数据类型。使用专门的序列化程序会更快、更节省内存。
     * 此外，我们还可以在 64 位操作系统上启用更快的内存映射文件（只用用于数据存储在文件中fileDB，不能用于memoryDB）
     */
    public static void test3() {
        DB db = DBMaker
                .fileDB("file.db")
                //64 位操作系统上启用更快的内存映射文件
                .fileMmapEnable()
                .make();

        ConcurrentMap<String, Long> map = db
                //指定专门的序列化方式
                .hashMap("map", Serializer.STRING, Serializer.LONG)
                .createOrOpen();
        map.put("something", 111L);
        System.out.println(map.get("something"));
        db.close();
    }

    public static void test4() {
        DB db = DBMaker
                .memoryDB()
                .transactionEnable()
                .make();
        //#a
        ConcurrentNavigableMap<Integer, String> map = db
                .treeMap("collectionName", Serializer.INTEGER, Serializer.STRING)
                .createOrOpen();

        map.put(1, "one");
        map.put(2, "two");
        //map.keySet() is now [1,2] even before commit

        db.commit();  //persist changes into disk

        map.put(3, "three");
        //map.keySet() is now [1,2,3]
        db.rollback(); //revert recent changes
        //map.keySet() is now [1,2]

        db.close();
        //#z
    }
}
