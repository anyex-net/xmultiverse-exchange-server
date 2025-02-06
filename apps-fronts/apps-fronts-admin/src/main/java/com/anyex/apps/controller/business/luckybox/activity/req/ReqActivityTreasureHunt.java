/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.activity.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "活动一元夺宝")
public class ReqActivityTreasureHunt extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**活动名称*/
	@NotNull(message = "活动名称不可为空")
	@ApiModelProperty(value = "活动名称", required = true)
	private String activityName;

	/**商品ID*/
	@NotNull(message = "商品ID不可为空")
	@ApiModelProperty(value = "商品ID", required = true)
	private Long skuId;

	/**产品ID*/
	@NotNull(message = "产品ID不可为空")
	@ApiModelProperty(value = "产品ID", required = true)
	private Long spuId;

	/**夺宝价格(金额)*/
	@NotNull(message = "夺宝价格(金额)不可为空")
	@ApiModelProperty(value = "夺宝价格(金额)", required = true)
	private java.math.BigDecimal treasurePrice;

	/**夺宝一轮总份数*/
	@NotNull(message = "夺宝一轮总份数不可为空")
	@ApiModelProperty(value = "夺宝一轮总份数", required = true)
	private Integer treasureSumNum;

	/**夺宝一轮机器人份数*/
	@NotNull(message = "夺宝一轮机器人份数不可为空")
	@ApiModelProperty(value = "夺宝一轮机器人份数", required = true)
	private Integer treasureRobotNum;

	/**夺宝总轮数*/
	@NotNull(message = "夺宝总轮数不可为空")
	@ApiModelProperty(value = "夺宝总轮数", required = true)
	private Long treasureSumRound;

	/**夺宝开始时间*/
	@NotNull(message = "夺宝开始时间不可为空")
	@ApiModelProperty(value = "夺宝开始时间", required = true)
	private Long treasureStartTime;

	/**夺宝结束时间*/
	@NotNull(message = "夺宝结束时间不可为空")
	@ApiModelProperty(value = "夺宝结束时间", required = true)
	private Long treasureEndTime;

	/**夺宝活动是否启用(0未启用、1启用)*/
	@NotNull(message = "夺宝活动是否启用(0未启用、1启用)不可为空")
	@ApiModelProperty(value = "夺宝活动是否启用(0未启用、1启用)", required = true)
	private Boolean status;

	/**夺宝当前轮数*/
	@NotNull(message = "夺宝当前轮数不可为空")
	@ApiModelProperty(value = "夺宝当前轮数", required = true)
	private Long actCurrentRound;

	/**夺宝当前轮已购买份数*/
	@NotNull(message = "夺宝当前轮已购买份数不可为空")
	@ApiModelProperty(value = "夺宝当前轮已购买份数", required = true)
	private Integer actCurrentPurchasedNum;

	@ApiModelProperty(value = "夺宝当前轮已参加账户数")
	private Integer actCurrentAccountNum;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
}

