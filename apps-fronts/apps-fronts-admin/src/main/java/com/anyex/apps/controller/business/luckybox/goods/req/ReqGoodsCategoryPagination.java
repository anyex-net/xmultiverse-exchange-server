package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品分类请求对象")
public class ReqGoodsCategoryPagination extends Pagination
{
    @ApiModelProperty(value = "分类D")
    private Long id;

    /**分类名称*/
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**上级分类ID*/
    @ApiModelProperty(value = "上级分类ID")
    private Long parentId;
}