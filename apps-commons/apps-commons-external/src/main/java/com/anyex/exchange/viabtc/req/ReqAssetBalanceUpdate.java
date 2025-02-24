/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.exchange.viabtc.req;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqAssetBalanceUpdate implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", position = 1, required = true)
	private Long userId;

	/**币种currency*/
	@NotEmpty(message = "币种currency不可为空")
	@ApiModelProperty(value = "币种currency", position = 2, required = true)
	private String currency;

	/**业务business*/
	@NotEmpty(message = "业务business不可为空")
	@ApiModelProperty(value = "业务business", position = 3, required = true)
	private String business;

	/**业务Id*/
	@NotNull(message = "业务Id不可为空")
	@ApiModelProperty(value = "业务Id", position = 4, required = true)
	private Long businessId;

	/**更新数量change*/
	@NotEmpty(message = "更新数量change不可为空")
	@ApiModelProperty(value = "更新数量change", position = 5, required = true)
	private String change;

	/**更新明细说明*/
	@ApiModelProperty(value = "更新明细说明", position = 6, required = true)
	private JSONObject detail;
}

