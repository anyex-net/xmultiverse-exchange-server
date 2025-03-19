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
 * slice_order_example 实体请求对象
 * <p>File：ReqSliceOrderExample.java</p>
 * <p>Title: ReqSliceOrderExample</p>
 * <p>Description:ReqSliceOrderExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "slice_order_example请求对象")
public class ReqSliceOrder extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**t*/
	@NotNull(message = "t不可为空")
	@ApiModelProperty(value = "t", position = 1, required = true)
	private Short t;

	/**side*/
	@NotNull(message = "side不可为空")
	@ApiModelProperty(value = "side", position = 2, required = true)
	private Short side;

	/**createTime*/
	@NotNull(message = "createTime不可为空")
	@ApiModelProperty(value = "createTime", position = 3, required = true)
	private Double createTime;

	/**updateTime*/
	@NotNull(message = "updateTime不可为空")
	@ApiModelProperty(value = "updateTime", position = 4, required = true)
	private Double updateTime;

	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "userId", position = 5, required = true)
	private Integer userId;

	/**market*/
	@NotEmpty(message = "market不可为空")
	@ApiModelProperty(value = "market", position = 6, required = true)
	private String market;

	/**price*/
	@NotNull(message = "price不可为空")
	@ApiModelProperty(value = "price", position = 7, required = true)
	private java.math.BigDecimal price;

	/**amount*/
	@NotNull(message = "amount不可为空")
	@ApiModelProperty(value = "amount", position = 8, required = true)
	private java.math.BigDecimal amount;

	/**takerFee*/
	@NotNull(message = "takerFee不可为空")
	@ApiModelProperty(value = "takerFee", position = 9, required = true)
	private java.math.BigDecimal takerFee;

	/**makerFee*/
	@NotNull(message = "makerFee不可为空")
	@ApiModelProperty(value = "makerFee", position = 10, required = true)
	private java.math.BigDecimal makerFee;

	/**left*/
	@NotNull(message = "left不可为空")
	@ApiModelProperty(value = "left", position = 11, required = true)
	private java.math.BigDecimal left;

	/**freeze*/
	@NotNull(message = "freeze不可为空")
	@ApiModelProperty(value = "freeze", position = 12, required = true)
	private java.math.BigDecimal freeze;

	/**dealStock*/
	@NotNull(message = "dealStock不可为空")
	@ApiModelProperty(value = "dealStock", position = 13, required = true)
	private java.math.BigDecimal dealStock;

	/**dealMoney*/
	@NotNull(message = "dealMoney不可为空")
	@ApiModelProperty(value = "dealMoney", position = 14, required = true)
	private java.math.BigDecimal dealMoney;

	/**dealFee*/
	@NotNull(message = "dealFee不可为空")
	@ApiModelProperty(value = "dealFee", position = 15, required = true)
	private java.math.BigDecimal dealFee;


}

