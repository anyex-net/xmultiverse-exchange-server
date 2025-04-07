/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqSpotTradeOrderPutLimit implements Serializable
{
	private static final long serialVersionUID = 1L;

//	/**用户Id*/
//	@NotNull(message = "用户Id不可为空")
//	@ApiModelProperty(value = "用户Id", position = 1, required = true)
//	private Long userId;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 2, required = true)
	private String market;

	/**交易方向(1:sell,2:buy)*/
	@NotNull(message = "交易方向不可为空")
	@ApiModelProperty(value = "交易方向(1:sell,2:buy)", position = 3, required = true)
	private Integer side;

	/**数量amount*/
	@NotEmpty(message = "数量不可为空")
	@ApiModelProperty(value = "数量amount", position = 4, required = true)
	private String amount;

	/**价格price*/
	@NotEmpty(message = "价格不可为空")
	@ApiModelProperty(value = "价格price", position = 5, required = true)
	private String price;

	/**takerFeeRate*/
	@NotEmpty(message = "takerFeeRate不可为空")
	@ApiModelProperty(value = "takerFeeRate", position = 6, required = true)
	private String takerFeeRate;

	/**makerFeeRate*/
	@NotEmpty(message = "makerFeeRate不可为空")
	@ApiModelProperty(value = "makerFeeRate", position = 7, required = true)
	private String makerFeeRate;

	/**来源source*/
	@NotEmpty(message = "来源sourcee不可为空")
	@ApiModelProperty(value = "来源source", position = 8, required = true)
	private String source;
}

