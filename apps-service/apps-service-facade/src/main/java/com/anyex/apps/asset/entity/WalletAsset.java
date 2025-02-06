/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 钱包资产表 实体对象
 * <p>File：WalletAsset.java</p>
 * <p>Title: WalletAsset</p>
 * <p>Description:WalletAsset</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产表")
public class WalletAsset extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private java.lang.Long accountId;

	/**币种(法币、BTC、ETH、USDT)*/
	@NotNull(message = "币种(法币、BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USDT)", required = true)
	private java.lang.String currency;

	/**余额*/
	@NotNull(message = "余额不可为空")
	@ApiModelProperty(value = "余额", required = true)
	private java.math.BigDecimal balance;

	/**冻结(不可用)*/
	@NotNull(message = "冻结(不可用)不可为空")
	@ApiModelProperty(value = "冻结(不可用)", required = true)
	private java.math.BigDecimal frozenBal;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
}

