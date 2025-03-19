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
 * slice_balance_example 实体请求对象
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
@ApiModel(description = "slice_balance_example请求对象")
public class ReqSliceBalance extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "userId", position = 1, required = true)
	private Integer userId;

	/**asset*/
	@NotEmpty(message = "asset不可为空")
	@ApiModelProperty(value = "asset", position = 2, required = true)
	private String asset;

	/**t*/
	@NotNull(message = "t不可为空")
	@ApiModelProperty(value = "t", position = 3, required = true)
	private Short t;

	/**balance*/
	@NotNull(message = "balance不可为空")
	@ApiModelProperty(value = "balance", position = 4, required = true)
	private java.math.BigDecimal balance;


}

