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


/**
 * 
 */
@Data
@ApiModel(description = "用户邀请统计")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedStatisticsModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "总邀请")
	private Integer total;

	@ApiModelProperty(value = "今日邀请")
	private Integer curTotal;

}

