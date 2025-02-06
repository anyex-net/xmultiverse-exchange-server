package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "通用帖子")
public class ReqSearchPostPagination extends Pagination {
    @NotEmpty(message = "搜索词不可为空")
    @ApiModelProperty(value = "搜索词",required = true)
    private String keywords;
}
