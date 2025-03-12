package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.model.EmailModel;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.google.Authenticator;
import com.anyex.apps.model.AliyunModel;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息设置 控制器
 * <p>File：UserController.java </p>
 * <p>Title: UserController </p>
 * <p>Description:UserController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping(GlobalConst.USER)
@Api(tags = "用户信息设置")
public class UserController extends GenericController
{
    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    UserCertKycService userCertKycService;

    @Autowired(required = false)
    UserPolicyService userPolicyService;

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
     * 获取用户信息
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "用户信息", httpMethod = "POST")
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public JsonMessage<User> userInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User user = userService.selectByPrimaryKey(OnLineUserUtils.getId());
        //
        return getJsonMessage(CommonEnums.SUCCESS, user);
    }

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

    /**
     * 修改登录密码
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改登录密码", httpMethod = "POST")
    @RequestMapping(value = "/setting/changeLoginPwd", method = RequestMethod.POST)
    public JsonMessage changeLoginPwd(String oldPass, String newPass) throws BusinessException
    {
        if (StringUtils.isBlank(oldPass) || StringUtils.isBlank(newPass))
        {// 判断参数
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (!EncryptUtils.validatePassword(oldPass, userDB.getLoginPwd()))
        {// 检验原始密码
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append("changeLoginPwd")// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= 10)
            {// 操作频率达到10次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), AccountConsts.FROZEN_REASON_CHANGE_PASSWORD);
                SecurityUtils.getSubject().logout(); // 冻结用户后退出当前会话
            }
            return getJsonMessage(UserEnums.USER_PASSWORD_ERROR);
        }
        if (null != userDB && !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        userDB.setLoginPwd(EncryptUtils.entryptPassword(newPass));
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
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "修改交易密码", httpMethod = "POST")
    @RequestMapping(value = "/setting/changeTradePwd", method = RequestMethod.POST)
    public JsonMessage changeFundPwd(String tradePwd, @ModelAttribute PolicyModel policy) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        try
        {
            if (StringUtils.isNotBlank(userDB.getMobileNo()) && UserConsts.SECURITY_POLICY_DEFAULT.equals(userDB.getSecurityPolicy()))
            {
                StringBuffer mobile = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
                if (!sysMsgRecordService.validSMSCode(mobile.toString(), policy.getSms(), "类型"))
                { throw new BusinessException(CommonEnums.ERROR_SMSCODE_VALID_FAILED); }
            }
            else
            {
                userPolicyService.validSecurityPolicy(userDB, policy);
            }
        }
        catch (BusinessException e)
        {
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append("changeFundPwd")// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(userDB.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= 10)
            {// 操作频率达到10次时,锁定用户
//                userService.modifyAccountStatusToFrozen(userDB.getId(), UserConsts.FROZEN_REASON_CHANGE_FUNDPWD);
                return getJsonMessage(CommonEnums.ERROR_FROZEN_USER);
            }
            throw e;
        }
        if (null != userDB && !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        userDB.setTradePolicy(UserConsts.TRADE_POLICY_TWOHOUR);
        userDB.setTradePwd(EncryptUtils.entryptPassword(tradePwd));
        userService.updateByPrimaryKeySelective(userDB);
        /*
         * if (BitmsConst.REMIND_PHONE_SWITCH.equals(BitmsConst.SWITCH_ENABLE))
         * {// 短信提醒
         * if (StringUtils.isNotBlank(principal.getUserMobile()))
         * {// 确保手机已绑定过
         * String vagueMobile = StringUtils.vagueMobile(principal.getUserMobile());
         * String mobile = new StringBuffer(principal.getCountry()).append(principal.getUserMobile()).toString();
         * msgRecordService.sendRemindSMS(mobile, MessageConst.REMIND_CHANGE_FUNDPASS_PHONE, principal.getLang(), vagueMobile,
         * CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
         * }
         * }
         */
