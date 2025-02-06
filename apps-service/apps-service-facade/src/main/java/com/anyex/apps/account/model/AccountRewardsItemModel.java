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
@ApiModel(description = "用户佣金统计")
@AllArgsConstructor
@NoArgsConstructor
public class AccountRewardsItemModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "UID")
	private Long unid;

	@ApiModelProperty(value = "头像")
	private String headUrl;

	@ApiModelProperty(value = "推荐码")
	private String referralCode;

	@ApiModelProperty(value = "总佣金")
	private BigDecimal total;



}

