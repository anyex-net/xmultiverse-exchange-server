/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.exchange.viabtc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class ReqMarketStatusToday implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 1, required = true)
	private String market;
}

