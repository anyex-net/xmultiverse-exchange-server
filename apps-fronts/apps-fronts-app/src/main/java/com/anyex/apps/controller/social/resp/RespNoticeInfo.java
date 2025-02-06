package com.anyex.apps.controller.social.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通知信息(未读汇总)")
public class RespNoticeInfo {

    @ApiModelProperty(value = "所有未读")
    private Integer countAll;

    @ApiModelProperty(value = "未读关注")
    private Integer countFans;

    @ApiModelProperty(value = "未读点赞")
    private Integer countLikes;

    @ApiModelProperty(value = "未读评论")
    private Integer countComments;
}
