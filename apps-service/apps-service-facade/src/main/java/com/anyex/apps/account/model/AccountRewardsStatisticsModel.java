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
@ApiModel(description = "佣金统计")
@AllArgsConstructor
@NoArgsConstructor
public class AccountRewardsStatisticsModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "总发放佣金")
	private BigDecimal total;

	@ApiModelProperty(value = "今日发放佣金")
	private BigDecimal curTotal;

}

