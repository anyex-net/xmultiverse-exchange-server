package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "用户id")
public class ReqPersonPostPagination extends Pagination {

    @NotEmpty(message = "userId不能为空")
    @ApiModelProperty(value = "IM userId",required = true)
    private String userId;

    @ApiModelProperty(value = "搜索词")
    private String keywords;
}
