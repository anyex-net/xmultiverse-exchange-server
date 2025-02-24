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
public class ReqMarketKline implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 1, required = true)
	private String market;

	/**start*/
	@NotNull(message = "start不可为空")
	@ApiModelProperty(value = "start", position = 2, required = true)
	private Integer start;

	/**end*/
	@NotNull(message = "end不可为空")
	@ApiModelProperty(value = "end", position = 3, required = true)
	private Integer end;

	/**interval*/
	@NotNull(message = "interval不可为空")
	@ApiModelProperty(value = "interval", position = 4, required = true)
	private Integer interval;

}

