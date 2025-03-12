package com.anyex.apps.controller.auth;

import com.anyex.apps.account.enums.LoginEnums;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysAppDeviceService;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.reqnew.ReqAccountTokenNew;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.AccountToken;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.RedisUtils;
import com.anyex.apps.utils.ValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * AuthController
 * <p>File：AuthController.java</p>
 * <p>Title: AuthController</p>
 * <p>Description: AuthController</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
//@RestController
//@RequestMapping("/auth")
@Api(tags = "账户登录")
public class AuthNewController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @Autowired(required = false)
    private SysAppDeviceService appDeviceService;

    @PostMapping("/login")
    @ApiOperation(value = "账户登录认证", httpMethod = "POST")
    public JsonMessage login(HttpServletRequest request, @RequestBody ReqAccountTokenNew reqAccountToken) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountToken))
        {
            Subject subject = SecurityUtils.getSubject();
            try
            {
                if (SecurityUtils.getSubject().isAuthenticated())
                { // 如果登录过就直接进入后台
                    Serializable sessinId = subject.getSession().getId();
                    return this.getJsonMessage(CommonEnums.SUCCESS, sessinId);
                }
                //
                //
                if (!ValidateUtils.isMailFormat(reqAccountToken.getEmail(), true, 64))
                {// 验证邮件格式
                    throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
                }
                String ip = NetworkUtils.getIpAddr(request);
                StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
                String captchaText = RedisUtils.get(key.toString());
                //
                if (captchaText == null || !captchaText.equalsIgnoreCase(reqAccountToken.getKaptcha()))
                {// 验证码检验
                    throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
                }

                //
                AccountToken accountToken = new AccountToken();
                accountToken.setLoginType(LoginEnums.EMAIL.getCode());
                accountToken.setUsername(reqAccountToken.getEmail());
                accountToken.setPassword(reqAccountToken.getLoginPwd().toCharArray());
                accountToken.setAuthCode(reqAccountToken.getLoginPwd());
                accountToken.setHost(NetworkUtils.getIpAddr(request));
                log.info("accountToken:{}", accountToken);
                //
                subject.login(accountToken);
                //
            }
            catch (IncorrectCredentialsException ice)
            {
                return this.getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD);
            }
            catch (UnknownAccountException uae)
            {
                return this.getJsonMessage(CommonEnums.ERROR_USER_NOT_EXIST);
            }
            catch (ExcessiveAttemptsException eae)
            {
                return this.getJsonMessage(CommonEnums.ERROR_LOGIN_TIMEOUT);
            }
            catch (AccountPolicyException gae)
            {
                return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK);
            }
            //
            return this.getJsonMessage(CommonEnums.SUCCESS, subject.getSession().getId());
        }
        //
        return json;
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "账户退出认证", httpMethod = "POST")
    public JsonMessage logout() throws BusinessException
    {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        log.info("logout success");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

//    @PostMapping("/login/sms/send")
//    @ApiOperation(value = "短信码发送", httpMethod = "POST")
//    public JsonMessage sendSMS(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, reqSendSms))
//        {
//            Integer ip = NetworkUtils.getIpAddrs(request);
//            StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
//            //
//            String captchaText = RedisUtils.get(key.toString());
//            if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//            {
//                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//            }
//            //
//            Account account = accountService.findByMobile(reqSendSms.getMobile());
//            if (null == account)
//            {
//                throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
//            }
//            //
//            StringBuffer mobileNum = new StringBuffer(GlobalConst.DEFAULT_COUNTRY).append(reqSendSms.getMobile());
//            msgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_LOGIN);
//        }
//        //
//        return json;
//    }

//    @PostMapping("/login/sms/check")
//    @ApiOperation(value = "短信码验证", httpMethod = "POST")
//    public JsonMessage checkSMSCode(HttpServletRequest request, @RequestBody ReqCheckSms reqCheckSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        if (beanValidator(json, reqCheckSms))
//        {
//            StringBuffer mobileNum = new StringBuffer(GlobalConst.DEFAULT_COUNTRY).append(reqCheckSms.getMobile());
//            if (!msgRecordService.validSMSCode(mobileNum.toString(), reqCheckSms.getSmsCode(), MessageConst.SMS_VALID_LOGIN))
//            {// 验证短信码
//                return getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//            }
//        }
//        //
//        return json;
//    }
}
