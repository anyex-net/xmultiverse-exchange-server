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
@ApiModel(description = "商品品类")
public class ReqGoodsSpecGroup extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**品类名称*/
	@NotNull(message = "品类名称不可为空")
	@ApiModelProperty(value = "品类名称", required = true)
	private String name;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
}

