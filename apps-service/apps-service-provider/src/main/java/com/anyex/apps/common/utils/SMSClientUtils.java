//package com.anyex.apps.common.utils;
//
//import com.anyex.apps.enums.CommonEnums;
//import com.anyex.apps.exception.BusinessException;
//import com.anyex.apps.utils.EncryptUtils;
//import com.anyex.apps.utils.HttpUtils;
//import com.anyex.apps.utils.JSONUtils;
//import com.anyex.apps.utils.StringUtils;
//import com.anyex.apps.common.model.SMSModel;
//import com.anyex.apps.common.model.SMSResult;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
///**
// * 短信发送客户端 Introduce
// * <p>File：SMSClient.java</p>
// * <p>Title: SMSClient</p>
// * <p>Description: SMSClient</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Component
//public class SMSClientUtils
//{
//    @Value("${com.anyex.sms.signature.en}")
//    private String SEND_SIGNATURE_EN;
//
//    @Value("${com.anyex.sms.signature.cn}")
//    private String SEND_SIGNATURE_CN;
//
//    @Value("${com.anyex.sms.host}")
//    private String SEND_HOST;
//
//    @Value("${com.anyex.sms.user}")
//    private String SEND_USER;
//
//    @Value("${com.anyex.sms.pass}")
//    private String SEND_PASS;
//
//    @Value("${com.anyex.sms.inthost}")
//    private String SEND_INTHOST;
//
//    @Value("${com.anyex.sms.intuser}")
//    private String SEND_INTUSER;
//
//    @Value("${com.anyex.sms.intpass}")
//    private String SEND_INTPASS;
//
//    /**
//     * 发送国内短信服务
//     * @param mobile
//     * @param content
//     * @return {@link SMSResult}
//     * @throws BusinessException
//     */
//    public SMSResult sendSMS(String mobile, String content) throws BusinessException
//    {
//        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content))
//        {// 防止空指针
//            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        SMSModel model = new SMSModel();
//        model.setAccount(SEND_USER);
//        model.setPassword(EncryptUtils.desDecrypt(SEND_PASS));
//        model.setPhone(mobile);
//        model.setMsg(content);
//        model.setReport(true);
//        if (StringUtils.isNotBlank(SEND_SIGNATURE_CN))
//        {
//            StringBuffer sms = new StringBuffer("【").append(SEND_SIGNATURE_CN).append("】").append(content);
//            model.setMsg(sms.toString());
//        }
//        String params = JSONUtils.beanToJson(model);
//        CloseableHttpClient client = HttpUtils.getHttpClient2();
//        String response = HttpUtils.postWithJSON(client, SEND_HOST, params);
//        HttpUtils.releaseHttpClient(client);
//        if (StringUtils.isBlank(response)) return null;
//        return JSONUtils.jsonToBean(response, SMSResult.class);
//    }
//
//    /**
//     * 发送国际短信服务
//     * @param mobile
//     * @param content
//     * @return {@link SMSResult}
//     * @throws BusinessException
//     */
//    public SMSResult sendIntSMS(String mobile, String content) throws BusinessException
//    {
//        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content))
//        {// 防止空指针
//            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        SMSModel model = new SMSModel();
//        model.setAccount(SEND_INTUSER);
//        model.setPassword(EncryptUtils.desDecrypt(SEND_INTPASS));
//        model.setMobile(mobile);
//        if (StringUtils.isNotBlank(SEND_SIGNATURE_EN))
//        {
//            StringBuffer sms = new StringBuffer("[").append(SEND_SIGNATURE_EN).append("]").append(content);
//            model.setMsg(sms.toString());
//        }
//        String params = JSONUtils.beanToJson(model);
//        CloseableHttpClient client = HttpUtils.getHttpClient2();
//        String response = HttpUtils.postWithJSON(client, SEND_INTHOST, params);
//        HttpUtils.releaseHttpClient(client);
//        if (StringUtils.isBlank(response)) return null;
//        return JSONUtils.jsonToBean(response, SMSResult.class);
//    }
//
//     public void main(String[] args) throws BusinessException
//     {
//     SMSResult result = sendIntSMS("8619906620879", "BITMS SMS TEST");
//     System.out.println(JSON.toJSONString(result));
//     }
//}
