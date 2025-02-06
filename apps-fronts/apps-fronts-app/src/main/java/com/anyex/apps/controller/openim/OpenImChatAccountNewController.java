/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.account.consts.AccountConst;
import com.anyex.apps.account.entity.*;
import com.anyex.apps.account.enums.LoginEnums;
import com.anyex.apps.account.service.*;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.req.ReqAccountRegister;
import com.anyex.apps.controller.social.req.ReqPublicModel;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.AccountPolicyException;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.chat.account.req.*;
import com.anyex.apps.openim.chat.account.resp.LoginResp;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.openim.service.OpenImChatService;
import com.anyex.apps.shiro.model.AccountToken;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.*;
import com.anyex.openim.api.auth.req.GetUserTokenReq;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(GlobalConst.IM + "/account")
@Api(tags = "账户管理")
public class OpenImChatAccountNewController extends GenericController {
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

    @Autowired(required = false)
    private AccountInviteStatisticsService accountInviteStatisticsService;

    @Autowired(required = false)
    private AccountInviteRewardsDetailService accountInviteRewardsDetailService;

    @Autowired(required = false)
    private AccountSignInInfoService accountSignInInfoService;

    @Autowired(required = false)
    private AccountSignInDetailService accountSignInDetailService;


    String registerCodeSend(SendVerifyCodeReq req) throws BusinessException
    {
        String act = "";

        if(StringUtils.isNotEmpty(req.getAreaCode()))
        {
            Account account = accountService.findByMobile(req.getPhoneNumber());
            if (null != account) {
                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
            }
            if (!ValidateUtils.isMobileFormat(req.getPhoneNumber(), true, 64)) {
                throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
            }
            act = req.getPhoneNumber();
        }
        else
        {
            Account account = accountService.findByEmail(req.getEmail());
            if (null != account) {
                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
            }
            act = req.getEmail();

        }
        return act;
    }

    String loginCodeSend(SendVerifyCodeReq req) throws BusinessException
    {
        String act = "";

        if(StringUtils.isNotEmpty(req.getAreaCode()))
        {
            Account account = accountService.findByMobile(req.getPhoneNumber());
            if (null == account) {
                throw new BusinessException(CommonEnums.ERROR_ACCOUNT_NOT_EXIST);
            }
            if (!ValidateUtils.isMobileFormat(req.getPhoneNumber(), true, 64)) {
                throw new BusinessException(CommonEnums.ERROR_MOBILE_VALID_FAILED);
            }
            act = req.getPhoneNumber();
        }
        else
        {
            Account account = accountService.findByEmail(req.getEmail());
            if (null == account) {
                throw new BusinessException(CommonEnums.ERROR_ACCOUNT_NOT_EXIST);
            }
            act = req.getEmail();

        }
        return act;
    }


