package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户重置密码参数Submit请求对象
 *
 * ReqUserResetPassSubmit
 * <p>File: ReqUserResetPassSubmit.java </p>
 * <p>Title: ReqUserResetPassSubmit </p>
 * <p>Description: ReqUserResetPassSubmit </p>
 * <p>Copyright: Copyright (c) 2018/11/9</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserResetPassSubmit implements Serializable
{
    @NotNull(message = "用户Id不可为空")
    @ApiModelProperty(value = "用户Id", required = true)
    private Long        userId;

    @NotEmpty(message = "随机码不可为空")
    @ApiModelProperty(value = "随机码", required = true)
    private String      randomCode;

    @NotEmpty(message = "密码不可为空")
    @ApiModelProperty(value = "密码", required = true)
    private String      password;
}
