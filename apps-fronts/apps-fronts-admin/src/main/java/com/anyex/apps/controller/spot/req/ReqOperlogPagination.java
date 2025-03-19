/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * operlog_example 分页请求对象
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
@ApiModel(description = "operlog_example分页请求对象")
public class ReqOperlogPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@ApiModelProperty(value = "time", position = 1)
	private Double time;

	/**detail*/
	@ApiModelProperty(value = "detail", position = 2)
	private String detail;


}

