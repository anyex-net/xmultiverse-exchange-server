package com.anyex.apps.controller.auth;

import com.anyex.apps.account.consts.AccountConst;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.reqnew.ReqAccountRegisterNew;
import com.anyex.apps.controller.auth.reqnew.ReqSendEmailNew;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 账户注册控制器
 * <p>File：RegisterController.java</p>
 * <p>Title: RegisterController</p>
 * <p>Description: RegisterController</p>
 * <p>Copyright: Copyright (c) 2017/12/18</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/register")
@Api(tags = "账户注册")
public class RegisterNewController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @PostMapping("/email/send")
    @ApiOperation(value = "邮箱码发送", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 60, isLogin = false) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage sendEmail(HttpServletRequest request, @RequestBody ReqSendEmailNew reqSendEmail) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info(reqSendEmail.toString());
        //
        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        //
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getKaptcha()))
//        {// 验证码检验
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
        //
        Account account = accountService.findByEmail4Register(reqSendEmail.getEmail());
        if (null != account)
        {
            throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
        }
        //
        msgRecordService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
        //
        return json;
    }

    @PostMapping("/account/register")
    @ApiOperation(value = "账户注册", httpMethod = "POST")
    public JsonMessage accountRegister(HttpServletRequest request, @RequestBody ReqAccountRegisterNew reqAccountRegister) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        //
        if (beanValidator(json, reqAccountRegister))
        {
            //
            if (!ValidateUtils.isMailFormat(reqAccountRegister.getEmail(), true, 64))
            {// 验证邮件格式
                throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
            }
            if (!msgRecordService.validEmailCode(reqAccountRegister.getEmail(), reqAccountRegister.getEmailCode(), MessageConst.TEMPLATE_EMAIL_REGISTERCODE))
            {// 验证邮箱码
                return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
            }
            //
            Account account = accountService.findByEmail4Register(reqAccountRegister.getEmail());
            if (null != account)
            {
                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
            }
            //
            log.info("reqAccountRegister:{}", reqAccountRegister);
            if(StringUtils.isNotEmpty(reqAccountRegister.getReferralCode())) {
                Account accountReferral = accountService.findByUnid(Long.valueOf(reqAccountRegister.getReferralCode()));
                log.info("accountReferral:{}", accountReferral);
                if (null == accountReferral)
                {
                    log.error("邀请码对应账户不存在,不能进行新账户注册");
                    //throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
                    reqAccountRegister.setReferralCode(null);
                }
            }
            //
            Long maxUID = accountService.getMaxUNID();
            account = new Account();
            account.setId(SerialnoUtils.buildPrimaryKey());
            account.setUnid(maxUID + 1);
            account.setCountry(GlobalConst.DEFAULT_COUNTRY);
            //account.setMobile();
            //account.setAccountName();
            //account.setRealName();
            //account.setCnic();
            account.setLoginPwd(EncryptUtils.entryptPassword(reqAccountRegister.getLoginPwd()));
            account.setHeadUrl("headUrl");
            account.setEmail(reqAccountRegister.getEmail());
            //account.setBirth();
            //account.setGender();
            account.setInvitationCode(String.valueOf(account.getUnid()));
            account.setReferralCode(reqAccountRegister.getReferralCode());
            account.setDeviceId(reqAccountRegister.getDeviceId());
            account.setIp(NetworkUtils.getIpAddr(request));
            account.setLat(reqAccountRegister.getLat());
            account.setLng(reqAccountRegister.getLng());
            account.setSource(reqAccountRegister.getSource());
            account.setStatus(AccountConst.ACCOUNT_STATUS_NORMAL);
            account.setRemark("newAccountRegister");
            account.setCreateTime(System.currentTimeMillis());
            log.info("accountRegister account:{}", account.toString());

            //
            WalletAsset walletAsset = new WalletAsset();
            walletAsset.setAccountId(account.getId());
            walletAsset.setCurrency(GlobalConst.CURRENCY_PKR);
            walletAsset.setBalance(BigDecimal.ZERO);
            walletAsset.setFrozenBal(BigDecimal.ZERO);
            walletAsset.setUpdateTime(System.currentTimeMillis());
            walletAsset.setRemark("walletAsset init");
            log.info("accountRegister walletAsset init:{}", walletAsset);

            //
            accountService.register(account, walletAsset);
        }
        //
        return json;
    }

//    @PostMapping("/email/check")
//    @ApiOperation(value = "邮箱码验证", httpMethod = "POST")
//    public JsonMessage checkEmail(HttpServletRequest request, @RequestBody ReqCheckEmail reqCheckEmail) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        if (!ValidateUtils.isMailFormat(reqCheckEmail.getEmail(), true, 64))
//        {// 验证邮件格式
//            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
//        }
//        if (!msgRecordService.validEmailCode(reqCheckEmail.getEmail(), reqCheckEmail.getEmailCode(), MessageConst.TEMPLATE_EMAIL_REGISTERCODE))
//        {// 验证邮箱码
//            return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
//        }
//        //
//        return json;
//    }

//    @PostMapping("/sms/send")
//    @ApiOperation(value = "短信码发送", httpMethod = "POST")
//    public JsonMessage sendSMS(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        if (beanValidator(json, reqSendSms))
//        {
//            String ip = NetworkUtils.getIpAddr(request);
//            StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip);
//            String captchaText = RedisUtils.get(key.toString());
//            if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//            {// 验证码检验
//                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//            }
//            Account account = accountService.findByMobile(reqSendSms.getMobile());
//            if (null != account)
//            {
//                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
//            }
//            //
//            StringBuffer mobileNum = new StringBuffer(GlobalConst.DEFAULT_COUNTRY).append(reqSendSms.getMobile());
//            msgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_REGISTER);
//        }
//        //
//        return json;
//    }
//
//    @PostMapping("/sms/check")
//    @ApiOperation(value = "短信码验证", httpMethod = "POST")
//    public JsonMessage checkSMSCode(HttpServletRequest request, @RequestBody ReqCheckSms reqCheckSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        if (beanValidator(json, reqCheckSms))
//        {
//            StringBuffer mobileNum = new StringBuffer(GlobalConst.DEFAULT_COUNTRY).append(reqCheckSms.getMobile());
//            if (!msgRecordService.validSMSCode(mobileNum.toString(), reqCheckSms.getSmsCode(), MessageConst.SMS_VALID_REGISTER))
//            {// 验证短信码
//                return getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//            }
//        }
//        //
//        return json;
//    }
}
