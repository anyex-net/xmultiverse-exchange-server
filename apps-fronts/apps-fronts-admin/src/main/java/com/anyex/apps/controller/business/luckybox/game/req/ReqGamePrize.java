/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "游戏奖品")
public class ReqGamePrize extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**游戏ID*/
	@NotNull(message = "游戏ID不可为空")
	@ApiModelProperty(value = "游戏ID", required = true)
	private java.lang.Long gameId;

	/**奖品名称*/
	@NotNull(message = "奖品名称不可为空")
	@ApiModelProperty(value = "奖品名称", required = true)
	private java.lang.String prizeName;

	/**奖品图片Url*/
	@NotNull(message = "奖品图片Url不可为空")
	@ApiModelProperty(value = "奖品图片Url", required = true)
	private java.lang.String prizeImgUrl;

	/**商品价格*/
	@NotNull(message = "商品价格不可为空")
	@ApiModelProperty(value = "商品价格", required = true)
	private java.math.BigDecimal price;

	/**成本*/
	@NotNull(message = "成本不可为空")
	@ApiModelProperty(value = "成本", required = true)
	private java.math.BigDecimal cost;

	/**百份中奖数量*/
	@NotNull(message = "百份中奖数量不可为空")
	@ApiModelProperty(value = "百份中奖数量", required = true)
	private java.lang.Integer percentWinningAmount;

	/**奖励金额*/
	@NotNull(message = "奖励金额不可为空")
	@ApiModelProperty(value = "奖励金额", required = true)
	private java.lang.Integer rewardBalance;

	/**游戏是否启用(0未启用、1启用)*/
	@NotNull(message = "游戏是否启用(0未启用、1启用)不可为空")
	@ApiModelProperty(value = "游戏是否启用(0未启用、1启用)", required = true)
	private java.lang.Boolean status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

