/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户持有数量奖励配置 实体请求对象
 * <p>File：ReqUserHoldAmountRewardConfig.java</p>
 * <p>Title: ReqUserHoldAmountRewardConfig</p>
 * <p>Description:ReqUserHoldAmountRewardConfig</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户持有数量奖励配置请求对象")
public class ReqUserHoldAmountRewardConfig extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**币种(BTC、ETH、USDT)*/
	@NotEmpty(message = "币种(BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 1, required = true)
	private java.lang.String currency;

	/**持有数量1(起)*/
	@NotNull(message = "持有数量1(起)不可为空")
	@ApiModelProperty(value = "持有数量1(起)", position = 2, required = true)
	private java.math.BigDecimal holdAmount1;

	/**持有数量2(终)*/
	@NotNull(message = "持有数量2(终)不可为空")
	@ApiModelProperty(value = "持有数量2(终)", position = 3, required = true)
	private java.math.BigDecimal holdAmount2;

	/**持有等级*/
	@NotEmpty(message = "持有等级不可为空")
	@ApiModelProperty(value = "持有等级", position = 4, required = true)
	private java.lang.String holdLevel;

	/**持有对应交易手续费费率折扣*/
	@NotNull(message = "持有对应交易手续费费率折扣不可为空")
	@ApiModelProperty(value = "持有对应交易手续费费率折扣", position = 5, required = true)
	private java.math.BigDecimal holdRateDiscount;

	/**状态(0不可用、1可用)*/
	@NotNull(message = "状态(0不可用、1可用)不可为空")
	@ApiModelProperty(value = "状态(0不可用、1可用)", position = 6, required = true)
	private java.lang.Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 7)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 8, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 9)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 10)
	private java.lang.Long updateTime;


}

