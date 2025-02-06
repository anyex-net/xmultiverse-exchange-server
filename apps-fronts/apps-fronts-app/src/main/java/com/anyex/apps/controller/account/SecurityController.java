//package com.anyex.apps.controller.account;
//
//import com.anyex.am.account.entity.AccountOpRecord;
//import com.anyex.am.account.model.req.*;
//import com.anyex.am.account.service.AccountOpRecordNoSqlService;
//import com.anyex.am.common.service.MsgRecordNoSqlService;
//import com.anyex.am.constant.CacheConst;
//import com.anyex.am.constant.account.AccountConst;
//import com.anyex.am.account.entity.Account;
//import com.anyex.am.constant.common.MessageConst;
//import com.anyex.am.controller.common.GenericAppController;
//import com.anyex.am.enums.account.AccountEnums;
//import com.anyex.am.enums.account.SecurityPolicyEnums;
//import com.anyex.am.enums.account.TradePolicyEnums;
//import com.anyex.am.account.service.AccountPolicyService;
//import com.anyex.am.account.service.AccountService;
//import com.anyex.am.constant.GlobalConst;
//import com.anyex.am.enums.CommonEnums;
//import com.anyex.am.exception.BusinessException;
//import com.anyex.am.google.Authenticator;
//import com.anyex.am.model.JsonMessage;
//import com.anyex.am.shiro.model.UserPrincipal;
//import com.anyex.am.utils.*;
//import com.google.common.collect.Maps;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import io.swagger.annotations.ApiOperation;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// * SecurityController
// * <p>File：SecurityController.java</p>
// * <p>Title: SecurityController</p>
// * <p>Description: SecurityController</p>
// * <p>Copyright: Copyright (c) 2019/11/4</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//@RestController
//@RequestMapping(GlobalConst.ACCOUNT)
//@Api(description = "帐户安全中心")
//public class SecurityController extends GenericAppController
//{
//    @Autowired(required = false)
//    private AccountService accountService;
//
//    @Autowired(required = false)
//    private AccountPolicyService accountPolicyService;
//
//    @Autowired(required = false)
//    private MsgRecordNoSqlService msgRecordNoSqlService;
//
//    @Autowired(required = false)
//    private AccountOpRecordNoSqlService accountOpRecordNoSqlService;
//
//    @PostMapping("/security/bindEmail/emailSend")
//    @ApiOperation(value = "发送邮箱验证码", httpMethod = "POST")
//    public JsonMessage sendMail(HttpServletRequest request, @RequestBody ReqSendEmail reqSendEmail) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
//        {// 验证邮件格式
//            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
//        }
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        //
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getKaptcha()))
//        {// 验证码检验
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
//        //
//        if (accountService.valiEmail(reqSendEmail.getEmail()))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(AccountEnums.ACCOUNT_EMAIL_HAS_BIND);
//        }
//        //
//        msgRecordNoSqlService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_BINDSENDCODE);
//        //
//        return json;
//    }
//
//    @PostMapping("/security/bindEmail/smsSend")
//    @ApiOperation(value = "发送手机短信码", httpMethod = "POST")
//    public JsonMessage sendSMS4BindEmail(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//        {
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
//        Account account = accountService.findByPhone(reqSendSms.getMobNo());
//        if (null == account)
//        {
//            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
//        }
//        StringBuffer phoneNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobNo());
//        msgRecordNoSqlService.sendSms(phoneNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_OTHER);
//        //
//        return json;
//    }
//
//    @PostMapping("/security/bindEmail")
//    @ApiOperation(value = "绑定邮箱", httpMethod = "POST")
//    public JsonMessage bindEmail(HttpServletRequest request, @RequestBody ReqBindEmail reqBindEmail) throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (null == account || !account.verifySignature())
//        {// 校验数据
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        if (StringUtils.isNotBlank(account.getEmail()))
//        {// 表示当前帐户已绑定过邮箱,防止用户串改会话ID来修改绑定的邮箱
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        String mobile = new StringBuffer(account.getCountry()).append(account.getMobNo()).toString();
//        if (!msgRecordNoSqlService.validSMSCode(mobile, reqBindEmail.getSmsCode(), MessageConst.SMS_VALID_OTHER))
//        {// 判断验证码
////            // 开始记录操作次数
////            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
////                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
////                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
////            int count = accountPolicyService.errorOperatorCounter(opCountKey);
////            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
////            {// 操作频率达到30次时,锁定用户
////                accountService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_BIND_EMAIL);
////                SecurityUtils.getSubject().logout(); // 冻结用户后登录当前会话
////            }
//            return getJsonMessage(AccountEnums.ACCOUNT_SMSCODE_ERROR);
//        }
//        String cacheKey = new StringBuffer(CacheConst.CACHE_SEND_EMAIL_PERFIX)//
//                .append(GlobalConst.SEPARATOR).append(MessageConst.TEMPLATE_EMAIL_BINDSENDCODE)
//                .append(GlobalConst.SEPARATOR).append(reqBindEmail.getEmail())//
//                .toString();
//        String randomNum = RedisUtils.get(cacheKey);
//        if (!StringUtils.equals(reqBindEmail.getEmailCode(), randomNum))
//        {// 判断验证码
////            // 开始记录操作次数
////            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
////                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_EMAIL)// 加入模块标识
////                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
////            int count = accountPolicyService.errorOperatorCounter(opCountKey);
////            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
////            {// 操作频率达到30次时,锁定用户
////                accountService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_BIND_EMAIL);
////                SecurityUtils.getSubject().logout(); // 冻结用户后登录当前会话
////            }
//            return getJsonMessage(AccountEnums.ACCOUNT_EMAILCODE_ERROR);
//        }
//        if (accountService.valiEmail(reqBindEmail.getEmail()))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(AccountEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
//        account.setEmail(reqBindEmail.getEmail());
//        account.setAccountName(reqBindEmail.getEmail());
//        // account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDGAORSMS);
//        account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDSMS);
//        accountService.updateByPrimaryKey(account);
//        //
//        saveOperationLogs(principal, "securitySetting", "bind email");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    @PostMapping("/security/bindPhone/smsSend")
//    @ApiOperation(value = "发送手机短信码", httpMethod = "POST")
//    public JsonMessage sendSMS4BindPhone(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//        {
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
////        Account account = accountService.findByPhone(reqSendSms.getMobNo());
////        if (null == account)
////        {
////            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
////        }
//        StringBuffer phoneNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobNo());
//        msgRecordNoSqlService.sendSms(phoneNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_OTHER);
//        //
//        return json;
//    }
//
//    @PostMapping("/security/bindPhone")
//    @ApiOperation(value = "绑定手机号码", httpMethod = "POST")
//    public JsonMessage bindPhone(HttpServletRequest request, @RequestBody ReqBindPhone reqBindPhone) throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (null == account || !account.verifySignature())
//        {// 校验数据
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        if (StringUtils.isNotBlank(account.getMobNo()))
//        {// 表示当前帐户已绑定过手机号,防止用户串改会话ID来修改绑定的手机
//            throw new BusinessException(AccountEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
//        //
//        String mobile = new StringBuffer(reqBindPhone.getCountry()).append(reqBindPhone.getMobNo()).toString();
//        if (!msgRecordNoSqlService.validSMSCode(mobile, reqBindPhone.getSmsCode(), MessageConst.SMS_VALID_OTHER))
//        {// 判断验证码
////            // 开始记录操作次数
////            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
////                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
////                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
////            int count = accountPolicyService.errorOperatorCounter(opCountKey);
////            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
////            {// 操作频率达到30次时,锁定用户
////                accountService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_BIND_PHONE);
////                SecurityUtils.getSubject().logout(); // 冻结用户后登录当前会话
////            }
//            return getJsonMessage(AccountEnums.ACCOUNT_SMSCODE_ERROR);
//        }
//        if (accountService.checkBindPhone(reqBindPhone.getMobNo()))
//        {// 一个手机号只能绑定一个帐号
//            throw new BusinessException(AccountEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
//        account.setCountry(reqBindPhone.getCountry());
//        account.setMobNo(reqBindPhone.getMobNo());
//        // account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDGAORSMS);
//        account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDSMS);
//        accountService.updateByPrimaryKey(account);
//        //
//        saveOperationLogs(principal, "securitySetting", "bind phone");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
////    @PostMapping("/security/bindGoogle")
////    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
//    public JsonMessage bindGoogle(String secretKey, String gaCode, String smsCode) throws BusinessException
//    {
//        Authenticator authenticator = new Authenticator();
//        if (StringUtils.isBlank(smsCode) || StringUtils.isBlank(gaCode) || StringUtils.isBlank(secretKey))
//        {// 参数需要验证
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        if (!authenticator.checkCode(secretKey, Long.valueOf(gaCode)))
//        {// 判断验证码
//            return getJsonMessage(AccountEnums.ACCOUNT_GACODE_ERROR);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        StringBuffer buffer = new StringBuffer(account.getCountry()).append(account.getMobNo());
//        if (!msgRecordNoSqlService.validSMSCode(buffer.toString(), smsCode, MessageConst.SMS_VALID_OTHER))
//        {// 手机验证码判断
//            return getJsonMessage(AccountEnums.ACCOUNT_SMSCODE_ERROR);
//        }
//        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
//                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//        RedisUtils.putObject(cacheKey, String.valueOf(secretKey), CacheConst.ONE_HOUR_CACHE_TIME);
//        //
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
////    @PostMapping("/security/bindGoogle/confirm")
////    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
//    public JsonMessage bindGoogleConfirm(String secretKey) throws BusinessException
//    {
//        if (StringUtils.isBlank(secretKey))
//        {// 参数需要验证
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
//                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
//                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
//        String cacheSecretKey = RedisUtils.get(cacheKey);// 缓存中的GA私钥
//        if (!cacheSecretKey.equals(cacheSecretKey))
//        {// 判断缓存中的私钥和页面传如的私钥是否匹配，不匹配时抛出异常
//            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (StringUtils.isNotBlank(account.getGaauthKey()))
//        {// 判断GA是否已绑定过
//            return getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
//        }
//        account.setGaauthKey(EncryptUtils.desEncrypt(secretKey));
//        account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDGA);
//        accountService.updateByPrimaryKey(account);
////        saveOperationLogs(principal, "bind Google Auth");
//        RedisUtils.del(cacheKey);// 清除缓存
//        //
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    /**
//     * 生成GoogleAuth私钥
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @PostMapping(value = "/security/bindGoogleAuth/buildGASecretKey")
//    @ApiOperation(value = "生成GoogleAuth私钥", httpMethod = "POST")
//    public JsonMessage buildGASecretKey() throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal)
//        {// 用户必须登录
//            throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        }
//        if (StringUtils.isBlank(principal.getUserMail()))
//        {// 避免用户通过手机号注册之后邮箱未绑定的前提下，重新查数据库
//            Account account = accountService.selectByPrimaryKey(principal.getId());
//            principal.setUserMail(account.getEmail());
//        }
//        Map<String, String> result = Maps.newHashMap();
//        String issuer = GlobalConst.PROJECT_NAME;
//        String gaSecretKey = Authenticator.generateSecretKey();
//        result.put("gaSecretKey", gaSecretKey);
//        result.put("email", principal.getUserMail());
//        result.put("gaInfo", "otpauth://totp/" + principal.getUserMail() + "?secret=" + gaSecretKey + "&issuer=" + issuer);
//        return this.getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
//    @PostMapping("/security/bindGoogleAuth/smsSend")
//    @ApiOperation(value = "发送手机短信码", httpMethod = "POST")
//    public JsonMessage sendSMS4BindGoogleAuth(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
//        {
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
//        Account account = accountService.findByPhone(reqSendSms.getMobNo());
//        if (null == account)
//        {
//            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
//        }
//        StringBuffer phoneNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobNo());
//        msgRecordNoSqlService.sendSms(phoneNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_OTHER);
//        //
//        return json;
//    }
//
//    @PostMapping("/security/bindGoogleAuth")
//    @ApiOperation(value = "绑定谷歌认证(一步到位)", httpMethod = "POST")
//    public JsonMessage bindGoogleAuth(HttpServletRequest request, @RequestBody ReqBindGA reqBindGA) throws BusinessException
//    {
//        Authenticator authenticator = new Authenticator();
//        if (!authenticator.checkCode(reqBindGA.getGaSecretKey(), Long.valueOf(reqBindGA.getGaCode())))
//        {// 判断验证码
//            return getJsonMessage(AccountEnums.ACCOUNT_GACODE_ERROR);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (StringUtils.isEmpty(account.getEmail()))
//        {// 判断邮箱是否绑定
//            return getJsonMessage(AccountEnums.ACCOUNT_EMAIL_NOTBIND);
//        }
//        if (StringUtils.isEmpty(account.getMobNo()))
//        {// 判断手机是否绑定
//            return getJsonMessage(AccountEnums.ACCOUNT_PHONE_NOTBIND);
//        }
//        if (StringUtils.isNotBlank(account.getGaauthKey()))
//        {// 判断GA是否已绑定过
//            return getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
//        }
//        StringBuffer buffer = new StringBuffer(account.getCountry()).append(account.getMobNo());
//        if (!msgRecordNoSqlService.validSMSCode(buffer.toString(), reqBindGA.getSmsCode(), MessageConst.SMS_VALID_OTHER))
//        {// 手机验证码判断
//            return getJsonMessage(AccountEnums.ACCOUNT_SMSCODE_ERROR);
//        }
//        // 账户实体类更新
//        account.setGaauthKey(EncryptUtils.desEncrypt(reqBindGA.getGaSecretKey()));
//        account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDGA); // 安全策略
//        accountService.updateByPrimaryKey(account);
//        //
//        saveOperationLogs(principal, "securitySetting", "bind Google Auth");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
////    @PostMapping("/security/changeBindPhone")
////    @ApiOperation(value = "换绑手机号码", httpMethod = "POST")
//    public JsonMessage changeBindPhone(String phone, String location, String smsCode, @ModelAttribute ReqPolicy reqPolicy) throws BusinessException
//    {
//        if (StringUtils.isBlank(phone) || StringUtils.isBlank(smsCode) || StringUtils.isBlank(location))
//        {// 必传参数校验
//            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        accountPolicyService.validSecurityPolicy(account, reqPolicy);
//        if (!accountPolicyService.validSMSCode(new StringBuffer(location).append(phone).toString(), smsCode))
//        {// 新手机校验失败
//            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        }
//        account.setMobNo(phone);
//        account.setCountry(location);
//        accountService.updateByPrimaryKey(account);
//        /*
//         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 短信提醒
//         * if (StringUtils.isNotBlank(principal.getUserMobile()))
//         * {// 确保手机已绑定过
//         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
//         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
//         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_PHONE_PHONE, principal.getLang(), vagueMobile,
//         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         * }
//         */
//        /*
//         * if (BitmsConst.REMIND_EMAIL_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 邮件提醒
//         * if (StringUtils.isNotBlank(principal.getUserMobile())) msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_PHONE_EMAIL,
//         * principal.getLang(), principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         */
////        saveOperationLogs(principal, "change phone");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
////    @PostMapping("/security/unBindGoogle")
////    @ApiOperation(value = "解绑谷歌认证", httpMethod = "POST")
//    public JsonMessage unBindGoogle(@ModelAttribute ReqPolicy reqPolicy) throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        accountPolicyService.validSecurityPolicy(account, reqPolicy);
//        if (AccountConst.SECURITY_POLICY_NEEDGAANDSMS.equals(account.getSecurityPolicy()))
//        {// 判断用户安全等级，如是同时启用了手GA和短信，此时由系统自动将安全等级降低成或
//            account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDGAORSMS);
//        }
//        else if (AccountConst.SECURITY_POLICY_NEEDGA.equals(account.getSecurityPolicy()))
//        {// 判断用户安全等级，如是启用了GA，此时由系统自动将安全等级降低成短信
//            account.setSecurityPolicy(AccountConst.SECURITY_POLICY_NEEDSMS);
//        }
//        account.setGaauthKey(null);// 请空GOOGLE密匙
//        accountService.updateByPrimaryKey(account);
//        /*
//         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 短信提醒
//         * if (StringUtils.isNotBlank(principal.getUserMobile()))
//         * {// 确保手机已绑定过
//         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
//         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
//         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_GOOGLE_PHONE, principal.getLang(), vagueMobile,
//         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         * }
//         * if (BitmsConst.REMIND_EMAIL_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 邮件提醒
//         * msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_GOOGLE_EMAIL, principal.getLang(), principal.getUserMail(),
//         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         */
////        saveOperationLogs(principal, "unbind Google Auth");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    @PostMapping("/security/changeLoginPwd")
//    @ApiOperation(value = "修改登录密码", httpMethod = "POST")
//    public JsonMessage changeLoginPwd(HttpServletRequest request, @RequestBody ReqChangePassword reqChangePassword) throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (null != account && !account.verifySignature())
//        {// 校验数据
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        if (!EncryptUtils.validatePassword(reqChangePassword.getOldPassword(), account.getLoginPwd()))
//        {// 检验原始密码
////            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
////                    .append(GlobalConst.SEPARATOR).append("changeLoginPwd")// 加入模块标识
////                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
////            int count = accountPolicyService.errorOperatorCounter(opCountKey);
////            if (count >= 10)
////            {// 操作频率达到10次时,锁定用户
////                accountService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_CHANGE_PASSWORD);
////                SecurityUtils.getSubject().logout(); // 冻结用户后退出当前会话
////            }
//            return getJsonMessage(AccountEnums.ACCOUNT_PASSWORD_ERROR);
//        }
//        account.setLoginPwd(EncryptUtils.entryptPassword(reqChangePassword.getNewPassword()));
//        accountService.updateByPrimaryKey(account);
//        /*
//         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
//         * {// 短信提醒
//         * if (StringUtils.isNotBlank(principal.getUserMobile()))
//         * {// 确保手机已绑定过
//         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
//         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
//         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_LOGINPASS_PHONE, principal.getLang(), vagueMobile,
//         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//         * }
//         * }
//         */
////        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
////        {// 邮件提醒
////            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_LOGINPASS_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL,
////                    principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
////        }
//        saveOperationLogs(principal, "securitySetting", "modify login password");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    @PostMapping("/security/changeCashPwd")
//    @ApiOperation(value = "修改资金密码", httpMethod = "POST")
//    public JsonMessage changeCashPwd(HttpServletRequest request, @RequestBody ReqPolicy reqPolicy) throws BusinessException
//    {
//        log.info("reqPolicy:{}", reqPolicy);
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (null != account && !account.verifySignature())
//        {// 校验数据
//            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//        }
//        try
//        {
//            if (StringUtils.isNotBlank(account.getMobNo()) && AccountConst.SECURITY_POLICY_DEFAULT.equals(account.getSecurityPolicy()))
//            {
//                StringBuffer mobile = new StringBuffer(account.getCountry()).append(account.getMobNo());
//                if (!msgRecordNoSqlService.validSMSCode(mobile.toString(), reqPolicy.getSmsCode(), MessageConst.SMS_VALID_MODIFYPASS))
//                {
//                    throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//                }
//            }
//            else
//            {
//                accountPolicyService.validSecurityPolicy(account, reqPolicy);
//            }
//        }
//        catch (BusinessException e)
//        {
////            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
////                    .append(GlobalConst.SEPARATOR).append("changeFundPwd")// 加入模块标识
////                    .append(GlobalConst.SEPARATOR).append(account.getId()).toString();
////            int count = accountPolicyService.errorOperatorCounter(opCountKey);
////            if (count >= 10)
////            {// 操作频率达到10次时,锁定用户
////                accountService.modifyAccountStatusToFrozen(account.getId(), AccountConsts.FROZEN_REASON_CHANGE_FUNDPWD);
////                return getJsonMessage(CommonEnums.ERROR_FROZEN_ACCOUNT);
////            }
//            throw e;
//        }
//        account.setTradePolicy(AccountConst.TRADE_POLICY_TWOHOUR);
//        account.setCashPwd(EncryptUtils.entryptPassword(reqPolicy.getCashPwd()));
//        accountService.updateByPrimaryKey(account);
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
//        saveOperationLogs(principal, "securitySetting", "modify cash password");
//        return this.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    @GetMapping(value = "/security/loginLogs")
//    @ApiOperation(value = "账户登录日志", httpMethod = "GET")
//    public JsonMessage loginLogs() throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        List<AccountOpRecord> result = accountOpRecordNoSqlService.findLastTenLoginLogs(principal.getId());
//        return this.getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
//    @GetMapping(value = "/security/settingLogs")
//    @ApiOperation(value = "账户安全设置日志", httpMethod = "GET")
//    public JsonMessage settingLogs() throws BusinessException
//    {
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        List<AccountOpRecord> result = accountOpRecordNoSqlService.findLastTenSecuritySettingLogs(principal.getId());
//        return this.getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
//    /////////////////////////////////////////////////////////////
////    @GetMapping(value = "/security/getSecurityPolicy")
////    @ApiOperation(value = "获取安全验证策略", httpMethod = "GET")
//    public JsonMessage getSecurityPolicy() throws BusinessException
//    {
//        List<SecurityPolicyEnums> EnumConstants = EnumUtils.toList(SecurityPolicyEnums.class);
//        Map<String, Object> result = Maps.newHashMap();
//        for (SecurityPolicyEnums securityPolicyEnums : EnumConstants)
//        {
//            result.put(String.valueOf(securityPolicyEnums.code), securityPolicyEnums.getMessage());
//        }
//        return super.getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
////    @GetMapping(value = "/security/getTradePolicy")
////    @ApiOperation(value = "获取交易验证策略", httpMethod = "GET")
//    public JsonMessage getTradePolicy() throws BusinessException
//    {
//        List<TradePolicyEnums> EnumConstants = EnumUtils.toList(TradePolicyEnums.class);
//        Map<String, Object> result = Maps.newHashMap();
//        for (TradePolicyEnums policyEnums : EnumConstants)
//        {
//            result.put(String.valueOf(policyEnums.code), policyEnums.getMessage());
//        }
//        return super.getJsonMessage(CommonEnums.SUCCESS, result);
//    }
//
////    @PostMapping("/security/setSecurityPolicy")
////    @ApiOperation(value = "设置安全验证策略", httpMethod = "POST", notes = "policyLevel不可为空")
//    public JsonMessage setSecurityPolicy(Integer policyLevel, @ModelAttribute ReqPolicy reqPolicy) throws BusinessException
//    {
//        if (policyLevel == null) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        //
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (AccountConst.SECURITY_POLICY_NEEDGA == policyLevel)
//        {// 判断是否已绑定GA
//            if (StringUtils.isBlank(account.getGaauthKey()))
//            {
//                throw new BusinessException(CommonEnums.ERROR_GA_NOT_BIND);
//            }
//        }
//        if (AccountConst.SECURITY_POLICY_NEEDSMS == policyLevel)
//        {// 判断是否已绑定SMS
//            if (StringUtils.isBlank(account.getMobNo()))
//            {
//                throw new BusinessException(AccountEnums.ACCOUNT_PHONE_NOTBIND);
//            }
//        }
//        if (AccountConst.SECURITY_POLICY_NEEDGAANDSMS == policyLevel)
//        {// 判断是否已绑定SMS和GA
//            if (StringUtils.isBlank(account.getGaauthKey()))
//            {
//                throw new BusinessException(CommonEnums.ERROR_GA_NOT_BIND);
//            }
//            if (StringUtils.isBlank(account.getMobNo()))
//            {
//                throw new BusinessException(AccountEnums.ACCOUNT_PHONE_NOTBIND);
//            }
//        }
//        //
//        accountPolicyService.validSecurityPolicy(account, reqPolicy);
//        //
//        account.setSecurityPolicy(policyLevel);
//        accountService.updateByPrimaryKey(account);
//        return super.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
////    @PostMapping("/security/setTradePolicy")
////    @ApiOperation(value = "设置交易验证策略", httpMethod = "POST", notes = "policyLevel不可为空")
//    public JsonMessage setTradePolicy(Integer policyLevel, @ModelAttribute ReqPolicy reqPolicy) throws BusinessException
//    {
//        if (policyLevel == null) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
//        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
//        Account account = accountService.selectByPrimaryKey(principal.getId());
//        if (AccountConst.TRADE_POLICY_TWOHOUR == policyLevel || AccountConst.TRADE_POLICY_EVERYTIME == policyLevel)
//        {// 如果启动资金密码后需要先判断用户是否已设置资金密码
//            if (StringUtils.isBlank(account.getCashPwd()))
//            {
//                throw new BusinessException(CommonEnums.ERROR_CASHPWD_VALID_NOEXIST);
//            }
//        }
//        //
//        accountPolicyService.validSecurityPolicy(account, reqPolicy);
//        //
//        account.setTradePolicy(policyLevel);
//        accountService.updateByPrimaryKey(account);
//        return super.getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//}
