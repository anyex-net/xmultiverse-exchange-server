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
 * slice_balance_example 分页请求对象
 * <p>File：ReqSliceBalanceExample.java</p>
 * <p>Title: ReqSliceBalanceExample</p>
 * <p>Description:ReqSliceBalanceExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "slice_balance_example分页请求对象")
public class ReqSliceBalancePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**userId*/
	@ApiModelProperty(value = "userId", position = 1)
	private Integer userId;

	/**asset*/
	@ApiModelProperty(value = "asset", position = 2)
	private String asset;

	/**t*/
	@ApiModelProperty(value = "t", position = 3)
	private Short t;

	/**balance*/
	@ApiModelProperty(value = "balance", position = 4)
	private java.math.BigDecimal balance;


}

