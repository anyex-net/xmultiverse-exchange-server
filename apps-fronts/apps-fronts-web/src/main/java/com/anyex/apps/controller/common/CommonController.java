package com.anyex.apps.controller.common;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.captcha20230305.Client;
import com.aliyun.captcha20230305.models.VerifyIntelligentCaptchaRequest;
import com.aliyun.captcha20230305.models.VerifyIntelligentCaptchaResponse;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysRegion;
import com.anyex.apps.common.service.SysDictionaryService;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.common.service.SysRegionService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqAliyunCaptchaV2;
import com.anyex.apps.controller.common.req.ReqGoogleRecaptchaV3;
import com.anyex.apps.controller.common.resp.RespUserAuth;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * CommonController
 * <p>File：CommonController.java</p>
 * <p>Title: CommonController</p>
 * <p>Description: CommonController</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "公共接口")
public class CommonController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private SysRegionService regionService;

    @Autowired(required = false)
    private SysDictionaryService dictionaryService;

    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

//    @PostMapping("/accountExist")
//    @ApiOperation(value = "获取账户存在否", httpMethod = "POST")
//    public JsonMessage accountExist(@RequestBody ReqAccount reqAccount) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        json.setMessage("账户不存在可以正常注册");
//        //
//        if (beanValidator(json, reqAccount))
//        {
//            Account account = accountService.findByMobile(reqAccount.getMobile());
//            if (null != account)
//            {
//                throw new BusinessException(CommonEnums.ERROR_REGISTER_EXIST);
//            }
//        }
//        //
//        return json;
//    }

////    @PostMapping("/sendsms")
////    @ApiOperation(value = "发送手机短信码", httpMethod = "POST")
//    public JsonMessage sendSMS(HttpServletRequest request, @RequestBody ReqSendSms reqSendSms) throws BusinessException
//    {
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        //
////        String ip = NetworkUtils.getIpAddr(request);
////        StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
////        String captchaText = RedisUtils.get(key.toString());
////        if (captchaText == null || !captchaText.equalsIgnoreCase(reqSendSms.getKaptcha()))
////        {
////            throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
////        }
////        Account account = accountService.findByMobile(reqSendSms.getMobile());
////        if (null == account)
////        {
////            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
////        }
//        StringBuffer mobileNum = new StringBuffer(GlobalConst.DEFAULT_COUNTRY).append(reqSendSms.getMobile());
//        msgRecordService.sendSms(mobileNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_OTHER);
//        //
//        return json;
//    }

    @GetMapping(value = "/dict/code")
    @ApiOperation(value = "根据字典编码取字典项", httpMethod = "GET")
    public JsonMessage findDictionaryByCode(String dictCode) throws BusinessException
    {
        if (StringUtils.isBlank(dictCode))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.findByCode(dictCode, GlobalConst.DEFAULT_LANG));
    }

