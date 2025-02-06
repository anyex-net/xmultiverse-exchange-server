package com.anyex.apps.controller.auth.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 账户注册请求对象
 * <p>File：ReqRegisterAccount.java</p>
 * <p>Title: ReqRegisterAccount</p>
 * <p>Description: ReqRegisterAccount</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAccountRegister implements Serializable
{
    /**电子邮箱*/
    @NotEmpty(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private java.lang.String email;

    /**邮箱验证码*/
    @NotEmpty(message = "邮箱验证码不可为空")
    @ApiModelProperty(value = "邮箱验证码", required = true)
    private java.lang.String emailCode;

    /**登录密码*/
    @NotEmpty(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private java.lang.String loginPwd;

    /**推荐码*/
    @ApiModelProperty(value = "推荐码")
    private java.lang.String referralCode;

    /**设备编号*/
    //@NotEmpty(message = "设备编号不可为空")
    //@ApiModelProperty(value = "设备编号", required = true)
    @ApiModelProperty(value = "设备编号")
    private java.lang.String deviceId;

    /**最新位置经度*/
    @ApiModelProperty(value = "最新位置经度")
    private java.lang.String lng;

    /**最新位置维度*/
    @ApiModelProperty(value = "最新位置维度")
    private java.lang.String lat;

    /**来源*/
    @NotEmpty(message = "来源不可为空")
    @ApiModelProperty(value = "来源", required = true)
    private java.lang.String source;
}
