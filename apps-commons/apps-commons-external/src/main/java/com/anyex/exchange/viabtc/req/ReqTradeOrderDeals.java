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
public class ReqTradeOrderDeals implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**orderId*/
	@NotNull(message = "orderId不可为空")
	@ApiModelProperty(value = "orderId", position = 1, required = true)
	private Long orderId;

	/**offset position*/
	@NotNull(message = "offset不可为空")
	@ApiModelProperty(value = "offset position", position = 2, required = true)
	private Integer offset;

	/**count limit*/
	@NotNull(message = "limit不可为空")
	@ApiModelProperty(value = "count limit", position = 3, required = true)
	private Integer limit;
}

