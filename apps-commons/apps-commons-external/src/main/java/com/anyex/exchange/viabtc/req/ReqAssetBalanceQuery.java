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
public class ReqAssetBalanceQuery implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", position = 1, required = true)
	private Long userId;

	/**币种currency*/
	@ApiModelProperty(value = "币种currency", position = 2)
	private String currency;
}

