package com.anyex.apps.controller.openim;/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *//*

package com.anyex.apps.controller.openim;

import com.anyex.apps.account.consts.AccountConst;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.enums.LoginEnums;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.account.service.AttributeService;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.req.ReqAccountRegister;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.openim.chat.account.req.*;
import com.anyex.apps.openim.chat.account.resp.LoginResp;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.AccountToken;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.*;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
import com.anyex.openim.base.OpenImResult;
import com.anyex.openim.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM + "/old/account")
@Api(tags = "账户管理")
public class OpenImChatAccountController extends GenericController {
    @Autowired(required = false)
    private OpenImChatService openImChatService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @Autowired(required = false)
    private AttributeService attributeService;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private String userMsgCodePrifix = "msg:code:%s";

    @PostMapping(value = "/code/send")
    @ApiOperation(value = "发送验证码", httpMethod = "POST")
    public OpenImResult<String> codeSend(@Validated @RequestBody SendVerifyCodeReq req) throws BusinessException {
        ValidatorUtils.validate(req);

        if (!ValidateUtils.isMobileFormat(req.getPhoneNumber(), true, 64)) {
            throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
        }
        // 1注册 2找回密码 3登录
        switch (req.getUsedFor().intValue()) {
            case 1: {
                Account account = accountService.findByMobile(req.getPhoneNumber());
                if (null != account) {
                    throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
                }
                RedisUtils.putObject(String.format(userMsgCodePrifix,req.getPhoneNumber() ), "666666", 60 * 5);
                // redisTemplate.opsForValue().set(req.getPhoneNumber(), "666666", 60 * 5);
                // msgRecordService.sendSms(req.getAreaCode()+req.getPhoneNumber(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
            }
            break;
            case 2:
                */
/*{
                    Account account = accountService.findByMobile(req.getPhoneNumber());
                    if (null == account)
                    {
                        throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
                    }
                    // msgRecordService.sendSms(req.getAreaCode()+req.getPhoneNumber(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
                }
                break;*//*

            case 3: {
                Account account = accountService.findByMobile(req.getPhoneNumber());
                if (null == account) {
                    throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
                }
                // msgRecordService.sendSms(req.getAreaCode()+req.getPhoneNumber(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
            }
            break;
            default:
                throw new BusinessException("userfor error");
        }
        OpenImResult<String> openImResult = new OpenImResult<String>();
        openImResult.success();
        return openImResult;
    }

    @PostMapping(value = "/code/verify")
    @ApiOperation(value = "校验验证码", httpMethod = "POST")
    public OpenImResult<String> codeVerify(@Validated @RequestBody VerifyCodeReq req) throws BusinessException {
       */
/* Account account = accountService.findByMobile(req.getPhoneNumber());
        if (null == account) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }*//*

        // 需要补充账户的存在逻辑 是判断缓存还是数据库
        // String verifyCode = (String)redisTemplate.opsForValue().get(req.getPhoneNumber());
        String verifyCode = RedisUtils.get(String.format(userMsgCodePrifix,req.getPhoneNumber() ));
        if(null == verifyCode){
            OpenImResult<String> openImResult = new OpenImResult<String>();
            openImResult.setErrCode(9999);
            openImResult.setErrMsg("验证码过期");
            return openImResult;
        }
        if (verifyCode.equals(req.getVerifyCode())) {
            RedisUtils.del(String.format(userMsgCodePrifix,req.getPhoneNumber() ));
            OpenImResult<String> openImResult = new OpenImResult<String>();
            openImResult.success();
            return openImResult;
        } else {
            OpenImResult<String> openImResult = new OpenImResult<String>();
            openImResult.setErrCode(9999);
            openImResult.setErrMsg("验证码错误");
            return openImResult;
        }
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登入", httpMethod = "POST")
    public OpenImResult<LoginResp> login(HttpServletRequest request, @Validated @RequestBody LoginReq req) throws BusinessException {
        ValidatorUtils.validate(req);

        Account account = null;
        // 手机号登录
        if(StringUtils.isNotEmpty(req.getAreaCode())&&StringUtils.isNotEmpty(req.getPhoneNumber()))
        {
            account = accountService.findByMobile(req.getPhoneNumber());
            if (null == account) {
                throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
            }
            if (!ValidateUtils.isMobileFormat(req.getPhoneNumber(), true, 20)) {
                throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
            }
        }
        else
        {
            account = accountService.findByEmail(req.getEmail());
            if (null == account) {
                throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
            }
            if (!ValidateUtils.isMailFormat(req.getEmail(), true, 64)) {
                throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
            }
        }

        Subject subject = SecurityUtils.getSubject();
        OpenImResult<LoginResp> openImResult = new OpenImResult<LoginResp>();
        LoginResp ret = new LoginResp();
        openImResult.success();
        try {
            if (SecurityUtils.getSubject().isAuthenticated()) {
                UserPrincipal principal = OnLineUserUtils.getPrincipal();
                Serializable sessinId = subject.getSession().getId();
                ret.setChatToken(sessinId.toString());
                ret.setImToken(getUserImToken(req.getPlatform(), account.getUserId()));
                ret.setUserID(account.getUserId());
                principal.setImToken(ret.getImToken());
                openImResult.setData(ret);
                return openImResult;
            }

            String ip = NetworkUtils.getIpAddr(request);
            StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
           */
/* String captchaText = RedisUtils.get(key.toString());
            if (captchaText == null || !captchaText.equalsIgnoreCase(req.getVerifyCode())) {
                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
            }*//*


            AccountToken accountToken = new AccountToken();
            accountToken.setLoginType(StringUtils.isNotEmpty(req.getAreaCode())?LoginEnums.MOBILELOGIN.getCode():LoginEnums.EMAILLOGIN.getCode());
            accountToken.setUsername(StringUtils.isNotEmpty(req.getAreaCode())?req.getPhoneNumber():req.getEmail());
            accountToken.setPassword(req.getPassword().toCharArray());
            accountToken.setAuthCode(req.getVerifyCode());
            accountToken.setHost(NetworkUtils.getIpAddr(request));
            log.info("accountToken:{}", accountToken);
            //
            subject.login(accountToken);
            //
        } catch (IncorrectCredentialsException ice) {
            openImResult.setErrCode(CommonEnums.ERROR_LOGIN_PASSWORD.code);
            openImResult.setErrMsg(CommonEnums.ERROR_LOGIN_PASSWORD.message);
            return openImResult;
        } catch (UnknownAccountException uae) {
            openImResult.setErrCode(CommonEnums.ERROR_LOGIN_ACCOUNT.code);
            openImResult.setErrMsg(CommonEnums.ERROR_LOGIN_ACCOUNT.message);
            return openImResult;
        } catch (ExcessiveAttemptsException eae) {
            openImResult.setErrCode(CommonEnums.ERROR_LOGIN_TIMEOUT.code);
            openImResult.setErrMsg(CommonEnums.ERROR_LOGIN_TIMEOUT.message);
            return openImResult;
        } catch (AccountPolicyException gae) {
            openImResult.setErrCode(CommonEnums.NEED_POLICY_CHECK.code);
            openImResult.setErrMsg(CommonEnums.NEED_POLICY_CHECK.message);
            return openImResult;
        }

        ret.setChatToken(subject.getSession().getId().toString());
        ret.setImToken(getUserImToken(req.getPlatform(), account.getUserId()));
        ret.setUserID(account.getUserId());
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        principal.setImToken(ret.getImToken());
        openImResult.setData(ret);
        log.info(ret.toString());
        return openImResult;
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册", httpMethod = "POST")
    public OpenImResult<LoginResp> register(HttpServletRequest request, @Validated @RequestBody RegisterUserReq req) throws BusinessException {
        ValidatorUtils.validate(req);
        Account account = null;
                OpenImResult<LoginResp> openImResult = new OpenImResult<LoginResp>();
        if(StringUtils.isNotEmpty(req.getUser().getAreaCode())&&StringUtils.isNotEmpty(req.getUser().getPhoneNumber()))
        {
            account = accountService.findByMobile(req.getUser().getPhoneNumber());
            if (null != account) {
                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
            }
            if (!ValidateUtils.isMobileFormat(req.getUser().getPhoneNumber(), true, 20)) {
                throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
            }
        }
        else
        {
            account = accountService.findByEmail(req.getUser().getEmail());
            if (null != account) {
                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
            }
            if (!ValidateUtils.isMailFormat(req.getUser().getEmail(), true, 64)) {
                throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
            }
        }

        if(req.getUser().getEmail() == null)
        {
            req.getUser().setEmail("");
        }

        //TODO 验证码暂时定为固定的666666
        if ("666666".equals(req.getVerifyCode())) {
            openImResult.success();

            ReqAccountRegister reqAccountRegister = new ReqAccountRegister();
            reqAccountRegister.setEmail(null);
            reqAccountRegister.setEmailCode(null);
            reqAccountRegister.setDeviceId(req.getDeviceID());
            reqAccountRegister.setSource("IM" + req.getPlatform().toString());

            if (StringUtils.isNotEmpty(req.getInvitationCode())) {
                Account accountReferral = accountService.findByUnid(Long.valueOf(req.getInvitationCode()));
                log.info("accountReferral:{}", accountReferral);
                if (null == accountReferral) {
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
            account.setCountry(req.getUser().getAreaCode());
            //account.setMobile();
            account.setAccountName(req.getUser().getNickname());
            //account.setRealName();
            //account.setCnic();
            account.setLoginPwd(EncryptUtils.entryptPassword(req.getUser().getPassword()));
            account.setHeadUrl("headUrl");
            account.setEmail(req.getUser().getEmail());
            account.setMobile(req.getUser().getPhoneNumber());
            account.setCountry(req.getUser().getAreaCode());
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
            account.setUserId(StringUtils.isNotEmpty(req.getUser().getAreaCode())?account.getMobile():account.getUnid().toString());
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

            LoginReq req2 = new LoginReq();
            req2.setAreaCode(req.getUser().getAreaCode());
            req2.setPhoneNumber(req.getUser().getPhoneNumber());
            req2.setEmail(req.getUser().getEmail());
           // req2.setAccount(req.getUser().getAccount());
            req2.setPassword(req.getUser().getPassword());
            req2.setVerifyCode(req.getVerifyCode());
            req2.setDeviceID(req.getDeviceID());
            req2.setPlatform(req.getPlatform());
           // req2.setIp(req.getIp());
            return login(request, req2);
        } else {
            openImResult.setErrCode(9999);
            openImResult.setErrMsg("验证码错误");
            return openImResult;
        }
    }

    @PostMapping(value = "/password/reset")
    @ApiOperation(value = "忘记密码", httpMethod = "POST")
    public OpenImResult<String> passwordReset(@Validated @RequestBody ResetPasswordReq req) throws BusinessException {
        ValidatorUtils.validate(req);
        Account account = accountService.findByMobile(req.getPhoneNumber());
        if (null == account) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }

        if (!ValidateUtils.isMobileFormat(req.getPhoneNumber(), true, 20)) {
            throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
        }
        OpenImResult<String> openImResult = new OpenImResult<String>();

        //TODO 验证码暂时定为固定的666666
        if ("666666".equals(req.getVerifyCode())) {
            openImResult.success();
            if(StringUtils.isEmpty(req.getPassword()))
            {
                throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
            }
            account.setLoginPwd(EncryptUtils.entryptPassword(req.getPassword()));
            accountService.updateByPrimaryKey(account);
            forceLogout(account.getUserId());
            return openImResult;
        } else {
            openImResult.setErrCode(9999);
            openImResult.setErrMsg("验证码错误");
            return openImResult;
        }
    }

    @PostMapping(value = "/password/change")
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    public OpenImResult<String> passwordChange(@Validated @RequestBody ChangePasswordReq req) throws BusinessException {
        ValidatorUtils.validate(req);
        Account account = accountService.findByUserId(req.getUserID());
        if (null == account) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        if (!EncryptUtils.validatePassword(String.valueOf(req.getCurrentPassword()), account.getLoginPwd())) {
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        OpenImResult<String> openImResult = new OpenImResult<String>();
        openImResult.success();
        account.setLoginPwd(EncryptUtils.entryptPassword(req.getNewPassword()));
        accountService.updateByPrimaryKey(account);
        forceLogout(account.getUserId());
        return openImResult;
    }

    */
/**
     * 获取用户的IMtoken
     *
     * @param
     * @return
     *//*

    private String getUserImToken(Integer platform, String userId) {
        GetUserTokenReq req = new GetUserTokenReq();
        req.setUserID(userId);
        req.setPlatformID(platform);
        return openImApiService.getImToken(req);
    }

    */
/**
     * 强制退出登录 各个客户端
     * @param userId
     *//*

    private void forceLogout(String userId) {
        for (int i = 1; i <= 10; i++) {
            openImApiService.forceLogout(i, userId);
        }
    }

}
*/
