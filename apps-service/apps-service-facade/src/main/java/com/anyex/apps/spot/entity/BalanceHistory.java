/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * balance_history_example 实体对象
 * <p>File：BalanceHistoryExample.java</p>
 * <p>Title: BalanceHistoryExample</p>
 * <p>Description:BalanceHistoryExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "balance_history")
public class BalanceHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@NotNull(message = "time不可为空")
	@ApiModelProperty(value = "time", position = 1, required = true)
	private Double time;

	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "userId", position = 2, required = true)
	private Integer userId;

	/**asset*/
	@NotEmpty(message = "asset不可为空")
	@ApiModelProperty(value = "asset", position = 3, required = true)
	private String asset;

	/**business*/
	@NotEmpty(message = "business不可为空")
	@ApiModelProperty(value = "business", position = 4, required = true)
	private String business;

	/**change*/
	@NotNull(message = "change不可为空")
	@ApiModelProperty(value = "change", position = 5, required = true)
	private java.math.BigDecimal change;

	/**balance*/
	@NotNull(message = "balance不可为空")
	@ApiModelProperty(value = "balance", position = 6, required = true)
	private java.math.BigDecimal balance;

	/**detail*/
	@NotEmpty(message = "detail不可为空")
	@ApiModelProperty(value = "detail", position = 7, required = true)
	private String detail;


}

