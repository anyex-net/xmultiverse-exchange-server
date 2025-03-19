package com.anyex.apps.controller.user;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.consts.MessageConst;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.user.req.ReqCaptcha;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.NetworkUtils;
import com.anyex.apps.utils.RedisUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 随机验证码生成器
 * <p>File: CaptchaController.java </p>
 * <p>Title: CaptchaController </p>
 * <p>Description: CaptchaController </p>
 * <p>Copyright: Copyright (c) 2019-05-21</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.AUTH)
@Api(tags = "随机验证码")
public class CaptchaController extends GenericController
{
    @Autowired(required = false)
    private Producer producer;

    /**
     * 生成谷歌随机验证码文本
     * @param request
     * @param reqCaptcha
     * @return
     * @throws BusinessException
     */
    @PostMapping(value = "/captcha")
    @ApiOperation(value = "生成随机验证码", httpMethod = "POST")
    public JsonMessage createCaptcha(HttpServletRequest request, @Validated @RequestBody ReqCaptcha reqCaptcha) throws BusinessException
    {
        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
        //
        String scene = reqCaptcha.getScene();
        String captchaText = producer.createText();
        //
        String ip = NetworkUtils.getIpAddr(request);
        StringBuffer key = new StringBuffer();
        // SMS
        if (MessageConst.SMS_VALID_LOGIN.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.SMS_VALID_FORGETPASS.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.SMS_VALID_MODIFYPASS.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_MODIFYPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.SMS_VALID_REGISTER.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.SMS_VALID_BINDMOBILE.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_BINDMOBILE).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.SMS_VALID_OTHER.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        // Email
        else if (MessageConst.EMAIL_VALID_LOGIN.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_FORGETPASS.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_MODIFYPASS.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_MODIFYPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_REGISTER.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_BINDEMAIL.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_BINDEMAIL).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_LOGOFF.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_LOGOFF).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else if (MessageConst.EMAIL_VALID_OTHER.equals(scene))
        {
            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
                    CacheConst.ONE_MINUTE_CACHE_TIME);
        }
        else
        {
            log.error("scene错误:{}", reqCaptcha.getScene());
            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
        }
        jsonMessage.setData(captchaText);
        //
        return jsonMessage;
    }
}
