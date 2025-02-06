package com.anyex.apps.pay;

import com.alibaba.fastjson.JSONObject;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.StringUtils;
import com.anyex.wivpay.config.WivPayConfig;
import com.anyex.wivpay.util.WivPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
//@Component
public class WivPayApi {

    static {
    }

    /**
     * 代收
     * @Param channelAliasName 支付通道
     * @Param orderSn 业务系统订单号
     * @Param cnic 身份证
     * @param realName 真实姓名
     * @param mobile   手机号
     * @param email    邮箱
     * @param amount   金额
     * @return
     * @throws BusinessException
     */
    public static JSONObject payIn(WivPayConfig wivPayConfig, String channelAliasName, String orderSn, String cnic, String realName, String mobile, String email, Double amount, Long accountId) throws BusinessException {
        TreeMap<String, String> map = new TreeMap<String, String>();
        // step 1 组装参数
        String notifyUrl = wivPayConfig.getNotifyUrl();
        map.put("notifyUrl", notifyUrl);
        String merchant_sn = wivPayConfig.getMerchantSn();
        DecimalFormat df = new DecimalFormat("#0.00");
        String amountStr = df.format(amount);
        // 6.2.0 版本
        map.put("merchantSn", merchant_sn);
        map.put("tradeNo", orderSn);
        map.put("payAmount", amountStr);
        map.put("channelAliasName",channelAliasName);
        if(accountId !=null)
        {
            map.put("userIdentity",accountId.toString());
        }
        // 冗余字段 接口未提到的字段
        /**
         * 给globalpay收银台带的字段
         */
        if(StringUtils.isNotBlank(cnic))
        {
            map.put("customerCnic", cnic);
        }
        if (StringUtils.isNotBlank(realName))
        {
            map.put("customerName", realName);
        }
        if (StringUtils.isNotBlank(mobile))
        {
            map.put("customerMobile", mobile);
        }
        if (StringUtils.isNotBlank(email))
        {
            map.put("customerEmail", email);
        }
       //  Calendar.getInstance().setTimeZone(Time);
        String timestr = System.currentTimeMillis() + "";
        map.put("timestamp", timestr);

        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str += entry.getKey() + "=" + entry.getValue() + "&";
        }
        // 签名【md5(商户号+商户订单号+法币⾦额+异步通知地址+时间戳+商户秘钥)】
        log.info("代收业务获取支付链接：str:{}", str);
        String key =
                new StringBuffer(wivPayConfig.getMerchantSn())
                        .append(orderSn)
                        .append(amountStr)
                        .append(wivPayConfig.getNotifyUrl())
                        .append(timestr)
                        .append(wivPayConfig.getMerchantSk()
                        ).toString();
        System.out.println(key);
        map.put("sign", WivPayUtil.getSignForWivPay(key));
        System.out.println(map.get("sign"));
        // step 2 发送请求
        String url = wivPayConfig.getPayUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        System.out.println(JSONObject.toJSONString(map));
        HttpEntity httpEntity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        log.info("代收业务获取支付链接：url:{}", url);
        log.info("代收业务获取支付链接：request body:{}", map);
        ResponseEntity<Object> rsp = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
        str = JSONObject.toJSONString(rsp.getBody());
        log.info("代收业务获取支付链接：response body:{}", str);
        // step 3 报文解析
        JSONObject object = JSONObject.parseObject(str);
        return object;
    }


    /**
     * 代收查询
     * @param tradeNo 订单号
     * @return
     * @throws BusinessException
     */
    public static JSONObject payInQuery(WivPayConfig wivPayConfig, String tradeNo) throws BusinessException {
        TreeMap<String, String> map = new TreeMap<String, String>();
        // step 1 组装参数
        String merchant_sn = wivPayConfig.getMerchantSn();
        map.put("merchantSn", merchant_sn);
        map.put("tradeNo", tradeNo);
        String str = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            str += entry.getKey() + "=" + entry.getValue() + "&";
        }
        str += "key=9047fc08a974b5a3f36573de88431b3a";
        log.info("代收业务结果查询：str:{}", str);
        map.put("sign", WivPayUtil.getSignForWivPay(wivPayConfig.getMerchantSn()+tradeNo+wivPayConfig.getMerchantSk()));
        // step 2 发送请求
        String url = wivPayConfig.getPayQueryUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity httpEntity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        log.info("代收业务结果查询：url:{}", url);
        log.info("代收业务结果查询：request body:{}", map);
        ResponseEntity<Object> rsp = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
        str = JSONObject.toJSONString(rsp.getBody());
        log.info("代收业务结果查询：response body:{}", str);
        // step 3 报文解析
        JSONObject object = JSONObject.parseObject(str);
        return object;
    }
}