/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 充值交易历史 实体对象
 * <p>File：DepositTransHistory.java</p>
 * <p>Title: DepositTransHistory</p>
 * <p>Description:DepositTransHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "充值交易历史")
public class DepositTransHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private java.lang.Long userId;

	/**币种(BTC、ETH、USDT)*/
	@NotEmpty(message = "币种(BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2, required = true)
	private java.lang.String currency;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@NotEmpty(message = "区块链(BTC、ETH、TRON、BSC)不可为空")
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3, required = true)
	private java.lang.String blockchain;

	/**用户充值地址*/
	@NotEmpty(message = "用户充值地址不可为空")
	@ApiModelProperty(value = "用户充值地址", position = 4, required = true)
	private java.lang.String depositAddress;

	/**交易ID*/
	@NotEmpty(message = "交易ID不可为空")
	@ApiModelProperty(value = "交易ID", position = 5, required = true)
	private java.lang.String transId;

	/**充值数量*/
	@NotNull(message = "充值数量不可为空")
	@ApiModelProperty(value = "充值数量", position = 6, required = true)
	private java.math.BigDecimal amount;

	/**网络手续费*/
	@NotNull(message = "网络手续费不可为空")
	@ApiModelProperty(value = "网络手续费", position = 7, required = true)
	private java.math.BigDecimal netFee;

	/**确认状态(unconfirm未确认、confirmed已确认)*/
	@NotEmpty(message = "确认状态(unconfirm未确认、confirmed已确认)不可为空")
	@ApiModelProperty(value = "确认状态(unconfirm未确认、confirmed已确认)", position = 8, required = true)
	private java.lang.String confirmState;

	/**充值入账状态(undeposit未入账、deposited已入账)*/
	@NotEmpty(message = "充值入账状态(undeposit未入账、deposited已入账)不可为空")
	@ApiModelProperty(value = "充值入账状态(undeposit未入账、deposited已入账)", position = 9, required = true)
	private java.lang.String depositState;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 11, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 12)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 13)
	private java.lang.Long updateTime;
}

