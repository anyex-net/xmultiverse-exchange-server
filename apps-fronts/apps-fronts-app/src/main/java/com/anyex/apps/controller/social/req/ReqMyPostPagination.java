package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "我的帖子查询")
public class ReqMyPostPagination extends Pagination {

    @ApiModelProperty(value = "搜索词")
    private String keywords;

}
