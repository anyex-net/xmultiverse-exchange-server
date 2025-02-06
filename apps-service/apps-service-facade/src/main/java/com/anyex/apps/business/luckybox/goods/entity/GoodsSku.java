/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品SKU表 实体对象
 * <p>File：GoodsSku.java</p>
 * <p>Title: GoodsSku</p>
 * <p>Description:GoodsSku</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品SKU表")
public class GoodsSku extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**产品ID*/
	@NotNull(message = "产品ID不可为空")
	@ApiModelProperty(value = "产品ID", required = true)
	private java.lang.Long spuId;

	/**商品标题*/
	@NotNull(message = "商品标题不可为空")
	@ApiModelProperty(value = "商品标题", required = true)
	private java.lang.String title;

	/**商品副标题*/
	@ApiModelProperty(value = "商品副标题")
	private java.lang.String subTitle;

	/**商品卖点*/
	@ApiModelProperty(value = "商品卖点")
	private java.lang.String sellingPoint;

	/**商品图标图片*/
	@NotNull(message = "商品图标图片不可为空")
	@ApiModelProperty(value = "商品图标图片", required = true)
	private java.lang.String iconImage;

	/**商品主图图片*/
	@NotNull(message = "商品主图图片不可为空")
	@ApiModelProperty(value = "商品主图图片(JSON)", required = true)
	private java.lang.String mainImages;

	/**商品详情图片*/
	@NotNull(message = "商品详情图片不可为空")
	@ApiModelProperty(value = "商品详情图片(JSON)", required = true)
	private java.lang.String detailImages;

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
	private java.lang.String param;

	/**是否上架*/
	@NotNull(message = "是否上架不可为空")
	@ApiModelProperty(value = "是否上架", required = true)
	private java.lang.Boolean saleable;

	/**是否有效*/
	@NotNull(message = "是否有效不可为空")
	@ApiModelProperty(value = "是否有效", required = true)
	private java.lang.Boolean valid;

	/**商品描述*/
	@ApiModelProperty(value = "商品描述")
	private java.lang.String description;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/////////////////////////////////
	/**分类ID*/
	@ApiModelProperty(value = "分类ID")
	private java.lang.Long categoryId;

	/**品牌ID*/
	@ApiModelProperty(value = "品牌ID")
	private java.lang.Long brandId;

	/**品类ID*/
	@ApiModelProperty(value = "品类ID")
	private java.lang.Long spgId;
}

