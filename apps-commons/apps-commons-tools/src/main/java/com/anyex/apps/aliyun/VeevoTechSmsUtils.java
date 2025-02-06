package com.anyex.apps.aliyun;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

@Slf4j
public class VeevoTechSmsUtils {

    /**
     *
     * @param mobile
     * @param content
     * @return result:{"status":"ACCEPTED","BillingLog":{"SMS_Parts":0,"Credit_Deducted":0},"MessageID":"4d5463774e544d7a4d7a4977"}
     */
    public static boolean send(String mobile, String content){
        mobile = mobile.replaceAll(" ", "");
        String result = null;
        content = content.replaceAll(" ", "%20");
        //content += "VT-OTPUy9c3j";
        String url = "https://api.veevotech.com/v3/sendsms?hash=047f86dc6e61cb1696589a56bed6c5d4&receivernum=" + mobile + "&sendernum=default&textmessage=" + content + "&header=X";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HTTP.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_JSON));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                // 获取响应信息
                result = EntityUtils.toString(response.getEntity());
                log.info("result:{}", result);
                JSONObject json = JSONObject.parseObject(result);
                log.info("json:{}", json);
                if (json != null && "SUCCESSFUL".equals(json.getString("STATUS"))) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(send("923300810092", "Dear user, your login verification code is 7437."));

//        String str = "{\"status\":\"ACCEPTED\",\"BillingLog\":{\"SMS_Parts\":0,\"Credit_Deducted\":0},\"MessageID\":\"4d5463774e544d304f446b35\"}";
//        JSONObject json = JSONObject.parseObject(str);
//        System.out.println(json.getString("status"));

        //发送短信请求
        String captcha = "5690";
        String hash = "eVBdgo/zslK";
        boolean bool = VeevoTechSmsUtils.send("923300810092", "Dear user, your login verification code is " + captcha + "." + hash);
    }
}
