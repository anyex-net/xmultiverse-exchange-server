package com.anyex.apps.controller.common;

import javax.servlet.http.HttpServletRequest;

import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysRegion;
import com.anyex.apps.common.service.SysDictionaryService;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.common.service.SysRegionService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqGoogleRecaptchaV3;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
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

    // google recaptcha V3 您将获得一个site key和一个secret key。
    // https://developers.google.com/recaptcha/docs/verify?hl=zh-cn
    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6LcHUs0qAAAAANB2TBcbKpOKyEEzyaQKW-vt6CNN";
    @PostMapping("/googleRecaptchaV3Verify")
    @ApiOperation(value = "googleRecaptchaV3Verify", httpMethod = "POST")
    public JsonMessage googleRecaptchaV3Verify(HttpServletRequest request, @RequestBody ReqGoogleRecaptchaV3 reqGoogleRecaptcha) throws Exception {
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
}
