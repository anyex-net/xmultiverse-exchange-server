package com.anyex.apps.controller.auth.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 短信重置账户登录密码请求对象
 * <p>File：ReqSmsResetpass.java</p>
 * <p>Title: ReqSmsResetpass</p>
 * <p>Description: ReqSmsResetpass</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqSmsResetpass implements Serializable
{
    /**
     * 国家地区
     */
    @NotNull(message = "国家地区不可为空")
    @ApiModelProperty(value = "国家地区", required = true)
    private String            country;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码不可为空")
    @ApiModelProperty(value = "手机号码", required = true)
    private String            mobile;

    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String            loginPwd;
}