//        if (BitmsConst.REMIND_EMAIL_SWITCH.equals(GlobalConst.SWITCH_ENABLE))
//        {// 邮件提醒
//            msgRecordService.sendRemindEmail(principal.getUserMail(), MessageConst.REMIND_CHANGE_FUNDPASS_EMAIL, "en_US", BitmsConst.HOST_EMAIL_LOGO_URL,
//                    principal.getUserMail(), CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS));
//        }
        saveOperationLogs(principal, "modify payment password");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定手机号码
     * @param phone
     * @param location
     * @param validCode
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定手机号码", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindPhone", method = RequestMethod.POST)
    public JsonMessage bindPhone(String phone, String location, String validCode) throws BusinessException
    {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(location) || StringUtils.isBlank(validCode))
        {// 参数需要验证
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        String mobile = new StringBuffer(location).append(phone).toString();
        if (!sysMsgRecordService.validSMSCode(mobile, validCode, "类型"))
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
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (null == userDB || !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        if (StringUtils.isNotBlank(userDB.getMobileNo()))
        {// 表示当前帐户已绑定过手机号,防止用户串改会话ID来修改绑定的手机
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        userDB.setMobileNo(phone);
        userDB.setCountry(location);
        userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGAORSMS);
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind phone");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 发送绑定邮件
     * @param request
     * @param email  邮箱地址
     * @param model   验证码信息
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "发送绑定邮件", httpMethod = "POST")
    @RequestMapping(value = "/setting/sendMail", method = RequestMethod.POST)
    public JsonMessage sendMail(HttpServletRequest request, String email, @ModelAttribute AliyunModel model) throws BusinessException
    {
        if (StringUtils.isBlank(email))
        {// 校验参数,邮件参数必须校验
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
//        if (!AliyunUtils.validParams(model))
//        {// 验证不通过时
//            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA, Boolean.TRUE);
//        }
        if (!ValidateUtils.isMailFormat(email, true, 64))
        {// 验证邮件地址
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
//        if (userService.valiEmail(email))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(UserEnums.ACCOUNT_EMAIL_HAS_BIND);
//        }
        String ipAddr = NetworkUtils.getIpAddr(request);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
//        sysMsgRecordService.sendBindEmail(email, principal.getId(), "en_US", ipAddr);
        sysMsgRecordService.sendEmail(email, "en_US", "模版");
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定邮箱
     * @param email
     * @param emailCode
     * @param smsCode
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定邮箱", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindEmail", method = RequestMethod.POST)
    public JsonMessage bindEmail(String email, String emailCode, String smsCode) throws BusinessException
    {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(emailCode) || StringUtils.isBlank(smsCode))
        {// 参数需要验证
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        String mobileNo = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo()).toString();
        if (!sysMsgRecordService.validSMSCode(mobileNo, smsCode, "类型"))
        {// 判断验证码
            // 开始记录操作次数
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
            {// 操作频率达到30次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), UserConsts.FROZEN_REASON_BIND_EMAIL);
                SecurityUtils.getSubject().logout(); // 冻结用户后登陆当前会话
            }
            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
        }
        StringBuffer cacheKey = new StringBuffer(GlobalConst.MESSAGE).append(GlobalConst.SEPARATOR).append(userDB.getId());
        EmailModel model = (EmailModel) RedisUtils.getObject(cacheKey.toString());
        if (!StringUtils.equals(emailCode, model.getRandomKey()))
        {// 判断验证码
            // 开始记录操作次数
            String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                    .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_EMAIL)// 加入模块标识
                    .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
            int count = userPolicyService.errorOperatorCounter(opCountKey);
            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
            {// 操作频率达到30次时,锁定用户
//                userService.modifyAccountStatusToFrozen(OnLineUserUtils.getId(), UserConsts.FROZEN_REASON_BIND_EMAIL);
                SecurityUtils.getSubject().logout(); // 冻结用户后登陆当前会话
            }
            return getJsonMessage(UserEnums.USER_EMAILCODE_ERROR);
        }
//        if (userService.valiEmail(email))
//        {// 一个邮箱只能绑定一个帐号
//            throw new BusinessException(UserEnums.ACCOUNT_PHONE_HAS_BIND);
//        }
        if (null == userDB || !userDB.verifySignature())
        {// 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        if (StringUtils.isNotBlank(userDB.getEmail()))
        {// 表示当前帐户已绑定过邮箱,防止用户串改会话ID来修改绑定的邮箱
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        userDB.setEmail(email);
        userDB.setUserName(email);
        userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGAORSMS);
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind email");
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定谷歌认证
     * @param secretKey
     * @param gaCode
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindGoogle", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "secretKey", value = "Google私钥", required = true, paramType = "form"),
            @ApiImplicitParam(name = "gaCode", value = "GoogleE验证码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "validCode", value = "短信验证码", required = true, paramType = "form")})
    public JsonMessage bindGoogleAuth(String secretKey, String gaCode, String validCode) throws BusinessException
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
        StringBuffer buffer = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
        if (!sysMsgRecordService.validSMSCode(buffer.toString(), validCode, "类型"))
        {// 手机验证码判断
            return getJsonMessage(UserEnums.USER_SMSCODE_ERROR);
        }
        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
        RedisUtils.putObject(cacheKey, String.valueOf(secretKey), CacheConst.ONE_HOUR_CACHE_TIME);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定谷歌认证
     * @param secretKey
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "校验谷歌认证", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindGoogle/confirm", method = RequestMethod.POST)
    public JsonMessage bindGoogleConfirm(String secretKey) throws BusinessException
    {
        if (StringUtils.isBlank(secretKey))
        {// 参数需要验证
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        String cacheKey = new StringBuffer(CacheConst.GOOGLE_CODE_PERFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_ACCOUNT_BIND_PHONE)// 加入模块标识
                .append(GlobalConst.SEPARATOR).append(OnLineUserUtils.getId()).toString();
        String cacheSecretKey = RedisUtils.get(cacheKey);// 缓存中的GA私钥
        if (!cacheSecretKey.equals(cacheSecretKey))
        {// 判断缓存中的私钥和页面传如的私钥是否匹配，不匹配时抛出异常
            return getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        User userDB = userService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isNotBlank(userDB.getGaAuthKey()))
        {// 判断GA是否已绑定过
            return getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        userDB.setGaAuthKey(EncryptUtils.desEncrypt(secretKey));
        userDB.setSecurityPolicy(UserConsts.SECURITY_POLICY_NEEDGA);
        userService.updateByPrimaryKeySelective(userDB);
        saveOperationLogs(principal, "bind Google Auth");
        RedisUtils.del(cacheKey);// 清除缓存
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 绑定谷歌认证(一步到位控制器入口)
     * @param secretKey
     * @param gaCode
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "绑定谷歌认证", httpMethod = "POST")
    @RequestMapping(value = "/setting/bindGoogleGA", method = RequestMethod.POST)
    public JsonMessage bindGoogleGA(String secretKey, String gaCode, String validCode) throws BusinessException
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
    @ResponseBody
    @ApiOperation(value = "换绑手机号码", httpMethod = "POST")
    @RequestMapping(value = "/setting/changeBindPhone", method = RequestMethod.POST)
    public JsonMessage changeBindPhone(String phone, String location, String validCode, @ModelAttribute PolicyModel policy) throws BusinessException
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
    @ResponseBody
    @ApiOperation(value = "解绑谷歌认证", httpMethod = "POST")
    @RequestMapping(value = "/setting/unBindGoogle", method = RequestMethod.POST)
    public JsonMessage unBindGoogle(@ModelAttribute PolicyModel policy) throws BusinessException
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
}
