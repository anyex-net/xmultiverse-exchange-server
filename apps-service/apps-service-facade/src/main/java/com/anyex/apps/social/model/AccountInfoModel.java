package com.anyex.apps.social.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "关注/粉丝对象")
public class AccountInfoModel {

    @ApiModelProperty(value = "账户ID")
    private Long id;

    @ApiModelProperty(value = "IM userId")
    private String userId;

    @ApiModelProperty(value = "账户昵称")
    private String accountName;

    @ApiModelProperty(value = "头像URL")
    private String headUrl;

    @ApiModelProperty(value = "是否是好友 0否 1是")
    private java.lang.Integer isFriend;

    @ApiModelProperty(value = "是否已关注 0否 1是")
    private java.lang.Integer isFollow;

    @ApiModelProperty(value = "粉丝数量")
    private java.lang.Integer fansCnt;

    @ApiModelProperty(value = "是否已读 0否 1是")
    private java.lang.Integer isRead;
}
