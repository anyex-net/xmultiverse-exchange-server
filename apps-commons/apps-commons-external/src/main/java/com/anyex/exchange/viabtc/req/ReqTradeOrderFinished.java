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
public class ReqTradeOrderFinished implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", position = 1, required = true)
	private Long userId;

	/**交易对market*/
	@NotEmpty(message = "交易对market不可为空")
	@ApiModelProperty(value = "交易对market", position = 2, required = true)
	private String market;

	/**开始时间(0 for unlimited)*/
	@NotNull(message = "开始时间不可为空")
	@ApiModelProperty(value = "开始时间(0 for unlimited)", position = 3, required = true)
	private Integer startTime;

	/**结束时间(0 for unlimited)*/
	@NotNull(message = "结束时间不可为空")
	@ApiModelProperty(value = "结束时间(0 for unlimited)", position = 4, required = true)
	private Integer endTime;

	/**offset position*/
	@NotNull(message = "offset不可为空")
	@ApiModelProperty(value = "offset position", position = 5, required = true)
	private Integer offset;

	/**count limit*/
	@NotNull(message = "limit不可为空")
	@ApiModelProperty(value = "count limit", position = 6, required = true)
	private Integer limit;

	/**交易方向0 for no limit，1 for sell，2 for buy*/
	@ApiModelProperty(value = "交易方向0 for no limit，1 for sell，2 for buy", position = 7)
	private Integer side;
}