//    @GetMapping(value = "/region")
//    @ApiOperation(value = "获取国家地区", httpMethod = "GET")
    public JsonMessage getRegion(HttpServletRequest request, String regionCode) throws BusinessException
    {
        if (StringUtils.isBlank(regionCode))
        {// code 为空是取所有区域
            return getJsonMessage(CommonEnums.SUCCESS, regionService.selectAll());
        }
        //
        SysRegion region = new SysRegion();
        region.setSCode(regionCode);
        return getJsonMessage(CommonEnums.SUCCESS, regionService.findList(region));
    }

    @GetMapping(value = "/server/timestamp")
    @ApiOperation(value = "获取服务器时间戳", httpMethod = "GET")
    public JsonMessage getServerTime() throws BusinessException
    {
        Long currentTime = System.currentTimeMillis();
        return getJsonMessage(CommonEnums.SUCCESS, currentTime.toString());
    }

    @GetMapping(value = "/user/auth")
    @ApiOperation(value = "userAuth", httpMethod = "GET")
    public JsonMessage userAuth() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        log.info("userAuth");
        //
        RespUserAuth respUserAuth = new RespUserAuth();
        respUserAuth.setUser_id(principal.getId());
        log.info("respUserAuth:{}", respUserAuth);
        //
        json.setCode(0);
        json.setData(respUserAuth);
        return json;
    }

    @GetMapping(value = "/user/sign")
    @ApiOperation(value = "userSign", httpMethod = "GET")
    public JsonMessage userSign(@RequestParam(value = "access_id", required = true) String accessId,
                                @RequestParam(value = "tonce", required = true) Long tonce) throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
        // 在这里处理接收到的参数
        log.info("access_id:{}", accessId);
        log.info("tonce:{}", tonce);
        //
        RespUserAuth respUserAuth = new RespUserAuth();
        respUserAuth.setUser_id(Long.valueOf(accessId));
        log.info("respUserAuth:{}", respUserAuth);
        //
        json.setCode(0);
        json.setData(respUserAuth);
        return json;
    }

    // google recaptcha V3 您将获得一个site key和一个secret key。
    // https://developers.google.com/recaptcha/docs/verify?hl=zh-cn
    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6LcHUs0qAAAAANB2TBcbKpOKyEEzyaQKW-vt6CNN";
    @PostMapping("/googleRecaptchaV3Verify")
    @ApiOperation(value = "googleRecaptchaV3Verify", httpMethod = "POST")
    public JsonMessage googleRecaptchaV3Verify(HttpServletRequest request, @RequestBody ReqGoogleRecaptchaV3 reqGoogleRecaptcha) throws Exception
    {
        log.info("reqGoogleRecaptcha:{}", reqGoogleRecaptcha);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(RECAPTCHA_URL);
        String json = "{\"secret\":\"" + SECRET_KEY + "\",\"response\":\"" + reqGoogleRecaptcha.getRecaptchaToken() + "\"}";
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = httpClient.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        log.info("verifyRecaptcha result:{}", result.toString());

        httpClient.close();

        // 解析响应
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(result.toString(), Map.class);

        if ((boolean) map.get("success")) {
            //return "Verification successful!";
            return getJsonMessage(CommonEnums.SUCCESS);
        } else {
            // return "Verification failed.";
            return getJsonMessage(CommonEnums.FAIL);
        }
    }

    @PostMapping("/aliyunCaptchaV2Verify")
    @ApiOperation(value = "aliyunCaptchaV2Verify", httpMethod = "POST")
    public JsonMessage aliyunCaptchaV2Verify(HttpServletRequest request, @Validated @RequestBody ReqAliyunCaptchaV2 reqAliyunCaptchaV2) throws Exception
    {
        // ====================== 1. 初始化配置 ======================
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        // 设置您的AccessKey ID 和 AccessKey Secret。
        config.accessKeyId = "LTAI5tBAKUNBfCswtMQgGPZT";
        config.accessKeySecret = "AEcdi4R8hZUHZxxnDSsMia4xclJOFz";
        //设置请求地址 国内调用地址 captcha.cn-shanghai.aliyuncs.com   新加坡调用地址 captcha.ap-southeast-1.aliyuncs.com
        config.endpoint = "captcha.cn-shanghai.aliyuncs.com";
        // 设置连接超时为5000毫秒
        config.connectTimeout = 5000;
        // 设置读超时为5000毫秒
        config.readTimeout = 5000;
        // ====================== 2. 初始化客户端（实际生产代码中建议复用client） ======================
        Client client = new Client(config);
        // 创建APi请求
        VerifyIntelligentCaptchaRequest verifyIntelligentCaptchaRequest = new VerifyIntelligentCaptchaRequest();
        // 本次验证的场景ID，建议传入，防止前端被篡改场景
        verifyIntelligentCaptchaRequest.sceneId = "1nhxa79i"; // 本次验证的场景ID
        // 前端传来的验证参数 CaptchaVerifyParam
        verifyIntelligentCaptchaRequest.captchaVerifyParam = reqAliyunCaptchaV2.getCaptchaVerifyParam(); // 前端传来的验证参数 CaptchaVerifyParam
        // ====================== 3. 发起请求） ======================
        try {
            System.out.println("client.verifyIntelligentCaptcha request：" + request);
            System.out.println("client.verifyIntelligentCaptcha start");
            VerifyIntelligentCaptchaResponse resp = client.verifyIntelligentCaptcha(verifyIntelligentCaptchaRequest);
            // 建议使用您系统中的日志组件，打印返回
            // 获取验证码验证结果（请注意判空），将结果返回给前端。出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            Boolean captchaVerifyResult = resp.body.result.verifyResult;
            // 原因code
            String captchaVerifyCode = resp.body.result.verifyCode;
            System.out.println("captchaVerifyResult:" + captchaVerifyResult);
            System.out.println("captchaVerifyCode:" + captchaVerifyCode);
            //
            return getJsonMessage(CommonEnums.SUCCESS, resp.body.result);
        } catch (BusinessException e) {
            e.printStackTrace();
            // 建议使用您系统中的日志组件，打印异常
            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            return getJsonMessage(CommonEnums.FAIL, e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            // 建议使用您系统中的日志组件，打印异常
            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
            return getJsonMessage(CommonEnums.FAIL, e.getLocalizedMessage());
        }
    }

    // 错误码列表
//    // https://help.aliyun.com/zh/captcha/captcha2-0/user-guide/server-integration?spm=5176.b82045537.console-base_help.dexternal.7d6bIwSKIwSKaI
//    public static void main(String[] args_) throws Exception {
//        java.util.List<String> args = java.util.Arrays.asList(args_);
//        // ====================== 1. 初始化配置 ======================
//        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
//        // 设置您的AccessKey ID 和 AccessKey Secret。
//        // getEnvProperty只是个示例方法，需要您自己实现AccessKey ID 和 AccessKey Secret安全的获取方式。
//        // static final String accessKeyId = "LTAI5tBAKUNBfCswtMQgGPZT";           // 改这里
//        // static final String accessKeySecret = "AEcdi4R8hZUHZxxnDSsMia4xclJOFz"; // 改这里
//        config.accessKeyId = "LTAI5tBAKUNBfCswtMQgGPZT";
//        config.accessKeySecret = "AEcdi4R8hZUHZxxnDSsMia4xclJOFz";
//        //设置请求地址 国内调用地址 captcha.cn-shanghai.aliyuncs.com   新加坡调用地址 captcha.ap-southeast-1.aliyuncs.com
//        config.endpoint = "captcha.cn-shanghai.aliyuncs.com";
//        // 设置连接超时为5000毫秒
//        config.connectTimeout = 5000;
//        // 设置读超时为5000毫秒
//        config.readTimeout = 5000;
//        // ====================== 2. 初始化客户端（实际生产代码中建议复用client） ======================
//        Client client = new Client(config);
//        // 创建APi请求
//        VerifyIntelligentCaptchaRequest request = new VerifyIntelligentCaptchaRequest();
//        // 本次验证的场景ID，建议传入，防止前端被篡改场景
//        request.sceneId = "1nhxa79i"; // 本次验证的场景ID
//        // 前端传来的验证参数 CaptchaVerifyParam
//        request.captchaVerifyParam = "CaptchaVerifyParam"; // 前端传来的验证参数 CaptchaVerifyParam
//        // ====================== 3. 发起请求） ======================
//        try {
//            System.out.println("client.verifyIntelligentCaptcha request：" + request);
//            System.out.println("client.verifyIntelligentCaptcha start");
//            VerifyIntelligentCaptchaResponse resp = client.verifyIntelligentCaptcha(request);
//            // 建议使用您系统中的日志组件，打印返回
//            // 获取验证码验证结果（请注意判空），将结果返回给前端。出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
//            Boolean captchaVerifyResult = resp.body.result.verifyResult;
//            // 原因code
//            String captchaVerifyCode = resp.body.result.verifyCode;
//            System.out.println("captchaVerifyResult:" + captchaVerifyResult);
//            System.out.println("captchaVerifyCode:" + captchaVerifyCode);
//        } catch (BusinessException error) {
//            error.printStackTrace();
//            // 建议使用您系统中的日志组件，打印异常
//            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
//            Boolean captchaVerifyResult = true;
//        } catch (Exception _error) {
//            _error.printStackTrace();
//            // BusinessException error = new BusinessException(_error.getMessage(), _error);
//            // 建议使用您系统中的日志组件，打印异常
//            // 出现异常建议认为验证通过，优先保证业务可用，然后尽快排查异常原因。
//            Boolean captchaVerifyResult = true;
//        }
//    }
}
