package com.anyex.apps.common;

import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * EmailServiceTest
 * <p>File: EmailServiceTest.java </p>
 * <p>Title: EmailServiceTest </p>
 * <p>Description: EmailServiceTest </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class EmailServiceTest extends BaseServiceImplTest {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String         formMail;

    /**
     * 发送简单邮件
     * @throws BusinessException
     */
    @Test
    public void sendSimpleMail() throws BusinessException
    {
        String toMail = "zhangxiger@gmail.com";
        String subject = "简单邮件测试";
        String content = "TEST EMAIL";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try
        {
            sender.send(simpleMailMessage);
            log.info("发送给" + toMail + "简单邮件已经发送。 subject：" + subject);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.info("发送给" + toMail + "send mail error subject：" + subject);
        }
    }

    /**
     * 发送富文本邮件
     * @throws BusinessException
     */
    @Test
    public void sendHtmlMail() throws BusinessException
    {
        String toMail = "275625385@qq.com";
        String subject = "富文本邮件测试";
        String content = "";
        MimeMessage mimeMessage = sender.createMimeMessage();
        try
        {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setFrom(formMail);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setSubject(subject);
            sender.send(mimeMessage);
            log.info("发送给" + toMail + "html邮件已经发送。 subject：" + subject);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
            log.info("发送给" + toMail + "html send mail error subject：" + subject);
        }
    }

    /**
     * 发送带有附件的邮件
     * @throws BusinessException
     */
    @Test
    public void sendAttachmentsMail() throws BusinessException
    {
        String toMail = "275625385@qq.com";
        String subject = "带有附件的邮件测试";
        String content = "";
        String filePath = "";
        MimeMessage message = sender.createMimeMessage();
        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(formMail);
            helper.setTo(toMail);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf("/"));
            helper.addAttachment(fileName, file);
            sender.send(message);
            log.info("发送给" + toMail + "带附件的邮件已经发送。");
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
            log.error("发送给" + toMail + "带附件的邮件时发生异常！", e);
        }
    }

    @Test
    public void testSerialnoUtils(){
        System.out.println(SerialnoUtils.randomNum(8));
        System.out.println(SerialnoUtils.buildPrimaryKey());
        System.out.println(SerialnoUtils.getOrderNum());

        System.out.println(System.currentTimeMillis()); // 毫秒
        System.out.println(System.nanoTime()); // 纳秒
    }
}
