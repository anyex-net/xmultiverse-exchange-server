package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品品牌请求对象")
public class ReqGoodsBrandPagination extends Pagination
{

    @ApiModelProperty(value = "品牌ID")
    private Long id;

    /**品牌名称*/
    @ApiModelProperty(value = "品牌名称")
    private String name;

    /**品牌Logo图片Url*/
    @ApiModelProperty(value = "品牌Logo图片Url")
    private String logoImageUrl;

    /**品牌首字母*/
    @ApiModelProperty(value = "品牌首字母")
    private String letter;

    /**状态(是否启用)*/
    @ApiModelProperty(value = "状态(是否启用)")
    private Boolean status;
}