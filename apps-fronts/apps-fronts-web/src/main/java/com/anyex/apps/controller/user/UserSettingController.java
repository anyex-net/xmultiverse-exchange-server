package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.*;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.google.Authenticator;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.enums.UserEnums;
import com.anyex.apps.user.model.PolicyModel;
import com.anyex.apps.user.service.UserCertKycService;
import com.anyex.apps.user.service.UserPolicyService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.*;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户安全设置 控制器
 * <p>File：UserSettingController.java </p>
 * <p>Title: UserSettingController </p>
 * <p>Description:UserSettingController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(GlobalConst.USER)
@Api(tags = "用户安全设置")
public class UserSettingController extends GenericController
{
    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    UserPolicyService userPolicyService;

    @Autowired(required = false)
    UserCertKycService userCertKycService;

//    @Autowired(required = false)
//    AccountLogNoSql             accountLogNoSql;

    @Autowired(required = false)
    SysMsgRecordService sysMsgRecordService;

//    @Autowired(required = false)
//    AccountInterfaceService     accountInterfaceService;
//
//    @Autowired(required = false)
//    StockInfoService            stockInfoService;
//
//    @Autowired(required = false)
//    AccountDebitAssetService    accountDebitAssetService;

    /**
     * 修改登录密码
     * @param reqUserModifyPwd
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改登录密码", httpMethod = "POST")
    @RequestMapping(value = "/setting/modifyLoginPwd", method = RequestMethod.POST)
    public JsonMessage modifyLoginPwd(@Validated @RequestBody ReqUserModifyPwd reqUserModifyPwd) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (null != userDB && !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        //
        if (!EncryptUtils.validatePassword(reqUserModifyPwd.getOldPass(), userDB.getLoginPwd()))
        {// 检验原始密码
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append("modifyLoginPwd")// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= 10)
            {// 操作频率达到10次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_CHANGE_PASSWORD);
                SecurityUtils.getSubject().logout(); // 冻结用户后退出当前会话
            }
            return getJsonMessage(UserEnums.USER_PASSWORD_ERROR);
        }
        userDB.setLoginPwd(EncryptUtils.entryptPassword(reqUserModifyPwd.getNewPass()));
        userDB.setUpdateTime(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(userDB);
        /*
         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 短信提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile()))
         * {// 确保手机已绑定过
         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_LOGINPASS_PHONE, principal.getLang(), vagueMobile,
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         * }
         */
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_LOGINPASS_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL,
//                    principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//        }
        saveOperationLogs(principal, "modify login password");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 修改交易密码
     * @param reqUserModifyPwd
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改交易密码", httpMethod = "POST")
    @RequestMapping(value = "/setting/modifyTradePwd", method = RequestMethod.POST)
    public JsonMessage modifyTradePwd(@Validated @RequestBody ReqUserModifyPwd reqUserModifyPwd) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (null != userDB && !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        //
        if (!EncryptUtils.validatePassword(reqUserModifyPwd.getOldPass(), userDB.getTradePwd()))
        {// 检验原始密码
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append("modifyTradePwd")// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= 10)
            {// 操作频率达到10次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_CHANGE_PASSWORD);
                SecurityUtils.getSubject().logout(); // 冻结用户后退出当前会话
            }
            return getJsonMessage(UserEnums.USER_PASSWORD_ERROR);
        }
        userDB.setTradePwd(EncryptUtils.entryptPassword(reqUserModifyPwd.getNewPass()));
        userDB.setUpdateTime(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(userDB);
        /*
         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 短信提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile()))
         * {// 确保手机已绑定过
         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_LOGINPASS_PHONE, principal.getLang(), vagueMobile,
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         * }
         */
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_LOGINPASS_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL,
//                    principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//        }
        saveOperationLogs(principal, "modify trade password");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

//    /**
//     * 修改交易密码
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @ApiOperation(value = "修改交易密码", httpMethod = "POST")
//    @RequestMapping(value = "/setting/modifyTradePwd", method = RequestMethod.POST)
//    public JsonMessage modifyTradePwd(String tradePwd, @ModelAttribute PolicyModel policy) throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        User userDB = userService.selectByPrimaryKey(principal.getId());
//        if (null != userDB && !userDB.verifySignature())
//        {// 校验数据
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        //
//        try
//        {
//            if (StringUtils.isNotBlank(userDB.getMobileNo()) && UserConsts.SECURITY_POLICY_DEFAULT.equals(userDB.getSecurityPolicy()))
//            {
//                StringBuffer mobile = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
//                if (!sysMsgRecordService.validSMSCode(mobile.toString(), policy.getSms(), "类型"))
//                { throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED); }
//            }
//            else
//            {
//                userPolicyService.validSecurityPolicy(userDB, policy);
//            }
//        }
//        catch (BusinessException e)
//        {
//            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
//                    .append(GlobalConst.SEPARATOR).append("modifyTradePwd")// 加入模块标识
//                    .append(GlobalConst.SEPARATOR).append(userDB.getId()).toString();
//            int count = userPolicyService.errorOperatorCounter(opCountKey);
//            if (count >= 10)
//            {// 操作频率达到10次时,锁定用户
////                userService.modifyAccountStatusToFrozen(userDB.getId(), UserConsts.FROZEN_REASON_CHANGE_FUNDPWD);
//                return getJsonMessage(CommonEnums.ERROR_FROZEN_USER);
//            }
//            throw e;
//        }
//        userDB.setTradePolicy(UserConsts.TRADE_POLICY_TWOHOUR);
//        userDB.setTradePwd(EncryptUtils.entryptPassword(tradePwd));
//        userService.updateByPrimaryKeySelective(userDB);
//        /*
//         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 短信提醒
//         * if (StringUtils.isNotBlank(principal.getUserMobile()))
//         * {// 确保手机已绑定过
//         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
//         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
//         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_FUNDPASS_PHONE, principal.getLang(), vagueMobile,
//         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         * }
//         */
////        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
////        {// 邮件提醒
////            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_FUNDPASS_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL,
////                    principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
////        }
//        saveOperationLogs(principal, "modify trade password");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }

    /**
     * 绑定邮箱发送邮件
     * @param request
     * @param reqSendEmail
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定邮箱发送邮件", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindEmail/sendEmail", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 60, isLogin = true) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage bindEmailSendEmail(HttpServletRequest request, @Validated @RequestBody ReqSendEmail reqSendEmail) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
//        if (!AliyunUtils.validParams(model))
//        {// 验证不通过时
//            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA, Boolean.TRUE);
//        }
        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
        {// 验证邮件地址
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
//        if (userService.valiEmail(email))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(UserEnums.ACCOUNT_EMAIL_HAS_BIND);
//        }
//        sysMsgRecordService.sendBindEmail(email, principal.getId(), "en_US", ipAddr);
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_BINDEMAIL).append(GlobalConst.SEPARATOR).append(ip);
        String captchaText = RedisUtils.get(key.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        sysMsgRecordService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_BINDSENDCODE);
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定邮箱
     * @param reqUserBindEmail
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定邮箱", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindEmail", method = RequestMethod.POST)
    public JsonMessage bindEmail(@Validated @RequestBody ReqUserBindEmail reqUserBindEmail) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (null == userDB || !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        if (StringUtils.isNotBlank(userDB.getEmail()))
        {// 表示当前帐户已绑定过邮箱,防止用户串改会话ID来修改绑定的邮箱
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        //
//        String mobileNo = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo()).toString();
//        if (!sysMsgRecordService.validSMSCode(mobileNo, smsCode, "类型"))
//        {// 判断验证码
//            // 开始记录操作次数
//            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
//                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
//                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//            int count = userPolicyService.errorOperatorCounter(opCountKey);
//            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
//            {// 操作频率达到30次时,锁定用户
////                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), UserConsts.FROZEN_REASON_BIND_EMAIL);
//                SecurityUtils.getSubject().logout(); // 冻结用户后登陆当前会话
//            }
//            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
//        }
//        StringBuffer cacheKey = new StringBuffer(GlobalConst.MESSAGE).append(GlobalConst.SEPARATOR).append(userDB.getId());
//        EmailModel model = (EmailModel) RedisUtils.getObject(cacheKey.toString());
//        if (!StringUtils.equals(emailCode, model.getRandomKey()))
//        {// 判断验证码
//            // 开始记录操作次数
//            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
//                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_EMAIL)// 加入模块标识
//                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//            int count = userPolicyService.errorOperatorCounter(opCountKey);
//            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
//            {// 操作频率达到30次时,锁定用户
////                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), UserConsts.FROZEN_REASON_BIND_EMAIL);
//                SecurityUtils.getSubject().logout(); // 冻结用户后登陆当前会话
//            }
//            return getJsonMessage(UserEnums.USER_EMAILCODE_ERROR);
//        }
//        if (userService.valiEmail(email))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(UserEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
        //
        if (!sysMsgRecordService.validEmailCode(reqUserBindEmail.getEmail(), reqUserBindEmail.getEmailCode(), MessageConst.TEMPLATE_EMAIL_BINDSENDCODE))
        {// 验证邮箱码
            return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
        }
        //
        userDB.setEmail(reqUserBindEmail.getEmail());
        // userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGAORSMS);
        userDB.setUpdateTime(System.currentTimeMillis());
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind email");
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定手机发送短信码
     * @param request
     * @param reqSendSms 发送短信验证码请求对象
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定手机发送短信码", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindMobile/sendSms", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 60, isLogin = true) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage bindMobileSendSMS(HttpServletRequest request, @Validated @RequestBody ReqSendSms reqSendSms) throws BusinessException
    {
        log.info("bindMobileSendSMS reqSendSms:{}", reqSendSms);
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_BINDMOBILE).append(GlobalConst.SEPARATOR).append(ip);
        String captchaText = RedisUtils.get(key.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        StringBuffer mobileNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobileNo());
        sysMsgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_BINDMOBILE);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定手机
     * @param reqUserBindMobile
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定手机", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindMobile", method = RequestMethod.POST)
    public JsonMessage bindMobile(@Validated @RequestBody ReqUserBindMobile reqUserBindMobile) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (null == userDB || !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        if (StringUtils.isNotBlank(userDB.getMobileNo()))
        {// 表示当前帐户已绑定过手机号,防止用户串改会话ID来修改绑定的手机
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        //
        String mobile = new StringBuffer(reqUserBindMobile.getCountry()).append(reqUserBindMobile.getMobileNo()).toString();
        if (!sysMsgRecordService.validSMSCode(mobile, reqUserBindMobile.getSmsCode(), MessageConst.SMS_VALID_BINDMOBILE))
        {// 判断验证码
            // 开始记录操作次数
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
            {// 操作频率达到30次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), UserConsts.FROZEN_REASON_BIND_PHONE);
                SecurityUtils.getSubject().logout(); // 冻结用户后登陆当前会话
            }
            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
        }
//        if (userService.checkBindPhone(phone))
//        {// 一个手机号只能绑定一个帐号
//            throw new BusinessException(UserEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
        userDB.setMobileNo(reqUserBindMobile.getMobileNo());
        userDB.setCountry(reqUserBindMobile.getCountry());
        if(StringUtils.isNotEmpty(userDB.getGaAuthKey())){
            userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGAORSMS);
        } else {
            userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDSMS);
        }
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind mobile");
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 生成GOOGLE校验码
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @PostMapping(value = "/setting/bindGA/buildGASecretKey")
    @ApiOperation(value = "绑定谷歌认证生成GASecretKey", httpMethod = "POST")
    public JsonMessage buildGASecretKey() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal)
        {// 用户必须登录
            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        }
        if (StringUtils.isEmpty(principal.getUserMail()))
        {// 用户邮箱如果没绑定的话 要先绑定邮箱
            throw new BusinessException("email unbind please first bind email");
        }
//        if (StringUtils.isBlank(principal.getUserMail()))
//        {// 避免用户通过手机号注册之后邮箱未绑定的前提下，重新查数据库
//            User userDB = userService.selectByPrimaryKey(principal.getId());
//            principal.setUserMail(userDB.getEmail());
//        }
        Map<String, String> result = Maps.newHashMap();
//        String issuer = GlobalConst.PROJECT_DEV_NAME;
//        if (BitmsConst.RUNNING_ENVIRONMONT.equalsIgnoreCase("production"))
//        {
//            issuer = GlobalConst.PROJECT_NAME;
//        }
//        if (BitmsConst.RUNNING_ENVIRONMONT.equalsIgnoreCase("testing"))
//        {
//            issuer = GlobalConst.PROJECT_TEST_NAME;
//        }
        String issuer = "exchange";
        String secretKey = Authenticator.generateSecretKey();
        result.put("secretKey", secretKey);
        result.put("email", principal.getUserMail());
        result.put("gaInfo", "otpauth://totp/" + principal.getUserMail() + "?secret=" + secretKey + "&issuer=" + issuer);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

//    /**
//     * 绑定谷歌认证
//     * @param secretKey
//     * @param gaCode
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
//    @RequestMapping(value = "/setting/bindGA", method = RequestMethod.POST)
//    @ApiImplicitParams({@ApiImplicitParam(name = "secretKey", value = "Google私钥", required = true, paramType = "form"),
//            @ApiImplicitParam(name = "gaCode", value = "GoogleE验证码", required = true, paramType = "form"),
//            @ApiImplicitParam(name = "validCode", value = "短信验证码", required = true, paramType = "form")})
//    public JsonMessage bindGoogleAuth(String secretKey, String gaCode, String validCode) throws BusinessException
//    {
//        Authenticator authenticator = new Authenticator();
//        if (StringUtils.isBlank(validCode) || StringUtils.isBlank(gaCode) || StringUtils.isBlank(secretKey))
//        {// 参数需要验证
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        if (!authenticator.checkCode(secretKey, Long.valueOf(gaCode)))
//        {// 判断验证码
//            return getJsonMessage(UserEnums.USER_GACODE_ERROR);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        User userDB = userService.selectByPrimaryKey(principal.getId());
//        StringBuffer buffer = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
//        if (!sysMsgRecordService.validSMSCode(buffer.toString(), validCode, "类型"))
//        {// 手机验证码判断
//            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
//        }
//        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
//                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//        RedisUtils.putObject(cacheKey, String.valueOf(secretKey), CacheConst.ONE_HOUR_CACHE_TIME);
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    /**
//     * 绑定谷歌认证
//     * @param secretKey
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @ApiOperation(value = "校验谷歌认证", httpMethod = "POST")
//    @RequestMapping(value = "/setting/bindGA/confirm", method = RequestMethod.POST)
//    public JsonMessage bindGoogleConfirm(String secretKey) throws BusinessException
//    {
//        if (StringUtils.isBlank(secretKey))
//        {// 参数需要验证
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
//                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//        String cacheSecretKey = RedisUtils.get(cacheKey);// 缓存中的GA私钥
//        if (!cacheSecretKey.equals(cacheSecretKey))
//        {// 判断缓存中的私钥和页面传如的私钥是否匹配，不匹配时抛出异常
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        User userDB = userService.selectByPrimaryKey(principal.getId());
//        if (StringUtils.isNotBlank(userDB.getGaAuthKey()))
//        {// 判断GA是否已绑定过
//            return getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
//        }
//        userDB.setGaAuthKey(EncryptUtils.desEncrypt(secretKey));
//        userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGA);
//        userService.updateByPrimaryKeySelective(userDB);
//        saveOperationLogs(principal, "bind GA");
//        RedisUtils.del(cacheKey);// 清除缓存
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }

    /**
     * 绑定谷歌认证(一步到位控制器入口)
     * @param secretKey
     * @param gaCode
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindGA", method = RequestMethod.POST)
    public JsonMessage bindGA(String secretKey, String gaCode, String validCode) throws BusinessException
    {
        Authenticator authenticator = new Authenticator();
        if (StringUtils.isBlank(validCode) || StringUtils.isBlank(gaCode) || StringUtils.isBlank(secretKey))
        {// 参数需要验证
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        if (!authenticator.checkCode(secretKey, Long.valueOf(gaCode)))
        {// 判断验证码
            return getJsonMessage(UserEnums.USER_GACODE_ERROR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isEmpty(userDB.getEmail()))
        {// 判断邮箱是否绑定
            return getJsonMessage(UserEnums.USER_EMAIL_NOTBIND);
        }
        if (StringUtils.isEmpty(userDB.getMobileNo()))
        {// 判断手机是否绑定
            return getJsonMessage(UserEnums.USER_PHONE_NOTBIND);
        }
        if (StringUtils.isNotBlank(userDB.getGaAuthKey()))
        {// 判断GA是否已绑定过
            return getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        StringBuffer buffer = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
        if (!sysMsgRecordService.validSMSCode(buffer.toString(), validCode, "类型"))
        {// 手机验证码判断
            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
        }
        // 账户实体类更新
        userDB.setGaAuthKey(EncryptUtils.desEncrypt(secretKey));
        userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGA); // 安全策略
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind Google Auth");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 换绑手机号码
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
//    @ResponseBody
//    @ApiOperation(value = "换绑手机号码", httpMethod = "POST")
//    @RequestMapping(value = "/setting/modifyBindMobile", method = RequestMethod.POST)
    public JsonMessage modifyBindMobile(String phone, String location, String validCode, @ModelAttribute PolicyModel policy) throws BusinessException
    {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(validCode) || StringUtils.isBlank(location))
        {// 必传参数校验
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        userPolicyService.validSecurityPolicy(userDB, policy);
        if (!userPolicyService.validSMSCode(new StringBuffer(location).append(phone).toString(), validCode))
        {// 新手机校验失败
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        userDB.setMobileNo(phone);
        userDB.setCountry(location);
        userService.updateByPrimaryKeySelective(userDB);
        /*
         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 短信提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile()))
         * {// 确保手机已绑定过
         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_PHONE_PHONE, principal.getLang(), vagueMobile,
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         * }
         */
        /*
         * if (BitmsConst.REMIND_EMAIL_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 邮件提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile())) msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_PHONE_EMAIL,
         * principal.getLang(), principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         */
        saveOperationLogs(principal, "change phone");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 解绑谷歌认证
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
//    @ResponseBody
//    @ApiOperation(value = "解绑谷歌认证", httpMethod = "POST")
//    @RequestMapping(value = "/setting/unBindGA", method = RequestMethod.POST)
    public JsonMessage unBindGA(@ModelAttribute PolicyModel policy) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        userPolicyService.validSecurityPolicy(userDB, policy);
        if (UserConsts.SECURITY_POLICY_NEEDGAANDSMS.equals(userDB.getSecurityPolicy()))
        {// 判断用户安全等级，如是同时启用了手GA和短信，此时由系统自动将安全等级降低成或
            userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGAORSMS);
        }
        else if (UserConsts.SECURITY_POLICY_NEEDGA.equals(userDB.getSecurityPolicy()))
        {// 判断用户安全等级，如是启用了GA，此时由系统自动将安全等级降低成短信
            userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDSMS);
        }
        userDB.setGaAuthKey(null);// 请空GOOGLE密匙
        userService.updateByPrimaryKeySelective(userDB);
        /*
         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 短信提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile()))
         * {// 确保手机已绑定过
         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_GOOGLE_PHONE, principal.getLang(), vagueMobile,
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         * }
         * if (BitmsConst.REMIND_EMAIL_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 邮件提醒
         * msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_GOOGLE_EMAIL, principal.getLang(), principal.getUserMail(),
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         */
        saveOperationLogs(principal, "unbind Google Auth");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 保存操作日志
     * @param principal
     * @param content
     */
    void saveOperationLogs(UserPrincipal principal, String content)
    {
//        try
//        {
//            if (null == principal) principal = OnLineUserUtils.getPrincipal();
//            HttpServletRequest request = ServletsUtils.getRequest();
//            AccountLog accountLog = new AccountLog();
//            accountLog.setContent(content);
//            accountLog.setSystemName(BitmsConst.BITMS_PROJECT_NAME);
//            accountLog.setAccountId(principal.getId());
//            accountLog.setUrl(request.getRequestURI());
//            accountLog.setOpType(AccountLogConsts.LOG_TYPE_SETTING);
//            accountLog.setAccountName(principal.getTrueName());
//            accountLog.setIpAddr(IPUtil.getOriginalIpAddr(request));
//            accountLog.setCreateDate(CalendarUtils.getCurrentLong());
//            if (null != accountLog.getIpAddr())
//            {
//                String rigonName = "Unknown address";
//                String[] ipArray = accountLog.getIpAddr().split(",");
//                for (String ip : ipArray)
//                {
//                    Location location = GeoIPUtils.getInstance().getLocation(ip);
//                    if (null != location)
//                    {
//                        rigonName = new StringBuilder(location.countryName).append("|").append(location.city).toString();
//                    }
//                    break;
//                }
//                accountLog.setRigonName(rigonName);
//            }
//            accountLogNoSql.insert(accountLog);
//        }
//        catch (RuntimeException e)
//        {
//            log.error("操作日志记录失败：{}", e.getCause());
//        }
//        finally
//        {
//            Long endTime = System.currentTimeMillis() + 86400000;
//            StringBuffer key = new StringBuffer(CacheConst.POLICY_PERFIX).append("uplocktime_Widthdraw_").append(principal.getId());
//            RedisUtils.putObject(key.toString(), endTime, CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
//        }
    }

//    /**
//     * 修改自动借贷默认策略
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @ApiOperation(value = "修改自动借贷默认策略", httpMethod = "POST")
//    @RequestMapping(value = "/setting/changeBorrowSwitch", method = RequestMethod.POST)
//    public JsonMessage changeBorrowSwitch(Integer autoDebit) throws BusinessException
//    {
////        UserPrincipal principal = OnLineUserUtils.getPrincipal();
////        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
////        StockInfo stockInfo = stockInfoService.selectByPrimaryKey(FundConsts.STOCKS_USD_TYPE);
////        Account account = accountService.selectByPrimaryKey(principal.getId());
////        account.setAutoDebit(autoDebit);
////        AccountDebitAsset entity = new AccountDebitAsset();
////        entity.setTableName(stockInfo.getTableDebitAsset());
////        entity.setBorrowerAccountId(principal.getId());
////        entity.setRelatedStockinfoId(stockInfo.getCapitalStockinfoId());
////        List<AccountDebitAsset> list = accountDebitAssetService.findListForDebit(entity);
////        if (list.size() > 0)
////        {
////            entity = list.get(0);
////            log.debug("存在借款 不能关闭");
////            if (autoDebit.intValue() == 0)
////            { throw new BusinessException(CommonEnums.FAIL, entity.getStockCode()); }
////        }
////        accountService.updateByPrimaryKeySelective(account);
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }

    //    /**
//     * 用户完成风险测评
//     * @return
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @ApiOperation(value = "用户风险测评", httpMethod = "POST")
//    @RequestMapping(value = "/doRiskEvaluation", method = RequestMethod.POST)
//    public JsonMessage doRiskEvaluation() throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        User user = userService.selectByPrimaryKey(OnLineUserUtils.getId());
//        user.setRiskEvaluation(1);
//        userService.updateByPrimaryKeySelective(user);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }

//    @ResponseBody
//    @ApiOperation(value = "用户buildRSA", httpMethod = "POST")
//    @RequestMapping(value = "/setting/buildRSA", method = RequestMethod.POST)
//    public JsonMessage buildRSA() throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }

}
