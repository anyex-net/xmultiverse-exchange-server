/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.aliyuncs.exceptions.ClientException;
import com.anyex.apps.aliyun.AliyunSmsUtils;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.CalendarUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.entity.SysMsgRecord;
import com.anyex.apps.common.entity.SysMsgTemplate;
import com.anyex.apps.common.enums.MessageEnums;
import com.anyex.apps.common.mapper.SysMsgRecordMapper;
import com.anyex.apps.common.mapper.SysMsgTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import javax.mail.Message.RecipientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 短信邮件记录表 服务实现类
 * <p>File：MsgRecordServiceImpl.java </p>
 * <p>Title: MsgRecordServiceImpl </p>
 * <p>Description:MsgRecordServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class SysMsgRecordServiceImpl extends GenericServiceImpl<SysMsgRecord> implements SysMsgRecordService
{
    protected SysMsgRecordMapper msgRecordMapper;

    @Value("${spring.mail.username}")
    protected String          formMail;

    @Autowired(required = false)
    protected JavaMailSender  sender;

//    @Autowired(required = false)
//    private SMSClientUtils smsClientUtils;

    @Autowired(required = false)
    private SysMsgTemplateMapper msgTemplateMapper;

    @Autowired(required = false)
    public SysMsgRecordServiceImpl(SysMsgRecordMapper msgRecordMapper)
    {
        super(msgRecordMapper);
        this.msgRecordMapper = msgRecordMapper;
    }

    @Override
    public void sendSms(String phone, String lang, String type) throws BusinessException
    {
        if (StringUtils.isBlank(phone))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        if (StringUtils.isBlank(type)) type = MessageConst.SMS_VALID_OTHER;
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone)//
                .toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            return;
        }
        SysMsgTemplate template = msgTemplateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_SENDVALICODE, MessageConst.MESSAGE_SMS, lang);
        if (null == template) throw new BusinessException(MessageEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone).toString();
        String randomKey = SerialnoUtils.randomNum(6);
        RedisUtils.putObject(cacheKey, randomKey, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        RedisUtils.putObject(expireKey, phone, 60);// 加入一分钟限制
        String content = String.format(template.getContent(), randomKey);
        SysMsgRecord record = new SysMsgRecord("sms", phone, content, Boolean.FALSE);
        record.setId(SerialnoUtils.buildPrimaryKey());
        record.setCreateDate(CalendarUtils.getCurrentLong());
        new Thread(() -> {
            try
            {
                /* 253短信通道
                SMSResult result = smsClientUtils.sendIntSMS(phone, content);
                if (StringUtils.isNotBlank(result.getMsgid()))
                {// 表示发送成功
                    record.setStatus(Boolean.TRUE);
                }
                */
                // 阿里云短信通道
                try {
                    if (AliyunSmsUtils.sendSmsCode(phone, randomKey))
                    {
                        record.setStatus(Boolean.TRUE);
                    }
                } catch (ClientException e) {
                    e.printStackTrace();
                    record.setStatus(Boolean.FALSE);
                }
            }
            catch (BusinessException e)
            {
                log.error(e.getLocalizedMessage());
            }
            finally
            {
                log.info("sendSms record:{}", record);
                msgRecordMapper.insert(record);
            }
        }).start();
    }

    @Override
    public void sendInformSms(String phone, String lang, String type, String level) throws BusinessException
    {
        if (StringUtils.isBlank(phone))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        if (StringUtils.isBlank(type)) type = MessageConst.SMS_VALID_OTHER;
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone)//
                .toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次短信
            return;
        }
        SysMsgTemplate template = msgTemplateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_SENDVALICODE, MessageConst.MESSAGE_SMS, lang);
        if (null == template) throw new BusinessException(MessageEnums.ERROR_TEMPLATE_NOTEXISTS);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone).toString();
        //String randomKey = SerialnoUtils.randomNum(6);
        RedisUtils.putObject(cacheKey, level, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        RedisUtils.putObject(expireKey, phone, 60);// 加入一分钟限制
        String content = String.format(template.getContent(), level);
        SysMsgRecord record = new SysMsgRecord("sms", phone, content, Boolean.FALSE);
        record.setId(SerialnoUtils.buildPrimaryKey());
        record.setCreateDate(CalendarUtils.getCurrentLong());
        new Thread(() -> {
            try
            {
                /* 253短信通道
                SMSResult result = smsClientUtils.sendIntSMS(phone, content);
                if (StringUtils.isNotBlank(result.getMsgid()))
                {// 表示发送成功
                    record.setStatus(Boolean.TRUE);
                }
                */
                // 阿里云短信通道
                try {
                    if (AliyunSmsUtils.sendSmsCode(phone, level))
                    {
                        record.setStatus(Boolean.TRUE);
                    }
                } catch (ClientException e) {
                    e.printStackTrace();
                    record.setStatus(Boolean.FALSE);
                }
            }
            catch (BusinessException e)
            {
                log.error(e.getLocalizedMessage());
            }
            finally
            {
                log.info("sendInformSms record:{}", record);
                msgRecordMapper.insert(record);
            }
        }).start();
    }

    @Override
    public boolean validSMSCode(String phone, String validCode, String type)
    {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(validCode)) return false;
        if (StringUtils.isBlank(type)) type = MessageConst.SMS_VALID_OTHER;
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone)//
                .toString();
        String sysCode = RedisUtils.get(cacheKey);
        boolean flag = StringUtils.equalsIgnoreCase(validCode, sysCode) ? true : false;
        return flag;
    }

    /**
     * 20240326 15:14:43.268 [Thread-5] ERROR com.anyex.apps.common.service.SysMsgRecordServiceImpl - [lambda$sendEmail$2-244] - Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: smtp.gmail.com, port: 465;
     *   nested exception is:
     * 	javax.net.ssl.SSLHandshakeException: No appropriate protocol (protocol is disabled or cipher suites are inappropriate). Failed messages: javax.mail.MessagingException: Could not connect to SMTP host: smtp.gmail.com, port: 465;
     *   nested exception is:
     * 	javax.net.ssl.SSLHandshakeException: No appropriate protocol (protocol is disabled or cipher suites are inappropriate)
     *
     * 	解决办法：
     *     1:找到 java.security 文件
     *     /usr/local/Wivmall2/jdk1.8.0_361/jre/lib/security/java.security
     *
     *     2:找到文件中存在 jdk.tls.disabledAlgorithm 字符串的位置，并删掉jdk.tls.disabledAlgorithm=SSLv3、TLSv1、TLSv1.1中的SSLv3、TLSv1和TLSv1.1即可
     *     #jdk.tls.disabledAlgorithms=SSLv3, TLSv1, TLSv1.1, RC4, DES, MD5withRSA, \
     *      jdk.tls.disabledAlgorithms=RC4, DES, MD5withRSA, \
     *              DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL, \
     *              include jdk.disabled.namedCurves
     *
     * @param email 邮箱号码
     * @param lang 语言编码
     * @param tplKey 邮件模块key
     * @return
     * @throws BusinessException
     */
    @Override
    public String sendEmail(String email, String lang, String tplKey) throws BusinessException {
        if (StringUtils.isBlank(email)) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        // 排除已经存在的邮件地址
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(tplKey)//
                .append(GlobalConst.SEPARATOR).append(email)//
                .toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey)))
        { // 一分钟内只允许发送一次邮件
            log.error("sendEmail超过一分钟内只允许发送一次邮件限制");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }

        // tplKey
        SysMsgTemplate template = msgTemplateMapper.findByKeyAndLang(tplKey, MessageConst.MESSAGE_EMAIL, lang);
        if (null == template) {
            throw new BusinessException(MessageEnums.ERROR_TEMPLATE_NOTEXISTS);
        }
        String randNum = SerialnoUtils.randomNum(6);
        log.info("sendEmail emailCode:{}", randNum);
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(tplKey)//根据tplKey动态拼接
                .append(GlobalConst.SEPARATOR).append(email)//
                .toString();
        log.info("cacheKey:{} randNum:{}", cacheKey, randNum);
        RedisUtils.putObject(cacheKey, randNum, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        //
        RedisUtils.putObject(expireKey, email, 60);// 加入一分钟限制
        //
        String content = String.format(template.getContent(), randNum);
        SysMsgRecord record = new SysMsgRecord(MessageConst.MESSAGE_EMAIL, email, content, Boolean.FALSE);
        record.setId(SerialnoUtils.buildPrimaryKey());
        record.setCreateDate(CalendarUtils.getCurrentLong());
        //
        new Thread(() -> {
            try {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom(formMail);
                simpleMailMessage.setTo(email);
                simpleMailMessage.setSubject(template.getTitle());
                simpleMailMessage.setText(content);
                sender.send(simpleMailMessage);
                //
                record.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            } finally {
                log.info("sendEmail record:{}", record);
                msgRecordMapper.insert(record);
            }
        }).start();
        //
        return cacheKey;
    }

    @Override
    public boolean validEmailCode(String email, String emailCode, String tplKey) throws BusinessException {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(emailCode)) {
            return false;
        }
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(tplKey)//根据tplKey动态拼接
                .append(GlobalConst.SEPARATOR).append(email)//
                .toString();
        String sysCode = RedisUtils.get(cacheKey);
        log.info("cacheKey:{} randNum:{}", cacheKey, sysCode);
        //
        boolean flag = StringUtils.equalsIgnoreCase(emailCode, sysCode);
        //
        return flag;
    }

    @Override
    public Boolean sendSysAlermEmail(String toEmail, String title, String content) throws BusinessException {
        if (StringUtils.isBlank(toEmail)) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        SysMsgRecord record = new SysMsgRecord(MessageConst.MESSAGE_EMAIL, toEmail, content, Boolean.FALSE);
        record.setId(SerialnoUtils.buildPrimaryKey());
        record.setCreateDate(CalendarUtils.getCurrentLong());

        Address[] addresses = new Address[2];
        try {
            addresses[0] = new InternetAddress("2079953053@qq.com");
            addresses[1] = new InternetAddress("287013644@qq.com");
        } catch (Exception e) {

        }
        //
        new Thread(() -> {
            try {
                MimeMessage simpleMailMessage = sender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(simpleMailMessage, true);
                mimeMessageHelper.setFrom(formMail);
                mimeMessageHelper.setTo(toEmail);
                mimeMessageHelper.setSubject(title);
                mimeMessageHelper.setText(content,true);
                simpleMailMessage.addRecipients(RecipientType.CC, addresses);
                sender.send(simpleMailMessage);
                //
                record.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            } finally {
                log.info("sendEmail record:{}", record);
                msgRecordMapper.insert(record);
            }
        }).start();
        //
        return true;
    }
}
