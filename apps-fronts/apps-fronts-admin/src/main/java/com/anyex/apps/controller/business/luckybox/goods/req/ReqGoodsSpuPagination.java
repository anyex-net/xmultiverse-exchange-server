package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商品品类请求对象")
public class ReqGoodsSpuPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**标题*/
    @ApiModelProperty(value = "标题")
    private String title;

    /**副标题*/
    @ApiModelProperty(value = "副标题")
    private String subTitle;

    /**分类ID*/
    @ApiModelProperty(value = "分类ID")
    private Long categoryId;

    /**品牌ID*/
    @ApiModelProperty(value = "品牌ID")
    private Long brandId;

    /**品类ID*/
    @ApiModelProperty(value = "品类ID")
    private Long spgId;

    /**是否上架*/
    @ApiModelProperty(value = "是否上架")
    private Boolean saleable;

    /**是否有效*/
    @ApiModelProperty(value = "是否有效")
    private Boolean valid;
}