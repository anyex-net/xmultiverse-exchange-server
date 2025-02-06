//package com.anyex.apps.account.model.req;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import java.io.Serializable;
//
///**
// * 绑定邮箱请求对象
// * <p>File：ReqBindEmail.java</p>
// * <p>Title: ReqBindEmail</p>
// * <p>Description: ReqBindEmail</p>
// * <p>Copyright: Copyright (c) 2017/7/5</p>
// * <p>Company: AnyEx</p>
// *
// * @author Playguy
// * @version 1.0
// */
//@Data
//public class ReqBindEmail implements Serializable
//{
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
//     * 短信验证码
//     */
//    @NotNull(message = "短信验证码不可为空")
//    @ApiModelProperty(value = "短信验证码", required = true)
//    private String            smsCode;
//}
