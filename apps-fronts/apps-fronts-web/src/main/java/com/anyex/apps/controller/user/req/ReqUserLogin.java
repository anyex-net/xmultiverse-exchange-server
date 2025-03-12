package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户登录请求对象
 * <p>File：ReqUserLogin.java</p>
 * <p>Title: ReqUserLogin</p>
 * <p>Description: ReqUserLogin</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserLogin implements Serializable
{
    /**
     * 登录类型
     */
    @NotEmpty(message = "登录类型不可为空")
    @ApiModelProperty(value = "登录类型(mobile | email)", required = true)
    private String            loginType;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String            email;

    /**
     * 国家地区
     */
    @ApiModelProperty(value = "国家地区")
    private String            country;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String            mobileNo;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不可为空")
    @ApiModelProperty(value = "密码", required = true)
    private char[]            password;

    /**
     * 随机验证码
     */
    @NotEmpty(message = "随机验证码不可为空")
    @ApiModelProperty(value = "随机验证码", required = true)
    private String            captcha;
}
