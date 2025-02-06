/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户浮动盈亏监控 分页请求对象
 * <p>File：ReqMonitorAccountProfitLoss.java</p>
 * <p>Title: ReqMonitorAccountProfitLoss</p>
 * <p>Description:ReqMonitorAccountProfitLoss</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户浮动盈亏监控分页请求对象")
public class ReqMonitorAccountProfitLossPagination extends Pagination
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "账户ID")
	private Long id;


}

