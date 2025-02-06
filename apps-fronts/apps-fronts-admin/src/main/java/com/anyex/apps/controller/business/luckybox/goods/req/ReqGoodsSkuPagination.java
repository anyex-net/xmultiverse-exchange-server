package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "商品SKU请求对象")
public class ReqGoodsSkuPagination extends Pagination
{
    @ApiModelProperty(value = "商品SKUID")
    private Long id;

    /**产品ID*/
    @ApiModelProperty(value = "产品ID")
    private Long spuId;

    /**商品标题*/
    @ApiModelProperty(value = "商品标题")
    private String title;

    /**商品副标题*/
    @ApiModelProperty(value = "商品副标题")
    private String subTitle;

    /**商品卖点*/
    @ApiModelProperty(value = "商品卖点")
    private String sellingPoint;

    /**商品图标图片*/
    @ApiModelProperty(value = "商品图标图片")
    private String iconImage;

    /**商品主图图片*/
    @ApiModelProperty(value = "商品主图图片(JSON)")
    private String mainImages;

    /**商品详情图片*/
    @ApiModelProperty(value = "商品详情图片(JSON)")
    private String detailImages;

    /**价格*/
    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    /**库存数量*/
    @ApiModelProperty(value = "库存数量")
    private Long stock;

    /**参数*/
    @ApiModelProperty(value = "参数(JSON)")
    private String param;

    /**是否上架*/
    @ApiModelProperty(value = "是否上架")
    private Boolean saleable;

    /**是否有效*/
    @ApiModelProperty(value = "是否有效")
    private Boolean valid;

    /**商品描述*/
    @ApiModelProperty(value = "商品描述")
    private String description;
}