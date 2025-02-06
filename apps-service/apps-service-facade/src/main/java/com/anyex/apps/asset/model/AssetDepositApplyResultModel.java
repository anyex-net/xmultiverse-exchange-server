/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@ApiModel(description = "充值申请结果对象")
@AllArgsConstructor
@NoArgsConstructor
public class AssetDepositApplyResultModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "业务ID")
	private Long id;

	@ApiModelProperty(value = "交易编码")
	private String trxNo;

	@ApiModelProperty(value = "支付链接")
	private String url;

	@ApiModelProperty(value = "支付通道")
	private String trxChannel;

	@ApiModelProperty(value = "deposit amount")
	private Double amount;

	@ApiModelProperty(value = "accountType")
	private String accountType;

	@ApiModelProperty(value = "mobile")
	private String mobile;

	@ApiModelProperty(value = "email")
	private String email;

	@ApiModelProperty(value = "realname")
	private String realname;

	@ApiModelProperty(value = "cnic")
	private String cnic;
}

