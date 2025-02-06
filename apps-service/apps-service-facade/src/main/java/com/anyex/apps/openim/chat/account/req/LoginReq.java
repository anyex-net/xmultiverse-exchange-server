package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  anyex
 */
@Data
public class LoginReq implements Serializable {
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

   // @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String verifyCode;

    // @NotEmpty(message = "设备ID不能为空")
    @ApiModelProperty(value = "设备ID")
    private String deviceID;

    @NotNull(message = "平台ID 不能为空")
    @ApiModelProperty(value = "平台ID, 1：IOS，2：Android，3：Windows，4：OSX，5：Web，6：MiniWeb，7：Linux，8：Android Pad，9：IPad，10：admin", required = true)
    private Integer platform;
}
