package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.ReqEmailRegister;
import com.anyex.apps.controller.user.req.ReqSendEmail;
import com.anyex.apps.controller.user.req.ReqSendSms;
import com.anyex.apps.controller.user.req.ReqSmsRegister;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户注册 Introduce
 * <p>File：RegisterController.java</p>
 * <p>Title: RegisterController</p>
 * <p>Description: RegisterController</p>
 * <p>Copyright: Copyright (c) 2017/7/4</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.AUTH)
@Api(tags = "用户注册")
public class RegisterController extends GenericController
{
    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    SysMsgRecordService sysMsgRecordService;

    /**
     * 邮箱注册验证码发送
     * @param request
     * @param reqSendEmail 发送邮箱验证码请求对象
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "邮箱注册验证码发送", httpMethod = "POST")
    @RequestMapping(value = "/emailRegister/sendCode", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 5, isLogin = false) // 未登录情况下限制5秒内最多请求1次
    public JsonMessage emailRegisterSendCode(HttpServletRequest request, @Validated @RequestBody ReqSendEmail reqSendEmail) throws BusinessException
    {
        log.info("emailRegisterSendCode reqSendEmail:{}", reqSendEmail);
        //
        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
        {// 验证邮件地址
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip);
        String captchaText = RedisUtils.get(key.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        User user = userService.findByUserNameAndNormal(reqSendEmail.getEmail().toLowerCase());
        if (null != user)
        {// 判断用户是否存在
            return this.getJsonMessage(CommonEnums.ERROR_REGISTER_EXIST);
        }
        //
        sysMsgRecordService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_REGISTERCODE);
        //
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 邮箱注册提交
     * @param reqEmailRegister 邮箱注册用户请求对象
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "邮箱注册提交", httpMethod = "POST")
    @RequestMapping(value = "/emailRegister/submit", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 5, isLogin = false) // 未登录情况下限制5秒内最多请求1次
    public JsonMessage emailRegisterSubmit(HttpServletRequest request, @Validated @RequestBody ReqEmailRegister reqEmailRegister) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info("emailRegisterSubmit reqEmailRegister:{}", reqEmailRegister);
        //
        if (!ValidateUtils.isMailFormat(reqEmailRegister.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
        if (!ValidateUtils.isRegex(reqEmailRegister.getLoginPwd(), 6, 12, true))
        {// 限制密规则
            throw new BusinessException(CommonEnums.ERROR_PASSWORD_TYPE);
        }
        if (!sysMsgRecordService.validEmailCode(reqEmailRegister.getEmail(), reqEmailRegister.getEmailCode(), MessageConst.TEMPLATE_EMAIL_REGISTERCODE))
        {// 验证邮箱码
            return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
        }
        //
        User userDB = userService.findByUserNameAndNormal(reqEmailRegister.getEmail().toLowerCase());
        if (null != userDB)
        {// 判断用户是否存在
            return this.getJsonMessage(CommonEnums.ERROR_REGISTER_EXIST);
        }
        //
        log.info("reqEmailRegister:{}", reqEmailRegister);
        if(StringUtils.isNotEmpty(reqEmailRegister.getInviteCode()))
        {
            User userReferralDB = userService.findByUnid(Long.valueOf(reqEmailRegister.getInviteCode()));
            if (null == userReferralDB)
            {
                reqEmailRegister.setInviteCode(null);
            }
        }
        //
        User user = new User();
        BeanUtils.copyProperties(reqEmailRegister, user);
        user.setUid(0L);
        user.setAvatar("avatar");
        user.setUserName("userName");
        user.setLoginPwd(EncryptUtils.entryptPassword(reqEmailRegister.getLoginPwd()));
        user.setInviteCode(null);
        user.setReferralCode(reqEmailRegister.getInviteCode());
        user.setState(0);
        user.setCreateTime(System.currentTimeMillis());
        log.info("emailRegisterSubmit user:{}", user);
        if (beanValidator(json, user))
        {
            userService.register(user);
        }
        //
        return json;
    }

    /**
     * 手机注册短信码发送
     * @param request
     * @param reqSendSms 发送短信验证码请求对象
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "手机注册短信码发送", httpMethod = "POST")
    @RequestMapping(value = "/mobileRegister/sendSms", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 5, isLogin = false) // 未登录情况下限制5秒内最多请求1次
    public JsonMessage mobileRegisterSendSMS(HttpServletRequest request, @Validated @RequestBody ReqSendSms reqSendSms) throws BusinessException
    {
        log.info("mobileRegisterSendSMS reqSendSms:{}", reqSendSms);
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip);
        String captchaText = RedisUtils.get(key.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        User user = userService.findByMobileNoAndCountry(reqSendSms.getMobileNo(), reqSendSms.getCountry());
        if (null != user)
        {// 判断用户是否存在
            throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
        }
        //
        StringBuffer mobileNum = new StringBuffer(reqSendSms.getCountry()).append(reqSendSms.getMobileNo());
        sysMsgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_REGISTER);
        //
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 手机注册提交
     * @param reqSmsRegister 手机注册用户请求对象
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @ApiOperation(value = "手机注册提交", httpMethod = "POST")
    @RequestMapping(value = "/mobileRegister/submit", method = RequestMethod.POST)
    @AccessLimit(limit = 1, timeScope = 5, isLogin = false) // 未登录情况下限制5秒内最多请求1次
    public JsonMessage mobileRegisterSubmit(HttpServletRequest request, @Validated @RequestBody ReqSmsRegister reqSmsRegister) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info("mobileRegisterSubmit reqSmsRegister:{}", reqSmsRegister);
        //
        if (!ValidateUtils.isRegex(reqSmsRegister.getLoginPwd(), 6, 12, true))
        {// 限制密规则
            throw new BusinessException(CommonEnums.ERROR_PASSWORD_TYPE);
        }
        StringBuffer mobileNo = new StringBuffer(reqSmsRegister.getCountry()).append(reqSmsRegister.getMobileNo());
        if (!sysMsgRecordService.validSMSCode(mobileNo.toString(), reqSmsRegister.getSmsCode(), MessageConst.SMS_VALID_REGISTER))
        {// 验证短信码
            return getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
        }
        //
        User userDB = userService.findByMobileNoAndCountry(reqSmsRegister.getMobileNo(), reqSmsRegister.getCountry());
        if (null != userDB)
        {// 判断用户是否存在
            log.error("The account has been registered");
            throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
        }
        //
        log.info("reqSmsRegister:{}", reqSmsRegister);
        if(StringUtils.isNotEmpty(reqSmsRegister.getInviteCode()))
        {
            User userReferralDB = userService.findByUnid(Long.valueOf(reqSmsRegister.getInviteCode()));
            if (null == userReferralDB)
            {
                reqSmsRegister.setInviteCode(null);
            }
        }
        //
        User user = new User();
        BeanUtils.copyProperties(reqSmsRegister, user);
        user.setUid(0L);
        user.setAvatar("avatar");
        user.setUserName("userName");
        user.setLoginPwd(EncryptUtils.entryptPassword(reqSmsRegister.getLoginPwd()));
        user.setInviteCode(null);
        user.setReferralCode(reqSmsRegister.getInviteCode());
        user.setState(0);
        user.setCreateTime(System.currentTimeMillis());
        log.info("mobileRegisterSubmit user:{}", user);
        if (beanValidator(json, user))
        {
            userService.register(user);
        }
        //
        return json;
    }

//    /**
//     * 注册提交
//     * @param account 账户信息
//     * @return {@link JsonMessage}
//     * @throws BusinessException
//     */
//    @ResponseBody
//    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
//    @ApiOperation(value = "注册提交", httpMethod = "POST")
//    public JsonMessage registerSubmit(HttpServletRequest request, @ModelAttribute Account account, String type, String code) throws BusinessException
//    {
//        try
//        {
//            if (!ValidateUtils.isRegex(account.getLoginPwd(), 8, 32, true))
//            {// 限制密规则
//                throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
//            }
//            if (StringUtils.isNotBlank(type) && "email".equals(type))
//            { // 邮件验证
//                StringBuffer cacheKey = new StringBuffer(GlobalConst.MESSAGE).append(GlobalConst.SEPARATOR).append(account.getEmail());
//                String cacheCode = (String) RedisUtils.getObject(cacheKey.toString());
//                if (null == cacheCode) return this.getJsonMessage(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
//                checkErrorCnt(account.getEmail(), "checkCnt", "email code");
//                if (!code.equals(cacheCode))
//                {
//                    checkErrorCnt(account.getEmail(), "addCnt", "email code");
//                    throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//                }
//                RedisUtils.del(cacheKey.toString());// 删除临时缓存
//            }
//            else
//            { // 手机验证
//                String phoneNum = new StringBuffer(account.getCountry()).append(account.getMobNo()).toString();
//                if (!msgRecordService.validSMSCode(phoneNum, code))
//                {//
//                    throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
//                }
//            }
//            String ipAddr = IPUtil.getOriginalIpAddr(request);
//            if (StringUtils.isNotBlank(ipAddr))
//            {
//                String rigonName = "Unknown address";
//                try
//                {
//                    String[] ipArray = ipAddr.split(",");
//                    for (String ip : ipArray)
//                    {
//                        Location location = GeoIPUtils.getInstance().getLocation(ip);
//                        if (null != location)
//                        {
//                            rigonName = new StringBuilder(ip).append("(").append(location.countryName).append("|").append(location.city).append(")").toString();
//                        }
//                        break;
//                    }
//                }
//                catch (Exception e)
//                {
//                    log.error("GeoIPUtils Exception:" + e.getLocalizedMessage());
//                }
//                account.setLocation(rigonName);
//            }
//            accountService.registerConfirm(account);
//            return getJsonMessage(CommonEnums.SUCCESS);
//        }
//        catch (Exception e)
//        {
//            log.error("registerSubmit Exception:" + e.getLocalizedMessage());
//            StringWriter errorsWriter = new StringWriter();
//            // e.printStackTrace(new PrintWriter(errorsWriter));
//            log.error("registerSubmit StackTrace:" + errorsWriter.toString());
//            throw new BusinessException(e.getLocalizedMessage());
//        }
//    }

