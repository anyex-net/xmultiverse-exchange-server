package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VerifyCodeReq {

    @NotNull(message = "验证码类型不能为空")
    @ApiModelProperty(value = "验证码类型 1注册 2找回密码 3登录", required = true)
    private Integer usedFor;

    // @NotEmpty(message = "手机号区号不能为空")
    @ApiModelProperty(value = "手机号区号 （邮箱 或 手机区号+手机号 二选一）", required = true)
    private String areaCode;

    // @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号（邮箱 或 手机区号+手机号 二选一）", required = true)
    private String phoneNumber;

    // @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "邮箱（邮箱 或 手机区号+手机号 二选一）", required = true)
    private String email;

    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String verifyCode;
}
