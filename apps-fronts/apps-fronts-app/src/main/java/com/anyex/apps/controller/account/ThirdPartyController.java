package com.anyex.apps.controller.account;//package com.anyex.am.controller.account;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.anyex.am.account.entity.Account;
//import com.anyex.am.account.entity.Thirdparty;
//import com.anyex.am.account.model.BindAccount;
//import com.anyex.am.account.model.ThirdLogin;
//import com.anyex.am.account.service.AccountService;
//import com.anyex.am.account.service.ThirdpartyService;
//import com.anyex.am.bean.GenericController;
//import com.anyex.am.common.consts.MessageConst;
//import com.anyex.am.common.service.MsgRecordNoSqlService;
//import com.anyex.am.consts.GlobalConst;
//import com.anyex.am.enums.CommonEnums;
//import com.anyex.am.exception.BusinessException;
//import com.anyex.am.model.JsonMessage;
//import com.anyex.am.shiro.utils.JwtUtil;
//import com.anyex.am.utils.NetworkUtils;
//import com.anyex.am.utils.RedisUtils;
//import com.anyex.am.utils.SerialnoUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
///**
// * ThirdPartyController
// * <p>File：ThirdPartyController.java</p>
// * <p>Title: ThirdPartyController</p>
// * <p>Description: ThirdPartyController</p>
// * <p>Copyright: Copyright (c) 2019/11/6</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@RestController
//@RequestMapping("/third")
//@Api(description = "第三方认证")
//public class ThirdPartyController extends GenericController
//{
//    @Autowired(required = false)
//    private AccountService accountService;
//
//    @Autowired(required = false)
//    private ThirdpartyService thirdpartyService;
//
//    @Autowired(required = false)
//    private MsgRecordNoSqlService msgRecordNoSqlService;
//
//    @PostMapping("/login")
//    @ApiOperation(value = "登录", httpMethod = "POST")
//    public JsonMessage login(HttpServletRequest request, ThirdLogin params) throws BusinessException
//    {
//        JsonMessage jsonMessage = this.getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(jsonMessage, params))
//        {
//            Thirdparty thirdparty = thirdpartyService.findByOpenId(params.getUid());
//            if (null == thirdparty)
//            {// 说明用户未登录过此系统
//                thirdparty = new Thirdparty();
//                thirdparty.setId(SerialnoUtils.buildPrimaryKey());
//                thirdparty.setType(params.getType());
//                thirdparty.setOpenId(params.getUid());
//                thirdparty.setNickName(params.getName());
//                thirdparty.setAccountLogo(params.getIconUrl());
//                thirdparty.setCreateDate(System.currentTimeMillis());
//                thirdpartyService.insert(thirdparty);
//            }
//            if (null == thirdparty.getAccountId())
//            {// 表明此UID未绑定过平台帐户
//                jsonMessage = new JsonMessage(CommonEnums.BIND_YOUR_PHONE);
//                jsonMessage.setObject(thirdparty.getId());
//                return jsonMessage;
//            }
//            // 记录登录的设备
////            AccountDevice device = new AccountDevice();
////            device.setAccountId(thirdparty.getAccountId());
////            device.setAppVersion(params.getAppVersion());
////            device.setDeviceName(params.getDeviceName());
////            device.setDeviceNum(params.getDeviceNum());
////            device.setDeviceType(params.getType());
////            device.setIpAddress(NetworkUtils.getIpAddr(request));
////            device.setCreateDate(System.currentTimeMillis());
////            device.setLatestLoginDate(System.currentTimeMillis());
////            accountDeviceService.addAccountDevice(device);
//            Account account = accountService.selectByPrimaryKey(thirdparty.getAccountId());
//            jsonMessage.setObject(JwtUtil.sign(account.getAccountName(), account.getLoginPwd()));
//        }
//        return jsonMessage;
//    }
//
//    @PostMapping("/sms")
//    @ApiOperation(value = "发送手机短信码", httpMethod = "POST")
//    public JsonMessage sendSMS(HttpServletRequest request, @ModelAttribute BindAccount params) throws BusinessException
//    {
//        params.setCode(GlobalConst.DEFAULT_VALUE);
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, params))
//        {
//            Integer ip = NetworkUtils.getIpAddrs(request);
//            StringBuffer key = new StringBuffer(MessageConst.SMS_VALID_OTHER).append(GlobalConst.SEPARATOR).append(ip);
//            String captchaText = RedisUtils.get(key.toString());
//            if (captchaText == null || !captchaText.equalsIgnoreCase(params.getKaptcha()))
//            {// 验证码检验
//                throw new BusinessException(CommonEnums.ERROR_VALID_CAPTCHA);
//            }
//            Thirdparty thirdparty = thirdpartyService.selectByPrimaryKey(params.getObjectId());
//            if (null == thirdparty) throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
//            StringBuffer phoneNum = new StringBuffer(params.getCountry()).append(params.getPhone());
//            msgRecordNoSqlService.sendSms(phoneNum.toString(), GlobalConst.DEFAULT_LANG, MessageConst.SMS_VALID_REGISTER);
//        }
//        return json;
//    }
//
//    @PostMapping("/bind")
//    @ApiOperation(value = "发送手机注册短信码", httpMethod = "POST")
//    public JsonMessage bind(HttpServletRequest request, @ModelAttribute BindAccount params) throws BusinessException
//    {
//        params.setKaptcha(GlobalConst.DEFAULT_VALUE);
//        JsonMessage json = this.getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, params))
//        {
//            Thirdparty thirdparty = thirdpartyService.selectByPrimaryKey(params.getObjectId());
//            if (null == thirdparty) throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
//            StringBuffer phoneNum = new StringBuffer(params.getCountry()).append(params.getPhone());
//            if (!msgRecordNoSqlService.validSMSCode(phoneNum.toString(), params.getCode(), MessageConst.SMS_VALID_REGISTER))
//            {// 验证短信码
//                return getJsonMessage(CommonEnums.ERROR_PHONE_VALID_FAILED);
//            }
//            Account account = accountService.findByPhone(params.getPhone());
//            if (null != account)
//            {// 绑定用户关系
//                thirdparty.setAccountId(account.getId());
//                thirdpartyService.save(thirdparty);
//            }
//            else
//            {
//                String passWord = SerialnoUtils.randomNum(8);
//                account = new Account();
//                account.setMobNo(params.getPhone());
//                account.setLoginPwd(passWord);
//                account.setLang(GlobalConst.DEFAULT_LANG);
//                account.setCountry(GlobalConst.DEFAULT_COUNTRY);
////                accountService.register(account);
//            }
//            json.setObject(JwtUtil.sign(account.getAccountName(), account.getLoginPwd()));
//        }
//        return json;
//    }
//}
