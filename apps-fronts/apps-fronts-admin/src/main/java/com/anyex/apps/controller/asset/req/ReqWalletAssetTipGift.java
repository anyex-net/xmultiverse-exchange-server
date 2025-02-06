/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钱包资产打赏礼物记录 实体请求对象
 * <p>File：ReqWalletAssetTipGift.java</p>
 * <p>Title: ReqWalletAssetTipGift</p>
 * <p>Description:ReqWalletAssetTipGift</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产打赏礼物记录请求对象")
public class ReqWalletAssetTipGift extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**来源账户ID*/
	@NotNull(message = "来源账户ID不可为空")
	@ApiModelProperty(value = "来源账户ID", required = true)
	private java.lang.Long fromAccountId;

	/**去处账户ID*/
	@NotNull(message = "去处账户ID不可为空")
	@ApiModelProperty(value = "去处账户ID", required = true)
	private java.lang.Long toAccountId;

	/**币种(法币、BTC、ETH、USD)*/
	@NotEmpty(message = "币种(法币、BTC、ETH、USD)不可为空")
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)", required = true)
	private java.lang.String currency;

	/**交易编号*/
	@NotEmpty(message = "交易编号不可为空")
	@ApiModelProperty(value = "交易编号", required = true)
	private java.lang.String trxNo;

	/**金额*/
	@NotNull(message = "金额不可为空")
	@ApiModelProperty(value = "金额", required = true)
	private java.math.BigDecimal trxBalance;

	/**手续费*/
	@NotNull(message = "手续费不可为空")
	@ApiModelProperty(value = "手续费", required = true)
	private java.math.BigDecimal trxFee;

	/**状态(0无效、1有效)*/
	@NotNull(message = "状态(0无效、1有效)不可为空")
	@ApiModelProperty(value = "状态(0无效、1有效)", required = true)
	private java.lang.Boolean status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
}

