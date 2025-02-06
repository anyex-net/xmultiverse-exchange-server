package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterUserReq {

    @ApiModelProperty(value = "邀请码")
    private String invitationCode;

    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String verifyCode;

    @ApiModelProperty(value = "IP")
    private String ip;

    @NotEmpty(message = "设备I不能为空")
    @ApiModelProperty(value = "设备ID", required = true)
    private String deviceID;

    private boolean autoLogin;

    @NotNull(message = "平台ID不能为空")
    @ApiModelProperty(value = "平台ID, 1：IOS，2：Android，3：Windows，4：OSX，5：Web，6：MiniWeb，7：Linux，8：Android Pad，9：IPad，10：admin", required = true)
    private Integer platform;

    @NotNull(message = "用户对象不能为空")
    @ApiModelProperty(value = "用户对象", required = true)
    private RegisterUserInfo user;
}
