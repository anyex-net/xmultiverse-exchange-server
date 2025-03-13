package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.model.EmailModel;
import com.anyex.apps.common.service.SysMsgRecordNoSqlService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.ReqUserForgetPass;
import com.anyex.apps.controller.user.req.ReqUserForgetPassUid;
import com.anyex.apps.controller.user.req.ReqUserResetPassSubmit;
import com.anyex.apps.controller.user.resp.RespUserForgetPass;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.enums.UserEnums;
import com.anyex.apps.controller.user.req.ReqUserResetPassCheck;
import com.anyex.apps.user.service.UserPolicyService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 忘记密码控制器 Introduce
 * <p>File：ForgetPassController.java</p>
 * <p>Title: ForgetPassController</p>
 * <p>Description: ForgetPassController</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.AUTH)
@Api(tags = "找回密码")
public class ForgetPassController extends GenericController
{
    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    UserPolicyService userPolicyService;

    @Autowired(required = false)
    SysMsgRecordNoSqlService sysMsgRecordService;

    /**
     * 确认用户是否存在
     * @param request
     * @param reqUserForgetPass
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/forgetPass/submit")
    @ApiOperation(value = "1确认用户是否存在", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 60, isLogin = false) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage<RespUserForgetPass> submit(HttpServletRequest request, @Validated @RequestBody ReqUserForgetPass reqUserForgetPass /*@ModelAttribute AliyunModel model*/) throws BusinessException
    {
        log.info("submit reqUserForgetPass:{}", reqUserForgetPass);
        if(reqUserForgetPass.getFindType().equals("email")){
            if (StringUtils.isEmpty(reqUserForgetPass.getEmail()))
            {// 校验参数,邮件必须校验
                return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
            }
        } else if(reqUserForgetPass.getFindType().equals("mobile")){
            if (StringUtils.isEmpty(reqUserForgetPass.getMobileNo()) || StringUtils.isEmpty(reqUserForgetPass.getCountry()) )
            {// 校验参数,邮件必须校验
                return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
            }
        } else {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
//        if (!AliyunUtils.validParams(model))
//        {// 验证不通过时
//            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA);
//        }
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer captchaKey = null;
        if(reqUserForgetPass.getFindType().equals("email")){
            captchaKey = new StringBuffer(MessageConst.EMAIL_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip);
        } else if(reqUserForgetPass.getFindType().equals("mobile")){
            captchaKey = new StringBuffer(MessageConst.SMS_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip);
        }
        String captchaText = RedisUtils.get(captchaKey.toString());
        if (captchaText == null || !captchaText.equalsIgnoreCase(reqUserForgetPass.getCaptcha()))
        {// 验证码检验
            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
        }
        //
        User userDB = null;
        if(reqUserForgetPass.getFindType().equals("email")) {
            userDB = userService.findByEmail(reqUserForgetPass.getEmail());
        } else if(reqUserForgetPass.getFindType().equals("mobile")) {
            userDB = userService.findByMobileNoAndCountry(reqUserForgetPass.getMobileNo(), reqUserForgetPass.getCountry());
        }
        if (null == userDB)
        {// 判断帐户是否存在
            errorCounter(request);
            log.error("Illegal Requests");
            return this.getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
        // request.getSession().setAttribute("accountId", account.getId());
        RespUserForgetPass respUserForgetPass = new RespUserForgetPass();
        respUserForgetPass.setUserId(userDB.getId());
        respUserForgetPass.setSecurityPolicy(userDB.getSecurityPolicy());
        respUserForgetPass.setEmail(userDB.getEmail());
        respUserForgetPass.setMobileNo(userDB.getMobileNo());
        respUserForgetPass.setCountry(userDB.getCountry());
        respUserForgetPass.setGaAuthKey(null == userDB.getGaAuthKey() ? null : "*****");
        log.info("respUserForgetPass:{}", respUserForgetPass);
        //
        return getJsonMessage(CommonEnums.SUCCESS, respUserForgetPass);
    }

    /**
     * 找回密码版块发送邮件
     * @param request
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/forgetPass/sendEmail")
    @ApiOperation(value = "2找回密码发送邮件", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 30, isLogin = false) // 未登录情况下限制30秒内最多请求1次
    public JsonMessage sendEmail(HttpServletRequest request, @Validated @RequestBody ReqUserForgetPassUid reqUserForgetPassUid) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        if (null == reqUserForgetPassUid.getUserId()) return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        User userDB = userService.selectByPrimaryKeyNoCheck(reqUserForgetPassUid.getUserId());
        if (null == userDB)
        {// 判断帐户是否存在
            errorCounter(request);
            log.error("Illegal Requests");
            return this.getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        sysMsgRecordService.sendEmail(userDB.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_FORGETPASSCODE);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 找回密码版块发送手机验证码
     * @param request
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/forgetPass/sendSMS")
    @ApiOperation(value = "2找回密码发送短信", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 30, isLogin = false) // 未登录情况下限制30秒内最多请求1次
    public JsonMessage sendSMS(HttpServletRequest request, @Validated @RequestBody ReqUserForgetPassUid reqUserForgetPassUid) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        if (null == reqUserForgetPassUid.getUserId()) return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        User userDB = userService.selectByPrimaryKeyNoCheck(reqUserForgetPassUid.getUserId());
        if (null == userDB)
        {// 判断帐户是否存在
            errorCounter(request);
            log.error("Illegal Requests");
            return this.getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        sysMsgRecordService.sendSms(new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo()).toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_FORGETPASS);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 忘记密码确认页面提交
     * @param request
     * @param reqUserResetPassCheck
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPass/confirmSubmit")
    @ApiOperation(value = "3忘记密码确认页面提交", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 10, isLogin = false) // 未登录情况下限制10秒内最多请求1次
    public JsonMessage confirmSubmit(HttpServletRequest request, @Validated @RequestBody ReqUserResetPassCheck reqUserResetPassCheck) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("accountId");
        Long userId = reqUserResetPassCheck.getUserId();
        if (null == reqUserResetPassCheck || null == userId)
        { // 验证必填参数
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        //
        User userDB = userService.selectByPrimaryKeyNoCheck(userId);
        if (null == userDB)
        {// 判断帐户是否存在
            errorCounter(request);
            log.error("Illegal Requests");
            return this.getJsonMessage(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        //
        if (StringUtils.isNotEmpty(userDB.getEmail()))
        { // 验证邮箱
            if (StringUtils.isEmpty(reqUserResetPassCheck.getEmailCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
//            StringBuffer cacheKey = new StringBuffer(GlobalConst.MESSAGE).append(GlobalConst.SEPARATOR).append(userDB.getId());
//            EmailModel model = (EmailModel) RedisUtils.getObject(cacheKey.toString());
//            if (!StringUtils.equals(reqUserResetPassCheck.getEmailCode(), model.getRandomKey()))
//            {// 判断验证码
//                errorCounter(request);
//                return this.getJsonMessage(CommonEnums.ERROR_EMAIL_VALID_FAILED);
//            }
        }
        if (StringUtils.isNotEmpty(userDB.getMobileNo()))
        {// 验证手机
            if (StringUtils.isEmpty(reqUserResetPassCheck.getSmsCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
//            StringBuffer buffer = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
//            if (!sysMsgRecordService.validSMSCode(buffer.toString(), reqUserResetPassCheck.getSmsCode(), "类型"))
//            {// 判断用户输入的验证码与缓存中的验证码
//                errorCounter(request);
//                return this.getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
//            }
        }
        if (StringUtils.isNotEmpty(userDB.getGaAuthKey()))
        {// 验证GA
            if (StringUtils.isEmpty(reqUserResetPassCheck.getGaCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
//            if (!userPolicyService.validGaCode(userDB.getGaAuthKey(), reqUserResetPassCheck.getGaCode()))
//            {// 判断验证码
//                return getJsonMessage(UserEnums.USER_GACODE_ERROR);
//            }
        }
        // request.getSession().setAttribute("check_status", "true");
        // 返回一个随机码 用于真正重置密码时验证
        String randomKey = SerialnoUtils.buildUUID();
        String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(randomKey).toString();
        RedisUtils.putObject(cacheKey, userDB, CacheConst.DEFAULT_CACHE_TIME);
        //
        return getJsonMessage(CommonEnums.SUCCESS, randomKey);
    }

    /**
     * 忘记密码重置方法
     * @param request
     * @param response
     * @param reqUserResetPassSubmit
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPass/resetPassSubmit", method = RequestMethod.POST)
    @ApiOperation(value = "4忘记密码重置页面提交", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 10, isLogin = false) // 未登录情况下限制10秒内最多请求1次
    public JsonMessage resetPassSubmit(HttpServletRequest request, HttpServletResponse response, @Validated @RequestBody ReqUserResetPassSubmit reqUserResetPassSubmit) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        Long userId = reqUserResetPassSubmit.getUserId();
        if (null == reqUserResetPassSubmit || null == userId ||
                StringUtils.isEmpty(reqUserResetPassSubmit.getRandomCode()) || StringUtils.isEmpty(reqUserResetPassSubmit.getPassword()))
        { // 验证必填参数
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        // String checkStatus = (String) request.getSession().getAttribute("check_status");
        //
        String randomCode = reqUserResetPassSubmit.getRandomCode();
        String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(randomCode).toString();
        User userDB = (User) RedisUtils.getObject(cacheKey);
        if (null == userDB)
        {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        userDB.setLoginPwd(EncryptUtils.entryptPassword(reqUserResetPassSubmit.getPassword()));
        userService.save(userDB);
        // request.getSession().removeAttribute("userId");
        // request.getSession().removeAttribute("check_status");
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 错误统计
     * @param request
     * @throws BusinessException
     */
    void errorCounter(HttpServletRequest request) throws BusinessException
    {
        String userLockKey = new StringBuffer(CacheConst.USER_LOCK_PREFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_FINDPWD)// 加入模块标识
                .append(GlobalConst.SEPARATOR).append(NetworkUtils.getIpAddr(request)).toString();
        String cacheHost = RedisUtils.get(userLockKey);
        if (StringUtils.isNotBlank(cacheHost) && StringUtils.equalsIgnoreCase(cacheHost, NetworkUtils.getIpAddr(request)))
        {// 锁定24小时的IP不允许找回密码
            throw new BusinessException("ip locked");
        }
        // 开始记录操作次数
        int count = 1;
        String opCountKey = new StringBuffer(CacheConst.OPERATION_COUNT_PREFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_FINDPWD)// 加入模块标识
                .append(GlobalConst.SEPARATOR).append(NetworkUtils.getIpAddr(request)).toString();
        String opTimes = RedisUtils.get(opCountKey);
        if (StringUtils.isNotBlank(opTimes))
        {// 表示操作记数缓存中已经存在
            count = Integer.valueOf(opTimes) + 1;
            if (count >= GlobalConst.LOCK_INTERVAL_COUNT)
            {// 操作频率达到30次时,锁定用户
                RedisUtils.putObject(userLockKey, NetworkUtils.getIpAddr(request), CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
            }
        }
        RedisUtils.putObject(opCountKey, String.valueOf(count), CacheConst.ONE_HOUR_CACHE_TIME);
    }
}
