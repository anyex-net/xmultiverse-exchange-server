/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * 
 */
@Data
@ApiModel(description = "用户邀请合计")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedStatisticsTotalForAppModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "合计返佣")
	private BigDecimal total;

	@ApiModelProperty(value = "待返佣")
	private BigDecimal curTotal;

	@ApiModelProperty(value = "已返佣")
	private BigDecimal hisTotal;

	@ApiModelProperty(value = "直接返佣")
	private BigDecimal direct;

	@ApiModelProperty(value = "间接返佣")
	private BigDecimal indirect;

}

