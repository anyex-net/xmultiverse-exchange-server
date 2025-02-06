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

import java.util.List;


/**
 * 
 */
@Data
@ApiModel(description = "用户邀请统计")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedStatisticsForAppModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "合计信息")
	private AccountInvitedStatisticsTotalForAppModel totalModel;

	@ApiModelProperty(value = "返佣列表")
	private List<AccountInvitedStatisticsItemForAppModel> items;

}

