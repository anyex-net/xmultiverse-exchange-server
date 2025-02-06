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
 * APP 每一级 全部统计和当日统计
 */
@Data
@ApiModel(description = "邀请统计明细")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedRewardsEveryLevelDetailModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ALL邀请返佣总金额")
	private BigDecimal totalSum;

	@ApiModelProperty(value = "ALL邀请人数")
	private Integer totalCnt;

	@ApiModelProperty(value = "TODAY邀请返佣总金额")
	private BigDecimal todaySum;

	@ApiModelProperty(value = "TODAY邀请人数")
	private Integer todayCnt;

}

