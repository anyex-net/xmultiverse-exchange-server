/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.goods.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "商品分类")
public class ReqGoodsCategory extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**分类名称*/
	@NotNull(message = "分类名称不可为空")
	@ApiModelProperty(value = "分类名称", required = true)
	private String name;

	/**上级分类ID*/
	@ApiModelProperty(value = "上级分类ID")
	private Long parentId;

	/**排序*/
	@NotNull(message = "排序不可为空")
	@ApiModelProperty(value = "排序", required = true)
	private Integer sort;
}

