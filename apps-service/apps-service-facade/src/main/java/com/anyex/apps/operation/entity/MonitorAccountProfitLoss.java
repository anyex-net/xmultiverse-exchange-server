/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 账户浮动盈亏监控 实体对象
 * <p>File：MonitorAccountProfitLoss.java</p>
 * <p>Title: MonitorAccountProfitLoss</p>
 * <p>Description:MonitorAccountProfitLoss</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户浮动盈亏监控")
public class MonitorAccountProfitLoss extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**余额*/
	@NotNull(message = "余额不可为空")
	@ApiModelProperty(value = "余额", required = true)
	private java.math.BigDecimal balance;

	/**冻结余额(不可用)*/
	@NotNull(message = "冻结余额(不可用)不可为空")
	@ApiModelProperty(value = "冻结余额(不可用)", required = true)
	private java.math.BigDecimal frozenBal;

	/**累计充值流入*/
	@NotNull(message = "累计充值流入不可为空")
	@ApiModelProperty(value = "累计充值流入", required = true)
	private java.math.BigDecimal sumDeposit;

	/**累计提现流出*/
	@NotNull(message = "累计提现流出不可为空")
	@ApiModelProperty(value = "累计提现流出", required = true)
	private java.math.BigDecimal sumWithDraw;

	/**累计强增流入*/
	@NotNull(message = "累计强增流入不可为空")
	@ApiModelProperty(value = "累计强增流入", required = true)
	private java.math.BigDecimal sumAdjustAdd;

	/**累计强减流出*/
	@NotNull(message = "累计强减流出不可为空")
	@ApiModelProperty(value = "累计强减流出", required = true)
	private java.math.BigDecimal sumAdjustSub;

	/**浮动盈亏*/
	@NotNull(message = "浮动盈亏不可为空")
	@ApiModelProperty(value = "浮动盈亏", required = true)
	private java.math.BigDecimal profitLoss;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;


}

