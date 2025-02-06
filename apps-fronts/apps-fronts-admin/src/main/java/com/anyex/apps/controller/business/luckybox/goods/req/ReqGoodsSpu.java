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
@ApiModel(description = "产品SPU")
public class ReqGoodsSpu extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**标题*/
	@NotNull(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
	private String title;

	/**副标题*/
	@NotNull(message = "副标题不可为空")
	@ApiModelProperty(value = "副标题", required = true)
	private String subTitle;

	/**分类ID*/
	@NotNull(message = "分类ID不可为空")
	@ApiModelProperty(value = "分类ID", required = true)
	private Long categoryId;

	/**品牌ID*/
	@NotNull(message = "品牌ID不可为空")
	@ApiModelProperty(value = "品牌ID", required = true)
	private Long brandId;

	/**品类ID*/
	@NotNull(message = "品类ID不可为空")
	@ApiModelProperty(value = "品类ID", required = true)
	private Long spgId;

	/**是否上架*/
	@NotNull(message = "是否上架不可为空")
	@ApiModelProperty(value = "是否上架", required = true)
	private Boolean saleable;

	/**是否有效*/
	@NotNull(message = "是否有效不可为空")
	@ApiModelProperty(value = "是否有效", required = true)
	private Boolean valid;
}