    @PostMapping(value = "/code/send")
    @ApiOperation(value = "发送验证码", httpMethod = "POST")
    @AccessLimit(limit = 5, timeScope = 60, isLogin = false)
    public JsonMessage<String> codeSend(@Validated @RequestBody SendVerifyCodeReq req) throws BusinessException {

        // 1注册 2找回密码 3登录
        switch (req.getUsedFor().intValue()) {
            case 1: {
                registerCodeSend(req);
                if(StringUtils.isEmpty(req.getAreaCode()))
                {
                    msgRecordService.sendEmail(req.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
                }
                else
                {
                   // TODO 请在此补充短信验证码发送
                }
            }
            break;
            case 2:
                // RedisUtils.putObject(String.format(userMsgCodePrifix,loginCodeSend(req) ), "666666", 60 * 5);
                loginCodeSend(req);
                if(StringUtils.isEmpty(req.getAreaCode()))
                {
                    msgRecordService.sendEmail(req.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE);
                }
                else
                {
                    // TODO 请在此补充短信验证码发送
                }
            break;
            default:
                throw new BusinessException("userfor error");
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/code/verify")
    @ApiOperation(value = "校验验证码", httpMethod = "POST")
    @AccessLimit(limit = 2, timeScope = 5, isLogin = false)
    public JsonMessage<String> codeVerify(@Validated @RequestBody VerifyCodeReq req) throws BusinessException {

        // 1注册 2找回密码 3登录
        switch (req.getUsedFor().intValue()) {
            case 1: {

                if(StringUtils.isEmpty(req.getAreaCode()))
                {
                    if (!msgRecordService.validEmailCode(req.getEmail(), req.getVerifyCode(), MessageConst.TEMPLATE_EMAIL_REGISTERCODE))
                    {// 验证邮箱码
                        return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
                    }
                }
                else
                {
                    // TODO 请在此修改短信验证码的校验逻辑
                    if(!StringUtils.equalsAnyIgnoreCase(req.getVerifyCode(),"666666"))
                    {
                        throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
                    }

                }
            }
            break;
            case 2:
            {
                if(StringUtils.isEmpty(req.getAreaCode()))
                {
                    if (!msgRecordService.validEmailCode(req.getEmail(), req.getVerifyCode(), MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE))
                    {// 验证邮箱码
                        return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
                    }
                }
                else
                {
                    // TODO 请在此修改短信验证码的校验逻辑
                    if(!StringUtils.equalsAnyIgnoreCase(req.getVerifyCode(),"666666"))
                    {
                        throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
                    }

                }
            }
                   break;
            default:
                throw new BusinessException("userfor error");
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登入", httpMethod = "POST")
    @AccessLimit(limit = 5, timeScope = 60, isLogin = false)
    public JsonMessage<LoginResp> login(HttpServletRequest request, @Validated @RequestBody LoginReq req) throws BusinessException {
        // ValidatorUtils.validate(req);

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
        LoginResp ret = new LoginResp();
        try {
            if (SecurityUtils.getSubject().isAuthenticated()) {
                UserPrincipal principal = OnLineUserUtils.getPrincipal();
                Serializable sessinId = subject.getSession().getId();
                ret.setChatToken(sessinId.toString());
                ret.setImToken(getUserImToken(req.getPlatform(), account.getUserId()));
                ret.setUserID(account.getUserId());
                ret.setNickname(account.getAccountName());
                ret.setFaceUrl(account.getHeadUrl());
                ret.setInvitationCode(account.getInvitationCode().toString());
                principal.setImToken(ret.getImToken());
                return this.getJsonMessage(CommonEnums.SUCCESS,ret);
            }

            String ip = NetworkUtils.getIpAddr(request);
            StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
           /* String captchaText = RedisUtils.get(key.toString());
            if (captchaText == null || !captchaText.equalsIgnoreCase(req.getVerifyCode())) {
                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
            }*/

            AccountToken accountToken = new AccountToken();
            accountToken.setLoginType(StringUtils.isNotEmpty(req.getAreaCode())?LoginEnums.MOBILEPASS.getCode():LoginEnums.EMAILPASS.getCode());
            accountToken.setUsername(StringUtils.isNotEmpty(req.getAreaCode())?req.getPhoneNumber():req.getEmail());
            accountToken.setPassword(req.getPassword().toCharArray());
            accountToken.setAuthCode(req.getVerifyCode());
            accountToken.setHost(NetworkUtils.getIpAddr(request));
            log.info("accountToken:{}", accountToken);
            //
            subject.login(accountToken);
            //
        } catch (IncorrectCredentialsException ice) {
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        } catch (UnknownAccountException uae) {
            throw new BusinessException(CommonEnums.ERROR_ACCOUNT_NOT_EXIST);
        } catch (ExcessiveAttemptsException eae) {
            throw new BusinessException(CommonEnums.ERROR_LOGIN_TIMEOUT);
        } catch (AccountPolicyException gae) {
            throw new BusinessException(CommonEnums.NEED_POLICY_CHECK);
        }

        ret.setChatToken(subject.getSession().getId().toString());
        ret.setImToken(getUserImToken(req.getPlatform(), account.getUserId()));
        ret.setUserID(account.getUserId());
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        ret.setNickname(account.getAccountName());
        ret.setFaceUrl(account.getHeadUrl());
        principal.setImToken(ret.getImToken());
        ret.setInvitationCode(account.getInvitationCode().toString());
        log.info(ret.toString());
        return this.getJsonMessage(CommonEnums.SUCCESS,ret);
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册", httpMethod = "POST")
    public JsonMessage<LoginResp> register(HttpServletRequest request, @Validated @RequestBody RegisterUserReq req) throws BusinessException {
        // ValidatorUtils.validate(req);

            //Date bir = DateUtils.parseDate(req.getUser().getBirthday(), "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 14);
        if (req.getUser().getBirthday() > calendar.getTime().getTime()) {
            throw new BusinessException(CommonEnums.FAIL.code, "Not allowed to register under the age of 14");
        }
        Account account = null;
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
            if (!msgRecordService.validEmailCode(req.getUser().getEmail(), req.getVerifyCode(), MessageConst.TEMPLATE_EMAIL_REGISTERCODE))
            {// 验证邮箱码
                return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
            }
        }

        if(req.getUser().getEmail() == null)
        {
            req.getUser().setEmail("");
        }

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
            else
            {
                reqAccountRegister.setReferralCode(req.getInvitationCode());
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
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(req.getUser().getBirthday());
        account.setBirth(DateUtils.formatDate(cl.getTime(), "yyyy-MM-dd"));
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
        account.setUserId(account.getUnid().toString());
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
        req2.setPassword(req.getUser().getPassword());
        req2.setVerifyCode(req.getVerifyCode());
        req2.setDeviceID(req.getDeviceID());
        req2.setPlatform(req.getPlatform());
        return login(request, req2);
    }

    @PostMapping(value = "/password/reset")
    @ApiOperation(value = "忘记密码", httpMethod = "POST")
    @AccessLimit(limit = 2, timeScope = 5, isLogin = false)
    public JsonMessage<String> passwordReset(@Validated @RequestBody ResetPasswordReq req) throws BusinessException {
        // ValidatorUtils.validate(req);
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
            // TODO 请在此修改短信验证码的校验逻辑
            if(!StringUtils.equalsAnyIgnoreCase(req.getVerifyCode(),"666666"))
            {
                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
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
            if (!msgRecordService.validEmailCode(req.getEmail(), req.getVerifyCode(), MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE))
            {// 验证邮箱码
                return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
            }
        }
        if(StringUtils.isEmpty(req.getPassword()))
        {
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        account.setLoginPwd(EncryptUtils.entryptPassword(req.getPassword()));
        accountService.updateByPrimaryKey(account);
        forceLogout(account.getUserId());
        return  this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/password/change")
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    public JsonMessage<String> passwordChange(@Validated @RequestBody ChangePasswordReq req) throws BusinessException {
        // ValidatorUtils.validate(req);
        Account account = accountService.findByUserId(req.getUserID());
        if (null == account) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        if (!EncryptUtils.validatePassword(String.valueOf(req.getCurrentPassword()), account.getLoginPwd())) {
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }

        account.setLoginPwd(EncryptUtils.entryptPassword(req.getNewPassword()));
        accountService.updateByPrimaryKey(account);
        forceLogout(account.getUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 获取用户的IMtoken
     *
     * @param
     * @return
     */
    private String getUserImToken(Integer platform, String userId) {
        GetUserTokenReq req = new GetUserTokenReq();
        req.setUserID(userId);
        req.setPlatformID(platform);
        return openImApiService.getImToken(req);
    }

    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录", httpMethod = "POST")
    public JsonMessage<LoginResp> logout(HttpServletRequest request, @Validated @RequestBody LogoutReq req) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        log.info("logout success");
        Account account = accountService.selectByPrimaryKey(principal.getId());
        openImApiService.forceLogout(req.getPlatform(), account.getUserId());
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/logoff")
    @ApiOperation(value = "应用内注销账户", httpMethod = "POST")
    public JsonMessage<LoginResp> logoff(HttpServletRequest request, @Validated @RequestBody LogoffPasswordReq req) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        Account account = accountService.selectByPrimaryKey(principal.getId());
        forceLogout(account.getUserId().toString());
        if(null == account) {
            log.error("邮箱码账户注销 没找到对应的账户信息");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        // 验证密码
        if (!EncryptUtils.validatePassword(String.valueOf(req.getPassword()), account.getLoginPwd())) {
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        account.setStatus(2); // 注销
        log.info("logoff account:{}", account.toString());
        accountService.updateByPrimaryKeySelective(account);
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        log.info("logoff success");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 强制退出登录 各个客户端
     * @param userId
     */
    private void forceLogout(String userId) {
        for (int i = 1; i <= 10; i++) {
            openImApiService.forceLogout(i, userId);
        }
    }

    @GetMapping(value = "/invite/statistics")
    @ApiOperation(value = "邀请返佣情况", httpMethod = "GET")
    public JsonMessage<AccountInviteStatistics> inviteStatistics(HttpServletRequest request) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        AccountInviteStatistics entity  = new AccountInviteStatistics();
        entity.setAccountId(principal.getId());
        entity= accountInviteStatisticsService.selectOne(entity);
        if(null == entity) {
            entity = new AccountInviteStatistics();
            entity.setInviteCnt(0);
            entity.setInviteAwardedTotal(BigDecimal.ZERO);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,entity);
    }

    @GetMapping(value = "/invite/detail")
    @ApiOperation(value = "邀请返佣明细", httpMethod = "POST")
    public JsonMessage<PaginateResult<AccountInviteRewardsDetail>> inviteDetail(Pagination pagination) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        AccountInviteRewardsDetail entity  = new AccountInviteRewardsDetail();
        entity.setAccountId(principal.getId());
        PaginateResult<AccountInviteRewardsDetail> list= accountInviteRewardsDetailService.search(pagination,entity);
        return this.getJsonMessage(CommonEnums.SUCCESS,list);
    }

    @PostMapping(value = "/signIn/doSignIn")
    @ApiOperation(value = "签到", httpMethod = "POST")
    public JsonMessage dosignin(@Validated @RequestBody ReqPublicModel req) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        String redisLockName = new StringBuilder(CacheConst.REDISLOCK_SIGNIN_ACCOUNT_PREFIX).append(principal.getId()).toString();
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                accountSignInInfoService.doSignIn(principal.getId(),DateUtils.formatDate(new Date(), DateConst.DATE_FORMAT_YMD));
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(CommonEnums.FAIL,e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            return this.getJsonMessage(CommonEnums.SERVICE_BUSY_ERROR);
        }

        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/signIn/info")
    @ApiOperation(value = "签到信息", httpMethod = "GET")
    public JsonMessage<AccountSignInInfo> signInfo(ReqPublicModel req) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        AccountSignInInfo info = accountSignInInfoService.findByAccountId(principal.getId());
        if(null == info)
        {
            info = new AccountSignInInfo();
            info.setAccountId(principal.getId());
            info.setCurrentSigninTimes(0);
            info.setTotalPoints(0);
            info.setIsSignedToday(false);
        }
        else {
            info.setIsSignedToday(DateUtils.isSameDay(new Date(), info.getLastSigninDate()));
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,info);
    }

    @GetMapping(value = "/signIn/detail")
    @ApiOperation(value = "签到明细", httpMethod = "GET")
    public JsonMessage<PaginateResult<AccountSignInDetail> > signDetail(Pagination pagination) throws BusinessException {

        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        AccountSignInDetail search = new AccountSignInDetail();
        search.setAccountId(principal.getId());
        PaginateResult<AccountSignInDetail> page = accountSignInDetailService.search(pagination,search);
        return this.getJsonMessage(CommonEnums.SUCCESS,page);
    }

}
