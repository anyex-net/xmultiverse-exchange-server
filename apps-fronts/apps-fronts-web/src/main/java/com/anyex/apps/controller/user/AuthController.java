package com.anyex.apps.controller.user;

import com.alibaba.fastjson.JSON;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.ReqUserLogin;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.exception.UserPolicyException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.RedisSessionManager;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.shiro.model.UserToken;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.entity.UserLog;
import com.anyex.apps.user.model.PolicyModel;
import com.anyex.apps.user.model.UserScanLoginModel;
import com.anyex.apps.user.service.UserLogService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.*;
import com.maxmind.geoip.Location;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录权限认证 Introduce
 * <p>File：AuthController.java </p>
 * <p>Title: AuthController </p>
 * <p>Description:AuthController </p>
 * <p>Copyright: Copyright (c) 17/6/21</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.AUTH)
@Api(tags = "用户登录")
public class AuthController extends GenericController
{
    @Autowired(required = false)
    GlobalProperies properies;

    @Autowired(required = false)
    RedisSessionManager redisSessionManager;
    
//    @Autowired(required = false)
//    AccountLogNoSql     accountLogNoSql;

    @Autowired(required = false)
    UserLogService userLogService;
    
    @Autowired(required = false)
    SysMsgRecordService sysMsgRecordService;
    
    @Autowired(required = false)
    UserService userService;
    
    private String      loginGaErrCnt = "login_ga_error_exchange_";
    
