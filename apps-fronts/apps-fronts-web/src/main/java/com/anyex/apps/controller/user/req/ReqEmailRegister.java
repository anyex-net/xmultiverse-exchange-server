package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 邮箱注册用户请求对象
 * <p>File：ReqEmailRegister.java</p>
 * <p>Title: ReqEmailRegister</p>
 * <p>Description: ReqEmailRegister</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqEmailRegister implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotEmpty(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String            loginPwd;

    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码")
    private String            inviteCode;

    /**
     * 来源
     */
    @NotEmpty(message = "来源不可为空")
    @ApiModelProperty(value = "来源(web、app)", required = true)
    private String            source;

    /**
     * 邮箱验证码
     */
    @NotEmpty(message = "邮箱验证码不可为空")
    @ApiModelProperty(value = "邮箱验证码", required = true)
    private String            emailCode;
}
