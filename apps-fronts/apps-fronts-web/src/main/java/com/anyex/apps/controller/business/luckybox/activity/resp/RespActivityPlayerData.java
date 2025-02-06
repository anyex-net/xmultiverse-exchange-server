/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity.resp;

import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "活动玩家数据")
public class RespActivityPlayerData implements Serializable
{
	/**玩家账户浏览数量*/
	@ApiModelProperty(value = "玩家账户浏览数量")
	private Long browseNum;

	/**玩家账户参与数量*/
	@ApiModelProperty(value = "玩家账户参与数量")
	private Long participationAccountNum;

	/**玩家中奖订单列表*/
	@ApiModelProperty(value = "玩家中奖订单列表")
	private List<Order4Activity> listWinningOrder4Activity;
}

