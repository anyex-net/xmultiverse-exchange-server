/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqSpotAssetBalanceHistory implements Serializable
{
	private static final long serialVersionUID = 1L;

//	/**用户Id*/
//	@NotNull(message = "用户Id不可为空")
//	@ApiModelProperty(value = "用户Id", position = 1, required = true)
//	private Long userId;

	/**币种currency*/
	@NotEmpty(message = "币种currency不可为空")
	@ApiModelProperty(value = "币种currency", position = 2, required = true)
	private String currency;

	/**业务business*/
	@NotEmpty(message = "业务business不可为空")
	@ApiModelProperty(value = "业务business", position = 3, required = true)
	private String business;

	/**开始时间(0 for unlimited)*/
	@NotNull(message = "开始时间不可为空")
	@ApiModelProperty(value = "开始时间(0 for unlimited)", position = 4, required = true)
	private Integer startTime;

	/**结束时间(0 for unlimited)*/
	@NotNull(message = "结束时间不可为空")
	@ApiModelProperty(value = "结束时间(0 for unlimited)", position = 5, required = true)
	private Integer endTime;

	/**offset position*/
	@NotNull(message = "offset不可为空")
	@ApiModelProperty(value = "offset position", position = 6, required = true)
	private Integer offset;

	/**count limit*/
	@NotNull(message = "limit不可为空")
	@ApiModelProperty(value = "count limit", position = 7, required = true)
	private Integer limit;
}

