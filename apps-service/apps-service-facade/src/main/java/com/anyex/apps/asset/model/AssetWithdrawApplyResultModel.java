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
@ApiModel(description = "提现申请结果对象")
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithdrawApplyResultModel
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "业务ID")
	private Long id;

	@ApiModelProperty(value = "交易编码")
	private String trxNo;

	@ApiModelProperty(value = "交易状态(pending处理中 success 交易成功)")
	private String status;

	@ApiModelProperty(value = "支付通道")
	private String trxChannel;
}

