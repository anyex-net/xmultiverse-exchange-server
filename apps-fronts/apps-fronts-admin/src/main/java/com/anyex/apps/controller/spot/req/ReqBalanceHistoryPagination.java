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
 * balance_history_example 分页请求对象
 * <p>File：ReqBalanceHistoryExample.java</p>
 * <p>Title: ReqBalanceHistoryExample</p>
 * <p>Description:ReqBalanceHistoryExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "balance_history_example分页请求对象")
public class ReqBalanceHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@ApiModelProperty(value = "time", position = 1)
	private Double time;

	/**userId*/
	@ApiModelProperty(value = "userId", position = 2)
	private Integer userId;

	/**asset*/
	@ApiModelProperty(value = "asset", position = 3)
	private String asset;

	/**business*/
	@ApiModelProperty(value = "business", position = 4)
	private String business;

	/**change*/
	@ApiModelProperty(value = "change", position = 5)
	private java.math.BigDecimal change;

	/**balance*/
	@ApiModelProperty(value = "balance", position = 6)
	private java.math.BigDecimal balance;

	/**detail*/
	@ApiModelProperty(value = "detail", position = 7)
	private String detail;


}

