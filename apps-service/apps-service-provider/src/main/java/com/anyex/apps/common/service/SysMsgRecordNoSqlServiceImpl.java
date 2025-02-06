/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.aliyuncs.exceptions.ClientException;
import com.anyex.apps.aliyun.AliyunSmsUtils;
import com.anyex.apps.bean.GenericNoSqlImpl;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.CalendarUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.entity.SysMsgRecord;
import com.anyex.apps.common.entity.SysMsgTemplate;
import com.anyex.apps.common.enums.MessageEnums;
import com.anyex.apps.common.mapper.SysMsgTemplateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息记录 服务实现类
 * <p>File：MsgRecordNoSqlServiceImpl.java </p>
 * <p>Title: MsgRecordNoSqlServiceImpl </p>
 * <p>Description:MsgRecordNoSqlServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class SysMsgRecordNoSqlServiceImpl extends GenericNoSqlImpl<SysMsgRecord> implements SysMsgRecordNoSqlService {
    @Value("${spring.mail.username}")
    protected String formMail;

    @Autowired(required = false)
    protected JavaMailSender sender;

//    @Autowired(required = false)
//    private SMSClientUtils smsClientUtils;

    @Autowired(required = false)
    private SysMsgTemplateMapper msgTemplateMapper;

    public SysMsgRecordNoSqlServiceImpl() {
        super(SysMsgRecord.class);
    }

    @Override
    public void sendSms(String phone, String lang, String type) throws BusinessException {
        if (StringUtils.isBlank(phone)) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        if (StringUtils.isBlank(type)) {
            type = MessageConst.SMS_VALID_OTHER;
        }
        String expireKey = new StringBuffer(CacheConst.CACHE_EXPIRE_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone)//
                .toString();
        if (StringUtils.isNotBlank(RedisUtils.get(expireKey))) { // 一分钟内只允许发送一次短信
            log.error("sendSms 一分钟内只允许发送一次短信");
            return;
        }
        SysMsgTemplate template = msgTemplateMapper.findByKeyAndLang(MessageConst.TEMPLATE_SMS_SENDVALICODE, MessageConst.MESSAGE_SMS, lang);
        if (null == template) {
            throw new BusinessException(MessageEnums.ERROR_TEMPLATE_NOTEXISTS);
        }
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone).toString();
        String randNum = SerialnoUtils.randomNum(6);
        log.info("sendSms smsCode:{}", randNum);
        RedisUtils.putObject(cacheKey, randNum, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
        RedisUtils.putObject(expireKey, phone, 60);// 加入一分钟限制
        String content = String.format(template.getContent(), randNum);
        SysMsgRecord record = new SysMsgRecord("sms", phone, content, Boolean.FALSE);
        record.setId(SerialnoUtils.buildPrimaryKey());
        record.setCreateDate(CalendarUtils.getCurrentLong());
        new Thread(() -> {
            try {
                // 阿里云短信通道
                try {
                    if (AliyunSmsUtils.sendSmsCode(phone, randNum)) {
                        record.setStatus(Boolean.TRUE);
                    }
                } catch (ClientException e) {
                    e.printStackTrace();
                    record.setStatus(Boolean.FALSE);
                }
            } catch (BusinessException e) {
                log.error(e.getLocalizedMessage());
            } finally {
                log.info("sendSms record:{}", record);
                //mongoTemplate.insert(record, "MsgRecord");
            }
        }).start();
    }

    @Override
    public boolean validSMSCode(String phone, String smsCode, String type) throws BusinessException {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(smsCode)) return false;
        if (StringUtils.isBlank(type)) {
            type = MessageConst.SMS_VALID_OTHER;
        }
        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_SMS_PERFIX)//
                .append(GlobalConst.SEPARATOR).append(type)//
                .append(GlobalConst.SEPARATOR).append(phone)//
                .toString();
        String sysCode = RedisUtils.get(cacheKey);
        //
        boolean flag = StringUtils.equalsIgnoreCase(smsCode, sysCode);
        //
        return flag;
    }

    @Override
    public String sendEmail(String email, String lang, String tplKey) throws BusinessException {
        if (StringUtils.isBlank(email)) {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        // 排除已经存在的邮件地址
        String expireKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX)//
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
        RedisUtils.putObject(cacheKey, randNum, CacheConst.FIFTEEN_MINUTE_CACHE_TIME);
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
                mongoTemplate.insert(record, "MsgRecord");
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
        //
        boolean flag = StringUtils.equalsIgnoreCase(emailCode, sysCode);
        //
        return flag;
    }

    @Override
    public PaginateResult<SysMsgRecord> search(Pagination pagin, SysMsgRecord entity) throws BusinessException {
        Query query = new Query();
        if (StringUtils.isNotBlank(entity.getType())) {
            query.addCriteria(Criteria.where("type").is(entity.getType()));
        }
        if (StringUtils.isNotBlank(entity.getObject())) {
            query.addCriteria(Criteria.where("object").regex(entity.getObject()));
        }
        if (null != entity.getStatus()) {
            query.addCriteria(Criteria.where("status").is(entity.getStatus()));
        }
        if (null != entity.getTimeStart() && null == entity.getTimeEnd()) {
            query.addCriteria(Criteria.where("createDate").gte(entity.getTimeStart()));
        }
        if (null != entity.getTimeEnd()) {
            if (null == entity.getTimeStart()) {
                query.addCriteria(Criteria.where("createDate").lte(entity.getTimeEnd()));
            } else {
                query.addCriteria(Criteria.where("createDate").gte(entity.getTimeStart()).lte(entity.getTimeEnd()));
            }
        }
        //
        pagin.setTotal(mongoTemplate.count(query, "MsgRecord"));
        query.with(PageRequest.of(pagin.getCurrent() - 1, pagin.getSize()));// 分页
        List<SysMsgRecord> data = mongoTemplate.find(query, SysMsgRecord.class, "MsgRecord");
        return new PaginateResult<>(pagin, data);
    }
}
