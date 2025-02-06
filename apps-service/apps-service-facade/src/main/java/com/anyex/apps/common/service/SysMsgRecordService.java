/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.common.entity.SysMsgRecord;

/**
 * 短信邮件记录表 服务接口
 * <p>File：MsgRecordService.java </p>
 * <p>Title: MsgRecordService </p>
 * <p>Description:MsgRecordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface SysMsgRecordService extends GenericService<SysMsgRecord>
{
    /**
    * 发送手机验证码
    * @param phone 手机号
    * @param lang 语言编码
    * @param type 短信类别
    * @throws BusinessException
    */
    void sendSms(String phone, String lang, String type) throws BusinessException;

    /**
    * 发送手机提示短信
    * @param phone 手机号
    * @param lang 语言编码
    * @param type 短信类别
    * @param level 传参
    * @throws BusinessException
    */
    void sendInformSms(String phone, String lang, String type, String level) throws BusinessException;

    /**
     * 验证手机验证码
     * @param phone
     * @param validCode
     * @param type 短信类别
     * @return {@link Boolean}
     */
    boolean validSMSCode(String phone, String validCode, String type);

    /**
     * 发送邮件验证码
     * @param email 邮箱号码
     * @param lang 语言编码
     * @param tplKey 邮件模块key
     * @throws BusinessException
     */
    String sendEmail(String email, String lang, String tplKey) throws BusinessException;

    /**
     * 验证邮箱验证码
     * @param email 邮箱号码
     * @param emailCode 验证码
     * @param tplKey 邮件模块key
     * @return {@link Boolean}
     */
    boolean validEmailCode(String email, String emailCode, String tplKey) throws BusinessException;


    Boolean sendSysAlermEmail(String toEmail, String title,String content) throws BusinessException;
}
