/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 活动半价购买表 实体对象
 * <p>File：ActivityHotDeals.java</p>
 * <p>Title: ActivityHotDeals</p>
 * <p>Description:ActivityHotDeals</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "活动半价购买表")
public class ActivityHotDeals extends GenericEntity
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

	/**活动价格(金额)*/
	@NotNull(message = "活动价格(金额)不可为空")
	@ApiModelProperty(value = "活动价格(金额)", required = true)
	private java.math.BigDecimal activityPrice;

	/**活动尾款金额*/
	@NotNull(message = "活动尾款金额不可为空")
	@ApiModelProperty(value = "活动尾款金额", required = true)
	private java.math.BigDecimal balancePayment;

	/**活动一轮总份数*/
	@NotNull(message = "活动一轮总份数不可为空")
	@ApiModelProperty(value = "活动一轮总份数", required = true)
	private Integer activitySumNum;

	/**活动一轮机器人份数*/
	@NotNull(message = "活动一轮机器人份数不可为空")
	@ApiModelProperty(value = "活动一轮机器人份数", required = true)
	private Integer activityRobotNum;

	/**活动总轮数*/
	@NotNull(message = "活动总轮数不可为空")
	@ApiModelProperty(value = "活动总轮数", required = true)
	private Long activitySumRound;

	/**活动总库存*/
	@NotNull(message = "活动总库存不可为空")
	@ApiModelProperty(value = "活动总库存", required = true)
	private Long activitySumStock;

	/**活动开始时间*/
	@NotNull(message = "活动开始时间不可为空")
	@ApiModelProperty(value = "活动开始时间", required = true)
	private Long activityStartTime;

	/**活动结束时间*/
	@NotNull(message = "活动结束时间不可为空")
	@ApiModelProperty(value = "活动结束时间", required = true)
	private Long activityEndTime;

	/**活动是否启用(0未启用、1启用)*/
	@NotNull(message = "活动是否启用(0未启用、1启用)不可为空")
	@ApiModelProperty(value = "活动是否启用(0未启用、1启用)", required = true)
	private Boolean status;

	/**活动当前轮数*/
	@NotNull(message = "活动当前轮数不可为空")
	@ApiModelProperty(value = "活动当前轮数", required = true)
	private Long actCurrentRound;

	/**活动当前轮已购买份数*/
	@NotNull(message = "活动当前轮已购买份数不可为空")
	@ApiModelProperty(value = "活动当前轮已购买份数", required = true)
	private Integer actCurrentPurchasedNum;

	/**夺宝当前轮已参加账户数*/
	@NotNull(message = "夺宝当前轮已参加账户数不可为空")
	@ApiModelProperty(value = "夺宝当前轮已参加账户数", required = true)
	private Integer actCurrentAccountNum;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/////////////////////////////////
	/**本账户夺宝当前轮已购买份数*/
	@ApiModelProperty(value = "本账户夺宝当前轮已购买份数")
	private Integer accountCurrentPurchasedNum;

	/**商品标题*/
	@ApiModelProperty(value = "商品标题")
	private java.lang.String title;

	/**商品副标题*/
	@ApiModelProperty(value = "商品副标题")
	private java.lang.String subTitle;

	/**商品卖点*/
	@ApiModelProperty(value = "商品卖点")
	private java.lang.String sellingPoint;

	/**商品图标图片*/
	@ApiModelProperty(value = "商品图标图片")
	private java.lang.String iconImage;

	/**商品主图图片*/
	@ApiModelProperty(value = "商品主图图片(JSON)")
	private java.lang.String mainImages;

	/**商品详情图片*/
	@ApiModelProperty(value = "商品详情图片(JSON)")
	private java.lang.String detailImages;

	/**价格*/
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal price;

	/**库存数量*/
	@ApiModelProperty(value = "库存数量")
	private java.lang.Long stock;

	/**参数*/
	@ApiModelProperty(value = "参数(JSON)")
	private java.lang.String param;

	/**商品描述*/
	@ApiModelProperty(value = "商品描述")
	private java.lang.String description;
}

