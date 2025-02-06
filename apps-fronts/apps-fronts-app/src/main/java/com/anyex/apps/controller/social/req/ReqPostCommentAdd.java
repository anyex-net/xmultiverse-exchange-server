package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "评论")
public class ReqPostCommentAdd {

    /**帖子Id*/
    @NotNull(message = "帖子Id不可为空")
    @ApiModelProperty(value = "帖子Id", required = true)
    private java.lang.String postId;

    /**评论内容*/
    @NotEmpty(message = "评论内容不可为空")
    @ApiModelProperty(value = "评论内容", required = true)
    private java.lang.String commentContent;

    /**父级评论的ID(如果是根评论则为NULL)*/
    @ApiModelProperty(value = "父级评论的ID(如果是根评论则为NULL)")
    private java.lang.String replyTo;

    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}
