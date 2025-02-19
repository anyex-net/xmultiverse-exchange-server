//package com.anyex.apps.controller.auth.req;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 邮箱注册账户请求对象
// * <p>File：ReqEmailRegister.java</p>
// * <p>Title: ReqEmailRegister</p>
// * <p>Description: ReqEmailRegister</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class ReqEmailRegister implements Serializable
//{
//    /**
//     * 国家地区
//     */
//    @NotNull(message = "国家地区不可为空")
//    @ApiModelProperty(value = "国家地区", required = true)
//    private String            country;
//
//    /**
//     * 电子邮箱
//     */
//    @NotNull(message = "电子邮箱不可为空")
//    @ApiModelProperty(value = "电子邮箱", required = true)
//    private String            email;
//
//    /**
//     * 邮箱验证码
//     */
//    @NotNull(message = "邮箱验证码不可为空")
//    @ApiModelProperty(value = "邮箱验证码", required = true)
//    private String            emailCode;
//
//    /**
//     * 登录密码
//     */
//    @NotNull(message = "登录密码不可为空")
//    @ApiModelProperty(value = "登录密码", required = true)
//    private String            loginPwd;
//}
