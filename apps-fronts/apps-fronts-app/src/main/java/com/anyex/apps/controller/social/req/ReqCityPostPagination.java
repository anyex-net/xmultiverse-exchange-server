package com.anyex.apps.controller.social.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "附近帖子")
public class ReqCityPostPagination extends Pagination {

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "搜索词")
    private String keywords;

}
