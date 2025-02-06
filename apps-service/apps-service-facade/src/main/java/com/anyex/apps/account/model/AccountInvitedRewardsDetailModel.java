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
 * APP 邀请返佣 点开一级用户后的明细
 */
@Data
@ApiModel(description = "一级邀请明细")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedRewardsDetailModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ALL直接邀请返佣总金额")
	private BigDecimal totalDirectSum;

	@ApiModelProperty(value = "ALL直接邀请返佣总金额(待发)")
	private BigDecimal totalDirectPendingSum;

	@ApiModelProperty(value = "ALL直接邀请人数")
	private Integer totalDirectCnt;

	@ApiModelProperty(value = "ALL间接邀请返佣总金额")
	private BigDecimal totalIndirectSum;

	@ApiModelProperty(value = "ALL间接邀请返佣总金额(待发)")
	private BigDecimal totalIndirectPendingSum;

	@ApiModelProperty(value = "ALL间接邀请人数")
	private Integer totalIndirectCnt;

	@ApiModelProperty(value = "TODAY直接邀请返佣总金额")
	private BigDecimal todayDirectSum;

	@ApiModelProperty(value = "TODAY直接邀请人数")
	private Integer todayDirectCnt;

	@ApiModelProperty(value = "TODAY间接邀请返佣总金额")
	private BigDecimal todayIndirectSum;

	@ApiModelProperty(value = "TODAY间接邀请人数")
	private Integer todayIndirectCnt;

}

