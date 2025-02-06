package com.anyex.apps.openim.chat.account.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginResp implements Serializable {

    @ApiModelProperty(value = "token")
    private String chatToken;

    @ApiModelProperty(value = "IM聊天token")
    private String imToken;

    @ApiModelProperty(value = "IM聊天userId")
    private String userID;

    /**昵称*/
    @NotEmpty(message = "昵称不可为空")
    @ApiModelProperty(value = "昵称", required = true)
    private String nickname;

    /**头像*/
    @ApiModelProperty(value = "头像")
    private String faceUrl;

    @ApiModelProperty(value = "邀请码")
    private String invitationCode;

}
