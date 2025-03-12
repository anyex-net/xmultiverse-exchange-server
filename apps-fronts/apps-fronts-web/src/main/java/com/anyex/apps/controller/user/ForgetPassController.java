package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.model.EmailModel;
import com.anyex.apps.common.service.SysMsgRecordNoSqlService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.AliyunModel;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.enums.UserEnums;
import com.anyex.apps.user.model.UserModel;
import com.anyex.apps.user.service.UserPolicyService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    SysMsgRecordNoSqlService sysMsgRecordService;

    @Autowired(required = false)
    UserPolicyService userPolicyService;

    /**
     * 通过帐户确认用户是否存在
     * @param request
     * @param accountName
     * @param model
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/forgetPass/submit")
    @ApiOperation(value = "通过用户确认用户是否存在", httpMethod = "POST")
    public JsonMessage submit(HttpServletRequest request, String accountName, String country, @ModelAttribute AliyunModel model) throws BusinessException
    {
        if (StringUtils.isBlank(accountName))
        {// 校验参数,邮件参数必须校验
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
//        if (!AliyunUtils.validParams(model))
//        {// 验证不通过时
//            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_CAPTCHA);
//        }
        User userDB = userService.findByUserNameAndNormal(accountName, country);
        if (null == userDB)
        {// 判断帐户是否存在
            errorCounter(request);
            return this.getJsonMessage(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        // request.getSession().setAttribute("accountId", account.getId());
        User userReturn = new User();
        userReturn.setId(userDB.getId());
        userReturn.setSecurityPolicy(userDB.getSecurityPolicy());
        userReturn.setEmail(userDB.getEmail());
        userReturn.setMobileNo(userDB.getMobileNo());
        userReturn.setGaAuthKey(null == userDB.getGaAuthKey() ? null : "*****");
        log.info("userReturn:{}", userReturn);
        return getJsonMessage(CommonEnums.SUCCESS, userReturn);
    }

    /**
     * 忘记密码确认页面提交
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPass/confirmSubmit")
    @ApiOperation(value = "忘记密码确认页面提交", httpMethod = "POST")
    public JsonMessage confirmSubmit(HttpServletRequest request, UserModel param) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("accountId");
        Long userId = param.getId();
        if (null == param || null == userId)
        { // 验证必填参数
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        User userDB = userService.selectByPrimaryKeyNoCheck(userId);
        if (StringUtils.isNotBlank(userDB.getEmail()))
        { // 验证邮箱
            if (StringUtils.isBlank(param.getEmailCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
            StringBuffer cacheKey = new StringBuffer(GlobalConst.MESSAGE).append(GlobalConst.SEPARATOR).append(userDB.getId());
            EmailModel model = (EmailModel) RedisUtils.getObject(cacheKey.toString());
            if (!StringUtils.equals(param.getEmailCode(), model.getRandomKey()))
            {// 判断验证码
                errorCounter(request);
                return this.getJsonMessage(CommonEnums.ERROR_EMAIL_VALID_FAILED);
            }
        }
        if (StringUtils.isNotBlank(userDB.getMobileNo()))
        {// 验证手机
            if (StringUtils.isBlank(param.getSmsCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
            StringBuffer buffer = new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo());
            if (!sysMsgRecordService.validSMSCode(buffer.toString(), param.getSmsCode(), "类型"))
            {// 判断用户输入的验证码与缓存中的验证码
                errorCounter(request);
                return this.getJsonMessage(CommonEnums.ERROR_SMSCODE_VALID_FAILED);
            }
        }
        if (StringUtils.isNotBlank(userDB.getGaAuthKey()))
        {// 验证GA
            if (StringUtils.isBlank(param.getGaCode()))
            { return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID); }
            if (!userPolicyService.validGaCode(userDB.getGaAuthKey(), param.getGaCode()))
            {// 判断验证码
                return getJsonMessage(UserEnums.ACCOUNT_GACODE_ERROR);
            }
        }
        // request.getSession().setAttribute("check_status", "true");
        String randomKey = SerialnoUtils.buildUUID();
        String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(randomKey).toString();
        RedisUtils.putObject(cacheKey, userDB, CacheConst.DEFAULT_CACHE_TIME);

        return getJsonMessage(CommonEnums.SUCCESS, randomKey);
    }

    /**
     * 忘记密码重置方法
     * @param request
     * @param response
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/forgetPass/resetPassSubmit", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码重置页面提交", httpMethod = "POST")
    public JsonMessage resetPassSubmit(HttpServletRequest request, HttpServletResponse response, String authCode, UserModel param) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        Long userId = param.getId();
        // String checkStatus = (String) request.getSession().getAttribute("check_status");
        String cacheKey = new StringBuffer(CacheConst.RESET_SECURITY_PREFIX).append(GlobalConst.SEPARATOR).append(authCode).toString();
        User userDB = (User) RedisUtils.getObject(cacheKey);
        if (null == userDB)
        {
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        if (null == userId || StringUtils.isBlank(authCode) || null == param || StringUtils.isBlank(param.getPassword()))
        {//
            return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        }
        // Account account = accountService.selectByPrimaryKey(accountId);
        userDB.setLoginPwd(EncryptUtils.entryptPassword(param.getPassword()));
        userService.save(userDB);
        request.getSession().removeAttribute("accountId");
        request.getSession().removeAttribute("check_status");
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
    @ApiOperation(value = "发送短信", httpMethod = "POST")
    public JsonMessage sendSMS(HttpServletRequest request, Long userId) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        if (null == userId) return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        User userDB = userService.selectByPrimaryKeyNoCheck(userId);
        String lang = "en_US";
        sysMsgRecordService.sendSms(new StringBuffer(userDB.getCountry()).append(userDB.getMobileNo()).toString(), lang, "类型");
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 找回密码版块发送邮件
     * @param request
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/forgetPass/sendEmail")
    @ApiOperation(value = "发送邮件", httpMethod = "POST")
    public JsonMessage sendEmail(HttpServletRequest request, Long userId) throws BusinessException
    {
        // Long userId = (Long) request.getSession().getAttribute("userId");
        if (null == userId) return this.getJsonMessage(CommonEnums.ERROR_PARAMS_VALID);
        User userDB = userService.selectByPrimaryKeyNoCheck(userId);
        String lang = "en_US";
//        sysMsgRecordService.sendEmailForgetPasswordCode(userDB.getEmail(), lang, null);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    /**
     * 错误统计
     * @param request
     * @throws BusinessException
     */
    void errorCounter(HttpServletRequest request) throws BusinessException
    {
        String accountLockKey = new StringBuffer(CacheConst.USER_LOCK_PREFIX)// 加入缓存前缀
                .append(GlobalConst.SEPARATOR).append(GlobalConst.OP_FINDPWD)// 加入模块标识
                .append(GlobalConst.SEPARATOR).append(NetworkUtils.getIpAddr(request)).toString();
        String cacheHost = RedisUtils.get(accountLockKey);
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
                RedisUtils.putObject(accountLockKey, NetworkUtils.getIpAddr(request), CacheConst.TWENTYFOUR_HOUR_CACHE_TIME);
            }
        }
        RedisUtils.putObject(opCountKey, String.valueOf(count), CacheConst.ONE_HOUR_CACHE_TIME);
    }
}
