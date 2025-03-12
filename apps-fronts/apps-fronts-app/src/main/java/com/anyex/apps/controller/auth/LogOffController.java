package com.anyex.apps.controller.auth;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.auth.req.ReqCheckEmail;
import com.anyex.apps.controller.auth.req.ReqSendEmail;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.interceptor.AccessLimit;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.openim.service.OpenImApiService;
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

/**
 * 账户注销控制器
 * <p>File：LogOffController.java</p>
 * <p>Title: LogOffController</p>
 * <p>Description: LogOffController</p>
 * <p>Copyright: Copyright (c) 2019/10/22</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/logoff")
@Api(tags = "账户注销")
public class LogOffController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @PostMapping("/email/send")
    @ApiOperation(value = "邮箱码发送(账户注销)", httpMethod = "POST")
    @AccessLimit(limit = 1, timeScope = 60, isLogin = false) // 未登录情况下限制60秒内最多请求1次
    public JsonMessage sendEmail(HttpServletRequest request, @RequestBody ReqSendEmail reqSendEmail) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info(reqSendEmail.toString());
        //
        if (!ValidateUtils.isMailFormat(reqSendEmail.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer(MessageConst.EMAIL_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip);
//        String captchaText = RedisUtils.get(key.toString());
//        //
//        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendEmail.getKaptcha()))
//        {// 验证码检验
//            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//        }
        //
        Account account = accountService.findByEmail(reqSendEmail.getEmail());
        if (null == account)
        {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        //
        msgRecordService.sendEmail(reqSendEmail.getEmail(), GlobalConst.DEFAULT_LANG, MessageConst.TEMPLATE_EMAIL_OTHERCODE);
        //
        return json;
    }

    @PostMapping("/email/logoff")
    @ApiOperation(value = "邮箱码账户注销", httpMethod = "POST")
    public JsonMessage emailLogoff(HttpServletRequest request, @RequestBody ReqCheckEmail reqCheckEmail) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        //
        if (!ValidateUtils.isMailFormat(reqCheckEmail.getEmail(), true, 64))
        {// 验证邮件格式
            throw new BusinessException(CommonEnums.ERROR_EMAIL_FORMAT_FAILED);
        }
        if (!msgRecordService.validEmailCode(reqCheckEmail.getEmail(), reqCheckEmail.getEmailCode(), MessageConst.TEMPLATE_EMAIL_OTHERCODE))
        {// 验证邮箱码
            return getJsonMessage(CommonEnums.ERROR_EMAILCODE_VALID_FAILED);
        }
        //
        Account account = accountService.findByEmail(reqCheckEmail.getEmail());
        if(null == account) {
            log.error("邮箱码账户注销 没找到对应的账户信息");
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        account.setStatus(2); // 注销
        log.info("logoff account:{}", account.toString());
        accountService.updateByPrimaryKeySelective(account);
        //
        forceLogout(account.getUserId());
        return json;
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
}
