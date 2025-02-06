package com.anyex.apps.controller.social.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "个人主页信息")
public class RespPersonInfo {

    @ApiModelProperty(value = "账户ID")
    private Long id;
    
    @ApiModelProperty(value = "IM userId")
    private String userId;
    
    @ApiModelProperty(value = "账户昵称")
    private String accountName;

    @ApiModelProperty(value = "头像URL")
    private String headUrl;

    @ApiModelProperty(value = "帖子数量")
    private Integer postNum;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansNum;

    @ApiModelProperty(value = "关注数量")
    private Integer followNum;

    @ApiModelProperty(value = "好友数量")
    private Integer friendNum;

    @ApiModelProperty(value = "是否好友")
    private Boolean isMyFriend;

    @ApiModelProperty(value = "是否粉丝")
    private Boolean isMyFans;

    @ApiModelProperty(value = "是否关注")
    private Boolean isMyFollow;
}
