package com.anyex.apps.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云短信服务：
 * 注意：需要 签名名称、模版CODE 以及 RAM访问控制中的 AccessKeyID 和 AccessKeySecret
 */
public class AliyunSmsUtils {

    // 产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI5tBAKUNBfCswtMQgGPZT";           // 改这里
    static final String accessKeySecret = "AEcdi4R8hZUHZxxnDSsMia4xclJOFz"; // 改这里

    public static boolean sendSmsCode(String telephone, String code) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("杭州瑞聚智能科技"); // 改这里
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_308740016");  // 改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
            return true;
        }else {
            System.out.println("短信发送失败！");
            return false;
        }
    }

    public static boolean sendSmsMsg(String telephone, String msg) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("杭州瑞聚智能科技"); // 改这里
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_269140719");  // 改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"msg\":\"" + msg + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
            return true;
        }else {
            System.out.println("短信发送失败！");
            return false;
        }
    }

    public static boolean sendSmsCodeInternational(String telephone, String code) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("杭州瑞聚智能科技"); // 改这里
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_478435578");  // 改这里
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的用户,您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

//        // hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
//            System.out.println("短信发送成功！");
//            return true;
//        }else {
//            System.out.println("短信发送失败！");
//            return false;
//        }

        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                System.out.println("短信发送成功！");
                return true;
            } else {
                System.out.println("短信发送失败：" + sendSmsResponse.getMessage());
                return false;
            }
        } catch (ClientException e) {
            System.err.println("Error code: " + e.getErrCode());
            System.err.println("Error message: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        //sendSmsCode("8619957039042", "123456");//填写你需要测试的手机号码
        sendSmsCodeInternational("19132638059", "123456");//填写你需要测试的手机号码
//        /*
//        SendSmsResponse sendSms =sendSmsCode("8619957039042", "123456");//填写你需要测试的手机号码
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + sendSms.getCode());
//        System.out.println("Message=" + sendSms.getMessage());
//        System.out.println("RequestId=" + sendSms.getRequestId());
//        System.out.println("BizId=" + sendSms.getBizId());
//        */
//
//        /*
//        SendSmsResponse sendSms =sendSmsMsg("8619957039042", "系统异常HQ异常");//填写你需要测试的手机号码
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + sendSms.getCode());
//        System.out.println("Message=" + sendSms.getMessage());
//        System.out.println("RequestId=" + sendSms.getRequestId());
//        System.out.println("BizId=" + sendSms.getBizId());
//        */
    }
}
