/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "参数配置")
public class ReqSysParameter extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**系统名称*/
	@NotNull(message = "系统名称不可为空")
	@ApiModelProperty(value = "系统名称", required = true)
	private String systemName;

	/**参数名称*/
	@NotNull(message = "参数名称不可为空")
	@ApiModelProperty(value = "参数名称", required = true)
	private String parameterName;

	/**参数大类*/
	@NotNull(message = "参数大类不可为空")
	@ApiModelProperty(value = "参数大类", required = true)
	private String division;

	/**参数类型*/
	@NotNull(message = "参数类型不可为空")
	@ApiModelProperty(value = "参数类型", required = true)
	private String type;

	/**参数值值域*/
	@NotNull(message = "参数值值域不可为空")
	@ApiModelProperty(value = "参数值值域", required = true)
	private String valueBound;

	/**参数值*/
	@NotNull(message = "参数值不可为空")
	@ApiModelProperty(value = "参数值", required = true)
	private String value;

	/**参数备注*/
	@NotNull(message = "参数备注不可为空")
	@ApiModelProperty(value = "参数备注", required = true)
	private String remark;
}

