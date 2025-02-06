package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通用帖子")
public class ReqPublicPostPagination extends Pagination {

    @ApiModelProperty(value = "搜索词")
    private String keywords;
}
