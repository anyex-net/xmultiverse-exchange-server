package com.anyex.apps.openim.chat.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author  anyex
 */
@Data
public class ChangePasswordReq {
    @NotEmpty(message = "userId不能为空")
    @ApiModelProperty(value = "IM userId", required = true)
    private String userID;

    @NotEmpty(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码", required = true)
    private String currentPassword;

    @NotEmpty(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
