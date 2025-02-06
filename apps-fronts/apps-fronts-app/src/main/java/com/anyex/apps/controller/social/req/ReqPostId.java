package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "帖子id")
public class ReqPostId {

    @NotEmpty(message = "帖子id不能为空")
    @ApiModelProperty(value = "帖子id",required = true)
    private String postId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
