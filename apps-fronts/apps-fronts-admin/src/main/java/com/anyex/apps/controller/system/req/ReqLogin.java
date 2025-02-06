package com.anyex.apps.controller.system.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录 请求对象
 * <p>File：ReqLogin.java</p>
 * <p>Title: ReqLogin</p>
 * <p>Description: ReqLogin</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqLogin implements Serializable
{
    /**
     * 用户名
     */
    @NotNull(message = "用户名不可为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String            username;

    /**
     * 密码
     */
    @NotNull(message = "密码不可为空")
    @ApiModelProperty(value = "密码", required = true)
    private String            password;

    /**
     * 随机验证码
     */
    @NotNull(message = "随机验证码不可为空")
    @ApiModelProperty(value = "随机验证码", required = true)
    private String            captcha;
}

