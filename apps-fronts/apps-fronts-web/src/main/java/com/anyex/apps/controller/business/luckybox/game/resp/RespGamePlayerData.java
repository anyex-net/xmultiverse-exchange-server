/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game.resp;

import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "游戏玩家数据")
public class RespGamePlayerData implements Serializable
{
	/**游戏玩家账户数量*/
	@ApiModelProperty(value = "游戏玩家账户数量")
	private Long accountNum;

	/**游戏玩家中奖账户数量*/
	@ApiModelProperty(value = "游戏玩家中奖账户数量")
	private Long isWinningAccountNum;

	/**游戏玩家中奖订单列表*/
	@ApiModelProperty(value = "游戏玩家中奖订单列表")
	private List<Order4Game> listWinningOrder4Game;
}

