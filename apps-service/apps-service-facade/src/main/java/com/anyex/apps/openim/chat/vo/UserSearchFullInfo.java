package com.anyex.apps.openim.chat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSearchFullInfo {
    @ApiModelProperty(value = "用户 ID")
    private String userID;
    @ApiModelProperty(value = "密码")
    private String password;
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
    @ApiModelProperty(value = "生日")
    private Long birth;
    @ApiModelProperty(value = "允许加友")
    private Integer allowAddFriend;
    @ApiModelProperty(value = "铃声提醒")
    private Integer allowBeep;
    @ApiModelProperty(value = "震动提醒")
    private Integer allowVibration;
    @ApiModelProperty(value = "是否接收默认人消息")
    private Integer globalRecvMsgOpt;
}
