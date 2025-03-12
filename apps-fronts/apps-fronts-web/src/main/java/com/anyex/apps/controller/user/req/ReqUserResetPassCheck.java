package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户重置密码参数Check请求对象
 *
 * ReqUserResetPassCheck
 * <p>File: ReqUserResetPassCheck.java </p>
 * <p>Title: ReqUserResetPassCheck </p>
 * <p>Description: ReqUserResetPassCheck </p>
 * <p>Copyright: Copyright (c) 2018/11/9</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqUserResetPassCheck implements Serializable
{
    @NotNull(message = "用户Id不可为空")
    @ApiModelProperty(value = "用户Id", required = true)
    private Long        userId;

    @ApiModelProperty(value = "邮件验证码")
    private String      emailCode;

    @ApiModelProperty(value = "短信验证码")
    private String      smsCode;

    @ApiModelProperty(value = "GA验证码")
    private String      gaCode;
}
