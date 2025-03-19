/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * operlog_example 实体请求对象
 * <p>File：ReqOperlogExample.java</p>
 * <p>Title: ReqOperlogExample</p>
 * <p>Description:ReqOperlogExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "operlog_example请求对象")
public class ReqOperlog extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@NotNull(message = "time不可为空")
	@ApiModelProperty(value = "time", position = 1, required = true)
	private Double time;

	/**detail*/
	@ApiModelProperty(value = "detail", position = 2)
	private String detail;


}

