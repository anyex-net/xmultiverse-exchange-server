package com.anyex.apps.openim.chat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPublicInfo {

    @ApiModelProperty(value = "用户 ID")
    private String userID;
    @ApiModelProperty(value = "账户")
    private String account;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "手机号区域")
    private String areaCode;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "头像")
    private String faceURL;
    @ApiModelProperty(value = "级别 20普通")
    private Integer level;
    @ApiModelProperty(value = "性别")
    private Integer gender;


}
