package com.anyex.apps;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.anyex.apps.exception.BusinessException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AwsSESEmailTest {

    // aws ses SMTP标准方式发送
    public static void main1(String[] args){
        //
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // 强制使用TLSv1.2
        props.put("https.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        String FROM_NAME = "xmultiverse.org@gmail.com"; // "service@biex.com";
        String FROM_PERSONA = "BIEX Notifications";
        String SMTP_USERNAME = "AKIAVOUOUGSOLEH6WG5K"; // "AKIAVOUOUGSOH4XC43U5";
        String SMTP_PASSWORD = "BLARLhbh/ilCsLwElOuD+ht9MF9cr0YIiE8sgSiVUV7k"; // "/NObXJG2qxx9Ng++ACsTmuuMGmpPNUwwLpdY90Kw";
        try
        {
            msg.setFrom(new InternetAddress(FROM_NAME, FROM_PERSONA));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("anyexnet@qq.com"));
//            if (null != cc)
//            {// 是否添加抄送
//                Address[] addresses = new InternetAddress[cc.length];
//                for (int i = 0; i < cc.length; i++)
//                {
//                    addresses[i] = new InternetAddress(cc[i]);
//                }
//                msg.setRecipients(Message.RecipientType.CC, addresses);
//            }
            msg.setSubject("subject BIEX Notifications");
            msg.setContent("body BIEX Notifications", "text/html;charset=utf-8");
            // msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
            transport = session.getTransport();
            transport.connect("email-smtp.ap-southeast-1.amazonaws.com", SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new BusinessException("The email was not sent. " + ex.getLocalizedMessage());
        }
        finally
        {
            try
            {
                transport.close();
            }
            catch (MessagingException e)
            {
            }
        }
    }

    // aws ses SESSDK方式发送
    public static void main3(String[] args) {
        // AWS凭证，需要替换为有效的凭证
        String accessKey = "AKIAVOUOUGSOH4XC43U5";
        String secretKey = "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";

        // 创建一个AWS credentials对象
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // 创建AmazonSimpleEmailService客户端
        AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTHEAST_1) // 替换为你的AWS区域
                .build();

        // 创建一个SendEmailRequest对象
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses("anyexnet@qq.com"))
                .withMessage(new com.amazonaws.services.simpleemail.model.Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8")
                                        .withData(createHtmlBody()))) // 设置HTML内容
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData("邮件主题"))) // 设置邮件主题
                .withSource("xmultiverse.org@gmail.com"); // 设置发件人邮箱地址

        // 发送邮件
        client.sendEmail(request);
        System.out.println("邮件发送成功！");
    }

    private static String createHtmlBody() {
        // 创建HTML表格内容
        String htmlBody = "<html>" +
                "<head></head>" +
                "<body>" +
                "<p>这是一封包含HTML表格的邮件：</p>" +
                "<table border='1'>" +
                "<tr><th>名称</th><th>值</th></tr>" +
                "<tr><td>数据1</td><td>值1</td></tr>" +
                "<tr><td>数据2</td><td>值2</td></tr>" +
                "</table>" +
                "</body>" +
                "</html>";
        return htmlBody;
    }
}
