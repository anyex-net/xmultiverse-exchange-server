package com.anyex.apps.utils;

import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * AmazonSESUtils Introduce
 * <p>Title: AmazonSESUtils</p>
 * <p>File：AmazonSESUtils.java</p>
 * <p>Description: AmazonSESUtils</p>
 * <p>Copyright: Copyright (c) 2018/4/26</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class AmazonSESUtils
{
    @Value("${com.amazon.ses.provider}")
    private String           SES_PROVIDER;
    
    @Value("${com.amazon.ses.host}")
    private String           HOST;
    
    @Value("${com.amazon.ses.port}")
    private Integer          PORT = 587;
    
    @Value("${com.amazon.ses.biex.fromuser}")
    private String           FROM_BIEX_NAME;
    
    @Value("${com.amazon.ses.biex.password}")
    private String           SMTP_BIEX_PASSWORD;
    
    @Value("${com.amazon.ses.biex.personal}")
    private String           FROM_BIEX_PERSONAL;
    
    @Value("${com.amazon.ses.biex.username}")
    private String           SMTP_BIEX_USERNAME;
    
    @Value("${com.amazon.ses.bitms.fromuser}")
    private String           FROM_BITMS_NAME;
    
    @Value("${com.amazon.ses.bitms.personal}")
    private String           FROM_BITMS_PERSONAL;
    
    @Value("${com.amazon.ses.bitms.username}")
    private String           SMTP_BITMS_USERNAME;
    
    @Value("${com.amazon.ses.bitms.password}")
    private String           SMTP_BITMS_PASSWORD;
    
    @Autowired
    private GlobalProperies properies;
    
    /**
     * 发送亚马逊邮件
     * @param subject 主题
     * @param body 内容
     * @param to 接收帐户
     * @param cc 抄送帐户
     * @throws BusinessException
     */
    public void sendMail(String subject, String body, String to, String ... cc) throws BusinessException
    {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        String FROM_NAME = FROM_BIEX_NAME;
        String FROM_PERSONA = FROM_BIEX_PERSONAL;
        String SMTP_USERNAME = EncryptUtils.desDecrypt(SMTP_BIEX_USERNAME);
        String SMTP_PASSWORD = EncryptUtils.desDecrypt(SMTP_BIEX_PASSWORD);
        if (properies.getProjectName().equalsIgnoreCase(SES_PROVIDER))
        {
            FROM_NAME = FROM_BITMS_NAME;
            FROM_PERSONA = FROM_BITMS_PERSONAL;
            SMTP_USERNAME = EncryptUtils.desDecrypt(SMTP_BITMS_USERNAME);
            SMTP_PASSWORD = EncryptUtils.desDecrypt(SMTP_BITMS_PASSWORD);
        }
        try
        {
            msg.setFrom(new InternetAddress(FROM_NAME, FROM_PERSONA));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (null != cc)
            {// 是否添加抄送
                Address[] addresses = new InternetAddress[cc.length];
                for (int i = 0; i < cc.length; i++)
                {
                    addresses[i] = new InternetAddress(cc[i]);
                }
                msg.setRecipients(Message.RecipientType.CC, addresses);
            }
            msg.setSubject(subject);
            msg.setContent(body, "text/html;charset=utf-8");
            msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
            transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            throw new BusinessException("The email was not sent." + ex.getLocalizedMessage());
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
    
    /**
     * 发送亚马逊邮件
     * @param subject 主题
     * @param body 内容
     * @param to 接收帐户
     * @param cc 抄送帐户
     * @throws BusinessException
     */
    public void sendMail(String subject, String body, String to, InternetAddress[] cc) throws BusinessException
    {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        Transport transport = null;
        String FROM_NAME = FROM_BIEX_NAME;
        String FROM_PERSONA = FROM_BIEX_PERSONAL;
        String SMTP_USERNAME = EncryptUtils.desDecrypt(SMTP_BIEX_USERNAME);
        ;
        String SMTP_PASSWORD = EncryptUtils.desDecrypt(SMTP_BIEX_PASSWORD);
        if (properies.getProjectName().equalsIgnoreCase(SES_PROVIDER))
        {
            FROM_NAME = FROM_BITMS_NAME;
            FROM_PERSONA = FROM_BITMS_PERSONAL;
            SMTP_USERNAME = EncryptUtils.desDecrypt(SMTP_BITMS_USERNAME);
            ;
            SMTP_PASSWORD = EncryptUtils.desDecrypt(SMTP_BITMS_PASSWORD);
        }
        try
        {
            msg.setFrom(new InternetAddress(FROM_NAME, FROM_PERSONA));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if (null != cc)
            {// 是否添加抄送
                msg.setRecipients(Message.RecipientType.CC, cc);
            }
            msg.setSubject(subject);
            msg.setContent(body, "text/html;charset=utf-8");
            msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
            transport = session.getTransport();
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        }
        catch (Exception ex)
        {
            throw new BusinessException("The email was not sent." + ex.getLocalizedMessage());
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
}
