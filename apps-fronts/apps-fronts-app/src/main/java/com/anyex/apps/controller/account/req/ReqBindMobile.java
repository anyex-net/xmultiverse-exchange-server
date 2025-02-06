//package com.anyex.apps.account.model.req;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 绑定手机请求对象
// * <p>File：ReqBindPhone.java</p>
// * <p>Title: ReqBindPhone</p>
// * <p>Description: ReqBindPhone</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class ReqBindPhone implements Serializable
//{
//    /**
//     * 国家地区
//     */
//    @NotNull(message = "国家地区不可为空")
//    @ApiModelProperty(value = "国家地区", required = true)
//    private String            country;
//
//    /**
//     * 手机号码
//     */
//    @NotNull(message = "手机号码不可为空")
//    @ApiModelProperty(value = "手机号码", required = true)
//    private String            mobNo;
//
//    /**
//     * 短信验证码
//     */
//    @NotNull(message = "短信验证码不可为空")
//    @ApiModelProperty(value = "短信验证码", required = true)
//    private String            smsCode;
//}
