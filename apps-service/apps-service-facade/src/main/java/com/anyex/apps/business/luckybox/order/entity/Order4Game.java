/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 游戏订单记录表 实体对象
 * <p>File：Order4Game.java</p>
 * <p>Title: Order4Game</p>
 * <p>Description:Order4Game</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "游戏订单记录表")
public class Order4Game extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	@NotNull(message = "订单编号不可为空")
	@ApiModelProperty(value = "订单编号", required = true)
	private java.lang.String orderTxNo;

	/**游戏ID*/
	@NotNull(message = "游戏ID不可为空")
	@ApiModelProperty(value = "游戏ID", required = true)
	private java.lang.Long gameId;

	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private java.lang.Long accountId;

	@NotNull(message = "游戏花费金额不可为空")
	@ApiModelProperty(value = "游戏花费金额", required = true)
	private BigDecimal gameExpendBalance;

	/**游戏中奖奖品ID*/
	@NotNull(message = "游戏中奖奖品ID不可为空")
	@ApiModelProperty(value = "游戏中奖奖品ID", required = true)
	private java.lang.Long gamePrizeId;

	/**游戏中奖奖品名称*/
	@NotNull(message = "游戏中奖奖品名称不可为空")
	@ApiModelProperty(value = "游戏中奖奖品名称", required = true)
	private java.lang.String gamePrizeName;

	/**游戏中奖奖励金额*/
	@NotNull(message = "游戏中奖奖励金额不可为空")
	@ApiModelProperty(value = "游戏中奖奖励金额", required = true)
	private java.lang.Integer gameRewardBalance;

	/**订单状态(0未中奖、1已中奖)*/
	@NotNull(message = "订单状态(0未中奖、1已中奖)不可为空")
	@ApiModelProperty(value = "订单状态(0未中奖、1已中奖)", required = true)
	private java.lang.Boolean orderStatus;

	/**订单是否中奖(0未中奖、1已中奖)*/
	@NotNull(message = "订单是否中奖(0未中奖、1已中奖)不可为空")
	@ApiModelProperty(value = "订单是否中奖(0未中奖、1已中奖)", required = true)
	private java.lang.Boolean isWinning;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/////////////////////////////////
	/**账户编号*/
	@ApiModelProperty(value = "账户编号")
	private java.lang.Long unid;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private java.lang.String email;
}