    /**
     * 用户登录认证
     * @param request
     * @param reqUserLogin
     * @param //model
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/login/submit")
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public JsonMessage loginSubmit(HttpServletRequest request, @Validated @RequestBody ReqUserLogin reqUserLogin /*, @ModelAttribute AliyunModel model*/) throws BusinessException
    {
        if (SecurityUtils.getSubject().isAuthenticated())
        {// 如果登陆过就直接进入后台
            log.info("如果登陆过就直接进入后台");
            return this.getJsonMessage(CommonEnums.SUCCESS);
        }
        //
//        if (validErrCount(request) || StringUtils.isNotBlank(model.getCsessionid()) || StringUtils.isNotBlank(model.getScene()))
//        { // 判断验证码
//            if (!AliyunUtils.validParams(model))
//            {// 验证不通过时
//                Map<String, Object> result = new HashMap();
//                result.put("showCaptcha", true);
//                return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA, result);
//            }
//        }
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer captchaKey = null;
        if(reqUserLogin.getLoginType().equals("email")){
            captchaKey = new StringBuffer(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
        } else if(reqUserLogin.getLoginType().equals("mobile")){
            captchaKey = new StringBuffer(MessageConst.SMS_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip);
        } else {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        String captchaText = RedisUtils.get(captchaKey.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqUserLogin.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        User userDB = null;
        if(reqUserLogin.getLoginType().equals("email")){
            userDB = userService.findByEmail(reqUserLogin.getEmail());
        } else if(reqUserLogin.getLoginType().equals("mobile")){
            userDB = userService.findByMobileNoAndCountry(reqUserLogin.getMobileNo(), reqUserLogin.getCountry());
        }
        //
        if (null == userDB) { throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST); }
        if (null == userDB || userDB.getState().intValue() == UserConsts.USER_STATUS_CLOSE.intValue())
        { throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST); }
        if (userDB.getState().intValue() == UserConsts.USER_STATUS_FROZEN.intValue())
        { throw new BusinessException(CommonEnums.ERROR_FROZEN_USER); }
        if (null != userDB && !userDB.verifySignature())
        {// 校验数据
            log.error("用户信息 数据校验失败");
            throw new BusinessException(CommonEnums.ERROR_LOGIN_LOCK);
        }
        //
        UserToken userToken = new UserToken();
        userToken.setHost(NetworkUtils.getIpAddr(request));
        userToken.setPassword(reqUserLogin.getPassword());
        if(reqUserLogin.getLoginType().equals("email")){
            userToken.setUsername(reqUserLogin.getEmail());
            userToken.setEmail(reqUserLogin.getEmail());
            userToken.setMobileNo(userDB.getMobileNo());
            userToken.setCountry(userDB.getCountry());
        } else if(reqUserLogin.getLoginType().equals("mobile")){
            userToken.setUsername(reqUserLogin.getCountry()+reqUserLogin.getMobileNo());
            userToken.setMobileNo(reqUserLogin.getMobileNo());
            userToken.setCountry(reqUserLogin.getCountry());
        }
        //
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(userToken);
            saveOperationLogs(request, OnLineUserUtils.getPrincipal(), "login");
        }
        catch (IncorrectCredentialsException ice)
        {
            logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("IncorrectCredentialsException:{}", ice.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD, result);
        }
        catch (UnknownAccountException uae)
        {
            logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("UnknownAccountException:{}", uae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_USER_NOT_EXIST, result);
        }
        catch (LockedAccountException lae)
        {
            logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("LockedAccountException:{}", lae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_LOCK, result);
        }
        catch (ExcessiveAttemptsException eae)
        {
            logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("ExcessiveAttemptsException:{}", eae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_TIMEOUT, result);
        }
        catch (UserPolicyException upe)
        {
            redisSessionManager.remove(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            Serializable sId = subject.getSession().getId();
            log.info("UserPolicyException sId :{}", sId);
            StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(sId);
            log.info("UserPolicyException key :{}", key.toString());
            // RedisUtils.putObject(key.toString(), userToken, CacheConst.DEFAULT_CACHE_TIME);
            RedisUtils.putObject(key.toString(), JSON.toJSONString(userToken), CacheConst.DEFAULT_CACHE_TIME);
            log.error("UserPolicyException upe:{}", upe.getLocalizedMessage());
            //
            //
            String mobileNo = "";
            if (userToken != null && StringUtils.isNotBlank(userToken.getMobileNo()))
            {
                // mobileNo = token.getMobNo().substring(0,token.getMobNo().length()-(token.getMobNo().substring(3)).length()) + "****" + token.getMobNo().substring(7);
                mobileNo = "****" + userToken.getMobileNo().substring(userToken.getMobileNo().length() - 3);
            }
            Map<String, Object> result = new HashMap();
            result.put("Authorization", sId);
            result.put("checkType", StringUtils.isNotEmpty(userDB.getGaAuthKey())? "gaCheck":"smsCheck");
            result.put("mobileNo", mobileNo);
            result.put("showCaptcha", false);
            //
            //
            // return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK, result);
            return this.getJsonMessage(CommonEnums.SUCCESS, result);
        }
        redisSessionManager.remove(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
        // if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
        // {// 短信提醒
        // UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // if (StringUtils.isNotBlank(principal.getUserMobile()))
        // {// 确保手机已绑定过
        // String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
        // String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
        // msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_LOGIN_PHONE, principal.getLang(), vagueMobile,
        // CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
        // }
        // }
//        String ip = NetworkUtils.getIpAddr(request);
//        if (StringUtils.isNotBlank(ip))
//        {
//            ip = ip.split(",")[0];
//        }
//        String userAgent = request.getHeader("User-Agent");
//        String userAgentString = UserAgentUtils.getUserAgentInfo(userAgent);
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            UserPrincipal principal = OnLineUserUtils.getPrincipal();
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_LOGIN_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL, principal.getUserMail(),
//                    userAgentString, CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS), ip);
//        }
        //返回成功并且返回sid
        Map<String, Object> result = new HashMap();
        result.put("Authorization", subject.getSession().getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    /**
     * 用户登录认证(二维码登录)
     * @param request
     * @param userToken
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
//    @ResponseBody
//    @RequestMapping("/login/submit/qrcode")
//    @ApiOperation(value = "登录(二维码扫码)", httpMethod = "POST")
    public JsonMessage loginSubmitQRCode(HttpServletRequest request, @Validated @RequestBody UserToken userToken) throws BusinessException
    {
        log.info("loginSubmitQrcode ip:{}", NetworkUtils.getIpAddr(request));
        //
        if (SecurityUtils.getSubject().isAuthenticated())
        {// 如果登陆过就直接进入后台
            log.info("如果登陆过就直接进入后台");
            return this.getJsonMessage(CommonEnums.SUCCESS);
        }
        //
        if(null == userToken || null == userToken.getQrCode())
        {
            log.error("必须传入qrCode参数,现在没传入");
            return this.getJsonMessage(CommonEnums.ERROR_NEED_QRCODE);
        }
        //
        userToken.setHost(NetworkUtils.getIpAddr(request));
        Subject subject = SecurityUtils.getSubject();
        try
        {
            // Redis缓存读取信息
            UserScanLoginModel userScanLoginModel = (UserScanLoginModel) RedisUtils.getObject(userToken.getQrCode());
            if(null == userScanLoginModel){
                log.error("根据qrCode参数没获取到对应redis缓存对象,可能二维码已经失效");
                return this.getJsonMessage(CommonEnums.ERROR_QRCODE_INVAILID);
            }
            // 二维码APP未扫描
            if(0 == userScanLoginModel.getStatus().intValue()){
                log.error("二维码APP未扫描");
                return this.getJsonMessage(CommonEnums.ERROR_QRCODE_NOTSCANNED);
            } // 二维码APP未扫描
            else if(1 == userScanLoginModel.getStatus().intValue()){
                log.error("二维码APP已扫描未确认");
                return this.getJsonMessage(CommonEnums.ERROR_QRCODE_NOTCONFIRM);
            } // APP确认登录
            else if(2 == userScanLoginModel.getStatus().intValue())
            {
                userToken.setId(userScanLoginModel.getUserId()); //用户ID
                subject.login(userToken);
                // 登录成功后 清除Redis缓存
                RedisUtils.del(userToken.getQrCode());
                // 记录日志
                saveOperationLogs(request, OnLineUserUtils.getPrincipal(), "login QRCode");
            } else {
                log.error("非法请求");
                return this.getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
            }
        }
        catch (IncorrectCredentialsException ice)
        {
            //logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("IncorrectCredentialsException:{}", ice.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD, result);
        }
        catch (UnknownAccountException uae)
        {
            //logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("UnknownAccountException:{}", uae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_USER_NOT_EXIST, result);
        }
        catch (LockedAccountException lae)
        {
            //logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("LockedAccountException:{}", lae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_LOCK, result);
        }
        catch (ExcessiveAttemptsException eae)
        {
            //logLoginTimes(request);
            Integer showCaptcha = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            log.error("ExcessiveAttemptsException:{}", eae.getLocalizedMessage());
            Map<String, Object> result = new HashMap();
            result.put("showCaptcha", null != showCaptcha && showCaptcha > 2 ? true : false);
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_TIMEOUT, result);
        }
        catch (UserPolicyException upe)
        {
            redisSessionManager.remove(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
            Serializable sId = subject.getSession().getId();
            log.info("UserPolicyException sid :{}", sId);
            //
            StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(sId);
            log.info("UserPolicyException key :{}", key.toString());
            userToken.setUsername(null); // 非用户名密码登录
            RedisUtils.putObject(key.toString(), userToken, CacheConst.DEFAULT_CACHE_TIME);
            log.error("UserPolicyException:{}", upe.getLocalizedMessage());
            //
            // 需要赋值一下
            UserToken token = (UserToken)upe.getObject();
            //
            String mobileNo = "";
            if (token != null && StringUtils.isNotBlank(token.getMobileNo()))
            {
                // mobileNo = token.getMobNo().substring(0,token.getMobileNo().length()-(token.getMobileNo().substring(3)).length()) + "****" + token.getMobileNo().substring(7);
                mobileNo = "****" + token.getMobileNo().substring(token.getMobileNo().length() - 3);
            }
            Map<String, Object> result = new HashMap();
            result.put("Authorization", sId);
            result.put("checkType", token.isGa()? "gaCheck":"smsCheck");
            result.put("mobileNo", mobileNo);
            result.put("showCaptcha", false);
            //
            //
            return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK, result);
        }
        redisSessionManager.remove(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
        // if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
        // {// 短信提醒
        // UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // if (StringUtils.isNotBlank(principal.getUserMobile()))
        // {// 确保手机已绑定过
        // String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
        // String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
        // msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_LOGIN_PHONE, principal.getLang(), vagueMobile,
        // CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
        // }
        // }
//        String ip = NetworkUtils.getIpAddr(request);
//        if (StringUtils.isNotBlank(ip))
//        {
//            ip = ip.split(",")[0];
//        }
//        String userAgent = request.getHeader("User-Agent");
//        String userAgentString = UserAgentUtils.getUserAgentInfo(userAgent);
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            UserPrincipal principal = OnLineUserUtils.getPrincipal();
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_LOGIN_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL, principal.getUserMail(),
//                    userAgentString, CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS), ip);
//        }
        //返回成功并且返回sid
        Map<String, Object> result = new HashMap();
        result.put("Authorization", subject.getSession().getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    /**
     * 用户登录二次认证前发送短信码
     * @param request
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "用户登录二次认证前发送短信码", httpMethod = "POST")
    @RequestMapping(value = "/login/check/sendSms", method = RequestMethod.POST)
    // @AccessLimit(limit = 1, timeScope = 45, isLogin = true) // 未登录情况下限制45秒内最多请求1次
    public JsonMessage checkSubmitSendSMS(HttpServletRequest request) throws BusinessException
    {
//        String sId = CookieUtils.get(request, CacheConst.WEB_IM_ID);
        Subject subject = SecurityUtils.getSubject();
        Serializable sId = subject.getSession().getId();
        log.info("checkSubmit sId:{}", sId);
        //
        StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(sId);
        log.info("checkSubmit key:{}", key.toString());
        //UserToken userToken = (UserToken) RedisUtils.getObject(key.toString());
        String jsonString = (String) RedisUtils.getObject(key.toString());
        UserToken userToken;
        if(StringUtils.isNotEmpty(jsonString)){
            userToken = JSON.parseObject(jsonString, UserToken.class);
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_SESSION_TIME_OUT);
        }
        log.info("checkSubmit userToken:{}", userToken);
        if (null == userToken) return this.getJsonMessage(CommonEnums.ERROR_SESSION_TIME_OUT);
        //
        StringBuffer mobileNum = new StringBuffer(userToken.getCountry()).append(userToken.getMobileNo());
        sysMsgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_LOGIN);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 用户登录二次认证提交
     * @param request
     * @param policy
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/login/check/submit")
    @ApiOperation(value = "用户登录二次认证提交", httpMethod = "POST")
    public JsonMessage checkSubmit(HttpServletRequest request, HttpServletResponse response, @Validated @RequestBody PolicyModel policy) throws BusinessException
    {
//        log.info("set-cookie:" + response.getHeader("set-cookie"));
//        response.setHeader("set-cookie", "");
//        response.setHeader("Set-Cookie", "");
//        log.info("重置set-cookie后:" + response.getHeader("set-cookie"));
//        // String sId = CookieUtils.get(request, CacheConst.WEB_COOKIE_ID);
//        String sId = CookieUtils.get(request, CacheConst.WEB_IM_ID);
//        log.info("checkSubmit sId:{}", sId);
//        StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(sId);

        Subject subject = SecurityUtils.getSubject();
        StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(subject.getSession().getId());
        log.info("checkSubmit key:{}", key.toString());

        // UserToken userToken = (UserToken) RedisUtils.getObject(key.toString());
        String jsonString = (String) RedisUtils.getObject(key.toString());
        UserToken userToken;
        if(StringUtils.isNotEmpty(jsonString)){
            userToken = JSON.parseObject(jsonString, UserToken.class);
        } else {
            return this.getJsonMessage(CommonEnums.ERROR_SESSION_TIME_OUT);
        }
        log.info("checkSubmit userToken:{}", userToken);
        if (null == userToken) return this.getJsonMessage(CommonEnums.ERROR_SESSION_TIME_OUT);
        if (null == policy) return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK);
        validErrGaCount(userToken.getId());// 检查用户ga输错次数
        //
        //
        userToken.setHost(NetworkUtils.getIpAddr(request));
        policy.setSmsScene(MessageConst.SMS_VALID_LOGIN); // 登录场景
        userToken.setPolicy(policy);
        //
        // Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(userToken);
        }
        catch (UserPolicyException upe)
        {
            //  累计ga错误次数，错误超过10次 退出登录
            if (logLoginGaTimes(userToken.getId()) > 9)
            {
                User user = userService.selectByPrimaryKeyNoCheck(userToken.getId());
                user.setState(1);
                userService.save(user);
                if (null != subject)
                {
                    subject.logout();
                }
            }
            return this.getJsonMessage(CommonEnums.ERROR_AUTHER_FAILED);
        }
        RedisUtils.del(key.toString());
        // if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
        // {// 短信提醒
        // UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // if (StringUtils.isNotBlank(principal.getUserMobile()))
        // {// 确保手机已绑定过
        // String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
        // String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
        // msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_LOGIN_PHONE, principal.getLang(), vagueMobile,
        // CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
        // }
        // }
//        String ip = NetworkUtils.getRemortIp(request);
//        if (StringUtils.isNotBlank(ip))
//        {
//            ip = ip.split(",")[0];
//        }
//        String userAgent = request.getHeader("User-Agent");
//        String userAgentStrng = UserAgentUtils.getUserAgentInfo(userAgent);
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            UserPrincipal principal = OnLineUserUtils.getPrincipal();
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_LOGIN_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL, principal.getUserMail(),
//                    userAgentStrng, CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS), ip);
//        }
        //
        Map<String, Object> result = new HashMap();
        result.put("Authorization", subject.getSession().getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    /**
     * 用户退出认证
     * @return {@link String}
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/logout")
    @ApiOperation(value = "用户退出", httpMethod = "POST")
    public JsonMessage logout(HttpServletRequest request) throws Exception
    {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            saveOperationLogs(request, OnLineUserUtils.getPrincipal(), "logout");
            subject.logout();
        }
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(CommonEnums.SUCCESS.getCode());
        jsonMessage.setMessage(CommonEnums.SUCCESS.getMessage());
        return jsonMessage;
    }
    
    /**
     * 保存操作日志
     * @param request
     * @param principal
     * @param content
     */
    void saveOperationLogs(HttpServletRequest request, UserPrincipal principal, String content)
    {
        try
        {
            if (null == principal) return;
            UserLog userLog = new UserLog();
            userLog.setUserId(principal.getId());
            userLog.setUserName(principal.getUserName());
            userLog.setSystemName("exchange-web");
            userLog.setOpType("login");
            userLog.setContent(content);
            userLog.setUrl(request.getRequestURI());
            userLog.setIpAddr(NetworkUtils.getIpAddr(request));
            userLog.setCreateTime(CalendarUtils.getCurrentLong());
            if (null != userLog.getIpAddr())
            {
                String rigonName = "Unknown address";
                String[] ipArray = userLog.getIpAddr().split(",");
                for (String ip : ipArray)
                {
                    Location location = GeoIPUtils.getInstance().getLocation(ip);
                    if (null != location)
                    {
                        rigonName = new StringBuilder(location.countryName).append("|").append(location.city).toString();
                    }
                    break;
                }
                userLog.setRigonName(rigonName);
            }
            userLogService.insert(userLog);
        }
        catch (RuntimeException e)
        {
            log.error("操作日志记录失败：{}", e.getCause());
        }
    }
    
    /**
     * 记录二次登录ga验证出错次数
     * @param accountId
     */
    int logLoginGaTimes(Long accountId) throws BusinessException
    {
        String key = loginGaErrCnt + accountId;
        Integer count = (Integer) RedisUtils.getObject(key);
        if (null == count) count = 0; // 默认为0
        count = count + 1;
        RedisUtils.putObject(key, count, CacheConst.ONE_MINUTE_CACHE_TIME);
        log.info("二次登录验证错误次数：" + count);
        return count;
    }
    
    /**
     * 判断二次登录ga验证出错次数
     * @param userId
     * @return
     */
    boolean validErrGaCount(Long userId) throws BusinessException
    {
        Integer count = (Integer) RedisUtils.getObject(loginGaErrCnt + userId);
        return null != count && count > 9 ? true : false;
    }
    
    /**
     * 记录登录出错的次数
     * @param request
     */
    void logLoginTimes(HttpServletRequest request)
    {
        Integer count = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
        if (null == count) count = 0; // 默认为0
        redisSessionManager.put(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA, count + 1, CacheConst.DEFAULT_CACHE_TIME);
        log.info("记录登录出错的次数：" + (count + 1) );
    }
    
    /**
     * 判断有无登录出错数
     * @param request
     * @return
     */
    boolean validErrCount(HttpServletRequest request)
    {
        Integer count = redisSessionManager.getInteger(request, RedisSessionManager.SessionKey.SHOW_CAPTCHA);
        return null != count && count > 2 ? true : false;
    }
}
