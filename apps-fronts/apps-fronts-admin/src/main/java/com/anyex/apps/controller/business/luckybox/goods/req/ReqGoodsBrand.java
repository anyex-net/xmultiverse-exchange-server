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
@ApiModel(description = "商品品牌")
public class ReqGoodsBrand extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**品牌名称*/
	@NotNull(message = "品牌名称不可为空")
	@ApiModelProperty(value = "品牌名称", required = true)
	private String name;

	/**品牌Logo图片Url*/
	@NotNull(message = "品牌Logo图片Url不可为空")
	@ApiModelProperty(value = "品牌Logo图片Url", required = true)
	private String logoImageUrl;

	/**品牌首字母*/
	@NotNull(message = "品牌首字母不可为空")
	@ApiModelProperty(value = "品牌首字母", required = true)
	private String letter;

	/**状态(是否启用)*/
	@NotNull(message = "状态(是否启用)不可为空")
	@ApiModelProperty(value = "状态(是否启用)", required = true)
	private Boolean status;
}

