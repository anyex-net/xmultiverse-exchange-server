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
@ApiModel(description = "商品品类参数")
public class ReqGoodsSpecParam extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**品类Id*/
	@NotNull(message = "品类Id不可为空")
	@ApiModelProperty(value = "品类Id", required = true)
	private Long spgId;

	/**参数名称*/
	@NotNull(message = "参数名称不可为空")
	@ApiModelProperty(value = "参数名称", required = true)
	private String paramName;

	/**是否为数字参数*/
	@NotNull(message = "是否为数字参数不可为空")
	@ApiModelProperty(value = "是否为数字参数", required = true)
	private Boolean isNumeric;

	/**单位(量词)*/
	@ApiModelProperty(value = "单位(量词)")
	private String unit;

	/**参数值*/
	@ApiModelProperty(value = "参数值")
	private String paramValue;
}

