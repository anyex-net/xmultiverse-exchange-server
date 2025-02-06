package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品品类请求对象")
public class ReqGoodsSpecGroupPagination extends Pagination
{
    @ApiModelProperty(value = "品类ID")
    private Long id;

    /**品类名称*/
    @ApiModelProperty(value = "品类名称")
    private String name;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private String remark;
}