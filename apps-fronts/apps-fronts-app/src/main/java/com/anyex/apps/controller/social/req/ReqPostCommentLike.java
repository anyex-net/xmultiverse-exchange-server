package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "帖子评论点赞")
public class ReqPostCommentLike {

    @NotNull(message = "帖子Id不可为空")
    @ApiModelProperty(value = "帖子Id", required = true)
    private java.lang.Long postId;

    /**帖子评论Id*/
    @ApiModelProperty(value = "帖子评论Id")
    private java.lang.Long postCommentId;


}
