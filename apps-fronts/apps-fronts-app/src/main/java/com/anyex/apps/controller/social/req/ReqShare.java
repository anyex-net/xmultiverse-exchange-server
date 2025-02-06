package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "分享")
public class ReqShare {

    @NotNull(message = "帖子id不能为空")
    @ApiModelProperty(value = "帖子id",required = true)
    private Long postId;

    //@NotEmpty(message = "接收人userIds不能为空")
    @ApiModelProperty(value = "接收人userIds")
    private String[] userIds;
}
