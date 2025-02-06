package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "评论id")
public class ReqCommentPagination extends Pagination {

    @NotEmpty(message = "id不能为空")
    @ApiModelProperty(value = "帖子id",required = true)
    private String postId;

    @ApiModelProperty(value = "评论id")
    private Long commentId;
}
