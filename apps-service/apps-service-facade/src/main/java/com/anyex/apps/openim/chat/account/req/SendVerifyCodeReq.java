package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SendVerifyCodeReq {

    @NotNull(message = "验证码类型不能为空")
    @ApiModelProperty(value = "验证码类型 1注册 2找回密码 3登录", required = true)
    private Integer usedFor;

   /* @NotEmpty(message = "设备I不能为空")
    @ApiModelProperty(value = "设备ID", required = true)
    private String deviceID;

    @NotNull(message = "平台ID不能为空")
    @ApiModelProperty(value = "平台ID, 1：IOS，2：Android，3：Windows，4：OSX，5：Web，6：MiniWeb，7：Linux，8：Android Pad，9：IPad，10：admin", required = true)
    private Integer platform;*/

    @ApiModelProperty(value = "手机号区号（邮箱 或 手机区号+手机号 二选一）", required = true)
    private String areaCode;

    @ApiModelProperty(value = "手机号（邮箱 或 手机区号+手机号 二选一）", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "邮箱（邮箱 或 手机区号+手机号 二选一）", required = true)
    private String email;
}
