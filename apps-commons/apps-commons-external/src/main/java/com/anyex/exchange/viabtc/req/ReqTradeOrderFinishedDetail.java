/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.exchange.viabtc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqTradeOrderFinishedDetail implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**orderId*/
	@NotNull(message = "orderId不可为空")
	@ApiModelProperty(value = "orderId", position = 1, required = true)
	private Long orderId;
}

