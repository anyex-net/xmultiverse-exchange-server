/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.exchange.viabtc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqTradeOrderDepth implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 1, required = true)
	private String market;

	/**count limit*/
	@NotNull(message = "limit不可为空")
	@ApiModelProperty(value = "count limit", position = 4, required = true)
	private Integer limit;

	/**interval String, e.g. "1" for 1 unit interval, "0" for no interval*/
	@NotEmpty(message = "interval不可为空")
	@ApiModelProperty(value = "interval \"1\" for 1 unit interval, \"0\" for no interval", position = 1, required = true)
	private String interval;
}

