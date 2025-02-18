package com.anyex.apps;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.anyex.apps.utils.AmazonSNSUtils;
import com.google.common.collect.Maps;

import java.util.Map;

public class AwsSNSSmsTest {

    public static void main(String[] args)
    {
        // sendSMS("+13238618730", "sms test");
        AmazonSNSUtils.sendSMS("+13238618730", "sms test");
    }

    //
    private static AmazonSNS snsClient;

    private static Map<String, MessageAttributeValue> smsAttributes;

    static
    {
        smsAttributes = Maps.newHashMap();
        // The sender ID shown on the device.
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("XM").withDataType("String"));
        // Sets the max price to 0.50 USD.
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue().withStringValue("0.50").withDataType("Number"));
        // Sets the type to promotional.
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Promotional").withDataType("String"));

        //
        initAmazonSNS();
    }

    /**
     * 初始化亚马逊文件SNS服务
     */
    static void initAmazonSNS()
    {
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTPS);
        Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
        // AWSCredentials credentials = new BasicAWSCredentials("AKIAJHHO2FOSKLW7FKVQ", "jQyR3Dq4KHrPC0FEHVfNQn8nOS5H7Ihk9VQCbcUa");
        AWSCredentials credentials = new BasicAWSCredentials("AKIAVOUOUGSOH4XC43U5", "/NObXJG2qxx9Ng++ACsTmuuMGmpPNUwwLpdY90Kw");
        // AWSCredentials credentials = new BasicAWSCredentials(BitmsConst.AWS_ACCESS_KEY_ID, BitmsConst.AWS_SECRET_ACCESS_KEY);
        snsClient = AmazonSNSClientBuilder.standard() //
                .withCredentials(new AWSStaticCredentialsProvider(credentials))// 凭证
                .withClientConfiguration(clientConfig)// 连接配置
                .withRegion(region.getName()).build(); // 区域配置
    }

    /**
     * 发送手机短信
     * @param phoneNumber
     * @param message
     */
    public static boolean sendSMS(String phoneNumber, String message)
    {
        PublishRequest request = new PublishRequest() //
                .withPhoneNumber(phoneNumber) // 手机号
                .withMessage(message) // 短信内容
                .withMessageAttributes(smsAttributes);
        PublishResult result = snsClient.publish(request);
        return null != result.getMessageId() ? true : false;
    }
}
