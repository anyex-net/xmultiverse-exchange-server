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
 * 商品分类与品牌关联表 实体对象
 * <p>File：GoodsCategoryBrand.java</p>
 * <p>Title: GoodsCategoryBrand</p>
 * <p>Description:GoodsCategoryBrand</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品分类与品牌关联表")
public class GoodsCategoryBrand extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**分类ID*/
	@NotNull(message = "分类ID不可为空")
	@ApiModelProperty(value = "分类ID", required = true)
	private java.lang.Long categoryId;

	/**品牌ID*/
	@NotNull(message = "品牌ID不可为空")
	@ApiModelProperty(value = "品牌ID", required = true)
	private java.lang.Long brandId;

	public GoodsCategoryBrand(Long categoryId, long brandId) {
		this.categoryId = categoryId;
		this.brandId = brandId;
	}
}

