//package com.anyex.apps.controller.common;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.anyex.apps.bean.GenericController;
//import com.anyex.apps.common.consts.MessageConst;
//import com.anyex.apps.controller.common.req.ReqKaptcha;
//import com.anyex.apps.consts.CacheConst;
//import com.anyex.apps.consts.GlobalConst;
//import com.anyex.apps.enums.CommonEnums;
//import com.anyex.apps.exception.BusinessException;
//import com.anyex.apps.model.JsonMessage;
//import com.anyex.apps.utils.NetworkUtils;
//import com.anyex.apps.utils.RedisUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.google.code.kaptcha.Producer;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
///**
// * 随机验证码生成器
// * <p>File: KaptchaController.java </p>
// * <p>Title: KaptchaController </p>
// * <p>Description: KaptchaController </p>
// * <p>Copyright: Copyright (c) 2019-05-21</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Slf4j
//@RestController
//@RequestMapping(GlobalConst.COMMON)
//@Api(tags = "验证码生成器")
//public class KaptchaController extends GenericController
//{
//    @Autowired(required = false)
//    private Producer producer;
//
//    /**
//     * 生成谷歌随机验证码文本
//     * @param request
//     * @param reqKaptcha
//     * @return
//     * @throws BusinessException
//     */
//    @PostMapping(value = "/kaptcha")
//    @ApiOperation(value = "生成随机验证码", httpMethod = "POST")
//    public JsonMessage createKaptcha(HttpServletRequest request, @RequestBody ReqKaptcha reqKaptcha) throws BusinessException
//    {
//        JsonMessage jsonMessage = getJsonMessage(CommonEnums.SUCCESS);
//        //
//        String scene = reqKaptcha.getScene();
//        String captchaText = producer.createText();
//        //
//        String ip = NetworkUtils.getIpAddr(request);
//        StringBuffer key = new StringBuffer();
////        // SMS
////        if (MessageConst.SMS_VALID_LOGIN.equals(scene))
////        {
////            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
////                    CacheConst.ONE_MINUTE_CACHE_TIME);
////        }
////        else if (MessageConst.SMS_VALID_FORGETPASS.equals(scene))
////        {
////            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
////                    CacheConst.ONE_MINUTE_CACHE_TIME);
////        }
////        else if (MessageConst.SMS_VALID_MODIFYPASS.equals(scene))
////        {
////            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_MODIFYPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
////                    CacheConst.ONE_MINUTE_CACHE_TIME);
////        }
////        else if (MessageConst.SMS_VALID_REGISTER.equals(scene))
////        {
////            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
////                    CacheConst.ONE_MINUTE_CACHE_TIME);
////        }
////        else if (MessageConst.SMS_VALID_OTHER.equals(scene))
////        {
////            RedisUtils.putObject(key.append(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
////                    CacheConst.ONE_MINUTE_CACHE_TIME);
////        }
//        // Email
//        if (MessageConst.EMAIL_VALID_LOGIN.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_LOGIN).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else if (MessageConst.EMAIL_VALID_FORGETPASS.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_FORGETPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else if (MessageConst.EMAIL_VALID_MODIFYPASS.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_MODIFYPASS).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else if (MessageConst.EMAIL_VALID_REGISTER.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_REGISTER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else if (MessageConst.EMAIL_VALID_LOGOFF.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_LOGOFF).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else if (MessageConst.EMAIL_VALID_OTHER.equals(scene))
//        {
//            RedisUtils.putObject(key.append(MessageConst.EMAIL_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip).toString(), captchaText,
//                    CacheConst.ONE_MINUTE_CACHE_TIME);
//        }
//        else
//        {
//            log.error("scene错误:{}", reqKaptcha.getScene());
//            throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
//        }
//        jsonMessage.setData(captchaText);
//        //
//        return jsonMessage;
//    }
//}
