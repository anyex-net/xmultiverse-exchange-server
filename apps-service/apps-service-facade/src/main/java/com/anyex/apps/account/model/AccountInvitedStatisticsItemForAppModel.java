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
@ApiModel(description = "用户邀请返佣列表")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedStatisticsItemForAppModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "账户ID")
	private Long accountId;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "头像")
	private String headUrl;

	@ApiModelProperty(value = "合计返佣")
	private BigDecimal total;

	@ApiModelProperty(value = "待返佣")
	private BigDecimal curTotal;

	@ApiModelProperty(value = "已返佣")
	private BigDecimal hisTotal;

	@ApiModelProperty(value = "注册时间")
	private Long registerTime;

	@ApiModelProperty(value = "是否有邀请 0无 ")
	private Integer hasInvite;

}

