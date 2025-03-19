/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * slice_history 实体请求对象
 * <p>File：ReqSliceHistory.java</p>
 * <p>Title: ReqSliceHistory</p>
 * <p>Description:ReqSliceHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "slice_history请求对象")
public class ReqSliceHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@NotNull(message = "time不可为空")
	@ApiModelProperty(value = "time", position = 1, required = true)
	private Long time;

	/**endOperId*/
	@NotNull(message = "endOperId不可为空")
	@ApiModelProperty(value = "endOperId", position = 2, required = true)
	private Long endOperId;

	/**endOrderId*/
	@NotNull(message = "endOrderId不可为空")
	@ApiModelProperty(value = "endOrderId", position = 3, required = true)
	private Long endOrderId;

	/**endDealsId*/
	@NotNull(message = "endDealsId不可为空")
	@ApiModelProperty(value = "endDealsId", position = 4, required = true)
	private Long endDealsId;


}

