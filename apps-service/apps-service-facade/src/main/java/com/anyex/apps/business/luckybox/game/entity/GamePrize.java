/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 游戏奖品表 实体对象
 * <p>File：GamePrize.java</p>
 * <p>Title: GamePrize</p>
 * <p>Description:GamePrize</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "游戏奖品表")
public class GamePrize extends GenericEntity
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
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

