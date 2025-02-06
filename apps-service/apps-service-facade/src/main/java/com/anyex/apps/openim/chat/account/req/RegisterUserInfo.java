package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author  anyex
 */
@Data
public class RegisterUserInfo {
    @ApiModelProperty(value = "用户 ID")
    private String userID;

    @ApiModelProperty(value = "账户")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    // @NotEmpty(message = "手机号区号不能为空")
    @ApiModelProperty(value = "手机号区号 手机号区号+手机号 / 邮箱 二选一", required = true)
    private String areaCode;

    // @NotEmpty(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号", required = true)
    private String phoneNumber;

    // @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String faceURL;
    @ApiModelProperty(value = "级别 20普通")
    private Integer level;
    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "生日(yyyy-MM-dd格式)", required = true)
    @NotNull(message = "birthday not empty")
    private Long birthday;
}
