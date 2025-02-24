/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.exchange.viabtc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReqAssetSummary implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**币种currency*/
	@ApiModelProperty(value = "币种currency", position = 1)
	private String currency;
}

