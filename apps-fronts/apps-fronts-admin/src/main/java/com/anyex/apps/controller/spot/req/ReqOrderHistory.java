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
 * order_history_example 实体请求对象
 * <p>File：ReqOrderHistoryExample.java</p>
 * <p>Title: ReqOrderHistoryExample</p>
 * <p>Description:ReqOrderHistoryExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "order_history_example请求对象")
public class ReqOrderHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**createTime*/
	@NotNull(message = "createTime不可为空")
	@ApiModelProperty(value = "createTime", position = 1, required = true)
	private Double createTime;

	/**finishTime*/
	@NotNull(message = "finishTime不可为空")
	@ApiModelProperty(value = "finishTime", position = 2, required = true)
	private Double finishTime;

	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "userId", position = 3, required = true)
	private Integer userId;

	/**market*/
	@NotEmpty(message = "market不可为空")
	@ApiModelProperty(value = "market", position = 4, required = true)
	private String market;

	/**source*/
	@NotEmpty(message = "source不可为空")
	@ApiModelProperty(value = "source", position = 5, required = true)
	private String source;

	/**t*/
	@NotNull(message = "t不可为空")
	@ApiModelProperty(value = "t", position = 6, required = true)
	private Short t;

	/**side*/
	@NotNull(message = "side不可为空")
	@ApiModelProperty(value = "side", position = 7, required = true)
	private Short side;

	/**price*/
	@NotNull(message = "price不可为空")
	@ApiModelProperty(value = "price", position = 8, required = true)
	private java.math.BigDecimal price;

	/**amount*/
	@NotNull(message = "amount不可为空")
	@ApiModelProperty(value = "amount", position = 9, required = true)
	private java.math.BigDecimal amount;

	/**takerFee*/
	@NotNull(message = "takerFee不可为空")
	@ApiModelProperty(value = "takerFee", position = 10, required = true)
	private java.math.BigDecimal takerFee;

	/**makerFee*/
	@NotNull(message = "makerFee不可为空")
	@ApiModelProperty(value = "makerFee", position = 11, required = true)
	private java.math.BigDecimal makerFee;

	/**dealStock*/
	@NotNull(message = "dealStock不可为空")
	@ApiModelProperty(value = "dealStock", position = 12, required = true)
	private java.math.BigDecimal dealStock;

	/**dealMoney*/
	@NotNull(message = "dealMoney不可为空")
	@ApiModelProperty(value = "dealMoney", position = 13, required = true)
	private java.math.BigDecimal dealMoney;

	/**dealFee*/
	@NotNull(message = "dealFee不可为空")
	@ApiModelProperty(value = "dealFee", position = 14, required = true)
	private java.math.BigDecimal dealFee;


}

