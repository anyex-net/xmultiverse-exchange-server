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
public class ReqMarketDeals implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 1, required = true)
	private String market;

	/**count，no more than 10000*/
	@NotNull(message = "limit不可为空")
	@ApiModelProperty(value = "count，no more than 10000", position = 2, required = true)
	private Integer limit;

	/**id limit*/
	@NotNull(message = "id limit不可为空")
	@ApiModelProperty(value = "id limit", position = 3, required = true)
	private Integer lastId;
}

