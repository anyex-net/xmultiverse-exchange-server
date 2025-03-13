package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户忘记密码请求对象
 * <p>File：ReqUserForgetPass.java</p>
 * <p>Title: ReqUserForgetPass</p>
 * <p>Description: ReqUserForgetPass</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserForgetPass implements Serializable
{
    /**
     * 忘记密码找回类型
     */
    @NotEmpty(message = "忘记密码找回类型不可为空")
    @ApiModelProperty(value = "忘记密码找回类型(email、mobile)", required = true)
    private String            findType;

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
     * 随机验证码
     */
    @NotEmpty(message = "随机验证码不可为空")
    @ApiModelProperty(value = "随机验证码", required = true)
    private String            captcha;
}
