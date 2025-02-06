package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResetPasswordReq {

    // @NotEmpty(message = "手机号区号不能为空")
    @ApiModelProperty(value = "手机号区号 手机号区号+手机号 / 邮箱 二选一", required = true)
    private String areaCode;

    // @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phoneNumber;

    // @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String verifyCode;
}
