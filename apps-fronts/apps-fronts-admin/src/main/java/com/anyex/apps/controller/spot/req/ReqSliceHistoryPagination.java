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
 * slice_history 分页请求对象
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
@ApiModel(description = "slice_history分页请求对象")
public class ReqSliceHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@ApiModelProperty(value = "time", position = 1)
	private Long time;

	/**endOperId*/
	@ApiModelProperty(value = "endOperId", position = 2)
	private Long endOperId;

	/**endOrderId*/
	@ApiModelProperty(value = "endOrderId", position = 3)
	private Long endOrderId;

	/**endDealsId*/
	@ApiModelProperty(value = "endDealsId", position = 4)
	private Long endDealsId;


}

