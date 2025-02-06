/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "商品SKU")
public class ReqGoodsSku extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**产品ID*/
	@NotNull(message = "产品ID不可为空")
	@ApiModelProperty(value = "产品ID", required = true)
	private Long spuId;

	/**商品标题*/
	@NotNull(message = "商品标题不可为空")
	@ApiModelProperty(value = "商品标题", required = true)
	private String title;

	/**商品副标题*/
	@ApiModelProperty(value = "商品副标题")
	private String subTitle;

	/**商品卖点*/
	@ApiModelProperty(value = "商品卖点")
	private String sellingPoint;

	/**商品图标图片*/
	@NotNull(message = "商品图标图片不可为空")
	@ApiModelProperty(value = "商品图标图片", required = true)
	private String iconImage;

	/**商品主图图片*/
	@NotNull(message = "商品主图图片不可为空")
	@ApiModelProperty(value = "商品主图图片(JSON)", required = true)
	private String mainImages;

	/**商品详情图片*/
	@NotNull(message = "商品详情图片不可为空")
	@ApiModelProperty(value = "商品详情图片(JSON)", required = true)
	private String detailImages;

	/**价格*/
	@NotNull(message = "价格不可为空")
	@ApiModelProperty(value = "价格", required = true)
	private java.math.BigDecimal price;

	/**库存数量*/
	@NotNull(message = "库存数量不可为空")
	@ApiModelProperty(value = "库存数量", required = true)
	private java.lang.Long stock;

	/**参数*/
	@NotNull(message = "参数不可为空")
	@ApiModelProperty(value = "参数(JSON)", required = true)
	private String param;

	/**是否上架*/
	@NotNull(message = "是否上架不可为空")
	@ApiModelProperty(value = "是否上架", required = true)
	private Boolean saleable;

	/**是否有效*/
	@NotNull(message = "是否有效不可为空")
	@ApiModelProperty(value = "是否有效", required = true)
	private Boolean valid;

	/**商品描述*/
	@ApiModelProperty(value = "商品描述")
	private String description;
}