    /**
     * 检查用户敏感操作错误次数和记录次数
     * @param accountName
     * @param flag checkCnt 检查次数是否已经超过  addCnt 累计错误数量
     * @param type 错误类型  用于提示
     * @throws BusinessException
     */
    public void checkErrorCnt(String accountName, String flag, String type) throws BusinessException
    {
        String errCntKey = "trade_account_" + type + "_error_cnt_";
        String errFrozenKey = "trade_account_error_frozen_";
        Long cnt = (Long) RedisUtils.getObject(errCntKey + accountName);
        Long frozen = (Long) RedisUtils.getObject(errFrozenKey + accountName);
        if (null == cnt) cnt = 0L;
        if (null == frozen) frozen = 0L;
        // true 检查次数是否已经超过
        if (StringUtils.equalsIgnoreCase("checkCnt", flag))
        {
            if (frozen.longValue() > 0L)
            { throw new BusinessException("Your " + type + " has been wrong over 10 times. The system will unlock your GA for 24 hours."); }
            if (cnt >= 10)
            {
                RedisUtils.putObject(errFrozenKey + accountName, 1L, CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
                throw new BusinessException("Your " + type + "  has been wrong over 10 times. The system will unlock your GA for 24 hours.");
            }
        }
        // false 累计错误数量
        else if (StringUtils.equalsIgnoreCase("addCnt", flag))
        {
            cnt += 1;
            RedisUtils.putObject(errCntKey + accountName, Long.valueOf(cnt), CacheConst.ONE_MINUTE_CACHE_TIME);
            throw new BusinessException(type + " error.");
        }
    }
}
