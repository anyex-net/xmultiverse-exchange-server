package com.anyex.apps.controller.auth;

import javax.servlet.http.HttpServletRequest;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户忘记密码控制器
 * <p>File：ForgetPassController.java</p>
 * <p>Title: ForgetPassController</p>
 * <p>Description: ForgetPassController</p>
 * <p>Copyright: Copyright (c) 2019/10/22</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/forget")
@Api(tags = "账户忘记密码")
public class ForgetPassController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @PostMapping("/email/send")
    @ApiOperation(value = "邮箱码发送(找回密码)", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 60, isLogin = false) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage sendEmail(HttpServletRequest request, @RequestBody ReqSendEmail reqSendEmail) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info(reqSendEmail.toString());
        //
        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        //
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getKaptcha()))
//        {// 验证码检验
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
        //
        Account account = accountService.findByEmail(reqSendEmail.getEmail());
        if (null == account)
        {
            throw new BusinessException(CommonEnums.ERROR_ACCOUNT_NOT_EXIST);
        }
        //
        msgRecordService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE);
        //
        return json;
    }

    @PostMapping("/email/check")
    @ApiOperation(value = "邮箱码验证", httpMethod = "POST")
    public JsonMessage checkEmail(HttpServletRequest request, @RequestBody ReqCheckEmail reqCheckEmail) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        //
        if (!ValidateUtils.isMailFormat(reqCheckEmail.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
        if (!msgRecordService.validEmailCode(reqCheckEmail.getEmail(), reqCheckEmail.getEmailCode(), MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE))
        {// 验证邮箱码
            return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
        }
        //
        // 用来最后一步重置密码
        String ip = NetworkUtils.getIpAddr(request);
        String cacheKey = new StringBuffer(CacheConst.FIND_PASSWD_PERFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(ip).toString();
        RedisUtils.putObject(cacheKey, reqCheckEmail.getEmail(), CacheConst.ONE_HOUR_CACHE_TIME);// 缓存超时设置为60分钟
        //
        //
        return json;
    }

    @PostMapping("/email/resetpass")
    @ApiOperation(value = "邮箱重置密码", httpMethod = "POST")
    public JsonMessage emailResetPass(HttpServletRequest request, @RequestBody ReqEmailResetpass reqEmailResetpass) throws BusinessException
    {
        //
        String ip = NetworkUtils.getIpAddr(request);
        String cacheKey = new StringBuffer(CacheConst.FIND_PASSWD_PERFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(ip).toString();
        String emailInCache = RedisUtils.get(cacheKey);
        if (StringUtils.isBlank(emailInCache))
        {
            log.error("邮箱重置密码 没找到对应的redis缓存 cacheKey:{}", cacheKey);
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
        //
        Account account = accountService.findByEmail(reqEmailResetpass.getEmail());
        if(null == account) {
            log.error("邮箱重置密码 没找到对应的账户信息");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        account.setLoginPwd(EncryptUtils.entryptPassword(reqEmailResetpass.getLoginPwd()));
        log.info("emailResetPass:{}", account.toString());
        accountService.updateByPrimaryKeySelective(account);
        //
        RedisUtils.del(cacheKey);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

//    @PostMapping("/sms/send")
//    @ApiOperation(value = "短信码发送(找回密码)", httpMethod = "POST")
//    public JsonMessage sendSMS(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        //
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//        {// 验证码检验
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
//        //
//        Account account = accountService.findByMobile(reqSendSms.getMobile());
//        if (null == account)
//        {
//            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
//        }
//        //
//        StringBuffer phoneNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobile());
//        msgRecordService.sendSms(phoneNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_FORGETPASS);
//        //
//        return json;
//    }
//
//    @PostMapping("/sms/check")
//    @ApiOperation(value = "短信码验证", httpMethod = "POST")
//    public JsonMessage checkSMS(HttpServletRequest request, @RequestBody ReqCheckSms reqCheckSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        StringBuffer phoneNum = new StringBuffer(reqCheckSms.getCountry()).append(reqCheckSms.getMobile());
//        if (!msgRecordService.validSMSCode(phoneNum.toString(), reqCheckSms.getSmsCode(), MessageConst.SMS_VALID_FORGETPASS))
//        {// 验证短信码
//            return getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//        }
//        // 用来最后一步重置密码
//        String ip = NetworkUtils.getIpAddr(request);
//        String cacheKey = new StringBuffer(CacheConst.FIND_PASSWD_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(ip).toString();
//        RedisUtils.putObject(cacheKey, reqCheckSms.getMobile(), CacheConst.ONE_HOUR_CACHE_TIME);// 缓存超时设置为60分钟
//        //
//        return json;
//    }
//
//    @PostMapping("/sms/resetpass")
//    @ApiOperation(value = "短信重置密码", httpMethod = "POST")
//    public JsonMessage smsResetPass(HttpServletRequest request, @RequestBody ReqSmsResetpass reqSmsResetpass) throws BusinessException
//    {
//        String ip = NetworkUtils.getIpAddr(request);
//        String cacheKey = new StringBuffer(CacheConst.FIND_PASSWD_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(ip).toString();
//        String phoneNum = RedisUtils.get(cacheKey);
//        if (StringUtils.isBlank(phoneNum))
//        {
//            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
//        }
//        //
//        Account account = accountService.findByMobile(reqSmsResetpass.getMobile());
//        account.setLoginPwd(EncryptUtils.entryptPassword(reqSmsResetpass.getLoginPwd()));
//        log.info(account.toString());
//        accountService.updateByPrimaryKeySelective(account);
//        //
//        RedisUtils.del(cacheKey);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
