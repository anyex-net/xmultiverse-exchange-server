//package com.anyex.apps.controller.auth.req;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 短信注册账户请求对象
// * <p>File：ReqSmsRegister.java</p>
// * <p>Title: ReqSmsRegister</p>
// * <p>Description: ReqSmsRegister</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class ReqSmsRegister implements Serializable
//{
//    /**
//     * 国家地区
//     */
//    @NotNull(message = "国家地区不可为空")
//    @ApiModelProperty(value = "国家地区(默认86)", required = true)
//    private String            country;
//
//    /**
//     * 手机号码
//     */
//    @NotNull(message = "手机号码不可为空")
//    @ApiModelProperty(value = "手机号码", required = true)
//    private String            mobile;
//
//    /**
//     * 短信验证码
//     */
//    @NotNull(message = "短信验证码不可为空")
//    @ApiModelProperty(value = "短信验证码", required = true)
//    private String            smsCode;
//
////    /**
////     * 登录密码
////     */
////    @NotNull(message = "登录密码不可为空")
////    @ApiModelProperty(value = "登录密码", required = true)
////    private String            loginPwd;
//}
