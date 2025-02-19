package com.anyex.apps.controller.auth.reqnew;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 邮箱重置账户登录密码请求对象
 * <p>File：ReqEmailResetpass.java</p>
 * <p>Title: ReqEmailResetpass</p>
 * <p>Description: ReqEmailResetpass</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqEmailResetpassNew implements Serializable
{
    /**
     * 电子邮箱
     */
    @NotNull(message = "电子邮箱不可为空")
    @ApiModelProperty(value = "电子邮箱", required = true)
    private String            email;

    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    private String            loginPwd;
}
