package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "删除评论")
public class ReqPostCommentDelete {

    @NotNull(message = "帖子Id不可为空")
    @ApiModelProperty(value = "帖子Id", required = true)
    private Long postId;

    /**帖子评论Id*/
    @NotNull(message = "帖子评论Id不可为空")
    @ApiModelProperty(value = "帖子评论Id", required = true)
    private Long postCommentId;
}
