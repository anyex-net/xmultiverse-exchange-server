/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 活动订单记录表 实体对象
 * <p>File：Order4Activity.java</p>
 * <p>Title: Order4Activity</p>
 * <p>Description:Order4Activity</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "活动订单记录表")
public class Order4Activity extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**订单编号*/
	@NotNull(message = "订单编号不可为空")
	@ApiModelProperty(value = "订单编号", required = true)
	private java.lang.String orderTxNo;

	/**商品活动类型(TreasureHunt、HotDeals等)*/
	@NotNull(message = "商品活动类型(TreasureHunt、HotDeals等)不可为空")
	@ApiModelProperty(value = "商品活动类型(TreasureHunt、HotDeals等)", required = true)
	private java.lang.String activityType;

	/**商品活动ID*/
	@NotNull(message = "商品活动ID不可为空")
	@ApiModelProperty(value = "商品活动ID", required = true)
	private java.lang.Long activityId;

	/**商品ID*/
	@NotNull(message = "商品ID不可为空")
	@ApiModelProperty(value = "商品ID", required = true)
	private java.lang.Long skuId;

	/**产品ID*/
	@NotNull(message = "产品ID不可为空")
	@ApiModelProperty(value = "产品ID", required = true)
	private java.lang.Long spuId;

	/**商品活动原价*/
	@NotNull(message = "商品活动原价不可为空")
	@ApiModelProperty(value = "商品活动原价", required = true)
	private BigDecimal activitySkuPrice;

	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private java.lang.Long accountId;

	/**订单活动价格(金额)*/
	@NotNull(message = "订单活动价格(金额)不可为空")
	@ApiModelProperty(value = "订单活动价格(金额)", required = true)
	private java.math.BigDecimal orderActPrice;

	/**订单活动尾款金额*/
	@NotNull(message = "订单活动尾款金额不可为空")
	@ApiModelProperty(value = "订单活动尾款金额", required = true)
	private java.math.BigDecimal orderActBalancePayment;

	/**订单活动购买份数*/
	@NotNull(message = "订单活动购买份数不可为空")
	@ApiModelProperty(value = "订单活动购买份数", required = true)
	private java.lang.Integer orderActPurchaseNum;

	/**订单总金额*/
	@NotNull(message = "订单总金额不可为空")
	@ApiModelProperty(value = "订单总金额", required = true)
	private java.math.BigDecimal orderSumBalance;

	/**订单状态(0未开奖、1未中奖、2已中奖无需支付尾款待发货、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已缺货等值金额充抵、10不支付尾款直接抵扣返现)*/
	@NotNull(message = "订单状态(0未开奖、1未中奖、2已中奖无需支付尾款待发货、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已缺货等值金额充抵、10不支付尾款直接抵扣返现)不可为空")
	@ApiModelProperty(value = "订单状态(0未开奖、1未中奖、2已中奖无需支付尾款待发货、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已缺货等值金额充抵、10不支付尾款直接抵扣返现)", required = true)
	private java.lang.Integer orderStatus;

	/**订单支付尾款钱包资产余额扣减金额(已支付尾款但缺货等值现金充抵情况下)*/
	@ApiModelProperty(value = "订单支付尾款钱包资产余额扣减金额(已支付尾款但缺货等值现金充抵情况下)")
	private java.math.BigDecimal balanceDeductionAmount;

	/**订单支付实际金额*/
	@JsonIgnore
	@ApiModelProperty(value = "订单支付实际金额")
	private java.math.BigDecimal paymentActualAmount;

	/**订单支付时间*/
    @JsonIgnore
	@ApiModelProperty(value = "订单支付时间")
	private java.lang.Long paymentTime;

	/**订单支付编号*/
    @JsonIgnore
	@ApiModelProperty(value = "订单支付编号")
	private java.lang.String paymentNo;

	/**订单支付状态(-1不用支付、0未支付、1已支付)*/
    @JsonIgnore
	@ApiModelProperty(value = "订单支付状态(-1不用支付、0未支付、1已支付)")
	private java.lang.Integer paymentStatus;

	/**订单支付描述*/
    @JsonIgnore
	@ApiModelProperty(value = "订单支付描述")
	private java.lang.String paymentDesc;

	/**活动一轮总份数*/
	@NotNull(message = "活动一轮总份数不可为空")
	@ApiModelProperty(value = "活动一轮总份数", required = true)
	private java.lang.Integer activitySumNum;

	/**活动总轮数*/
	@NotNull(message = "活动总轮数不可为空")
	@ApiModelProperty(value = "活动总轮数", required = true)
	private java.lang.Long activitySumRound;

	/**活动当前轮数*/
	@NotNull(message = "活动当前轮数不可为空")
	@ApiModelProperty(value = "活动当前轮数", required = true)
	private java.lang.Long activityCurrentRound;

	/**订单是否开奖(0未开奖、1已开奖)*/
	@NotNull(message = "订单是否开奖(0未开奖、1已开奖)不可为空")
	@ApiModelProperty(value = "订单是否开奖(0未开奖、1已开奖)", required = true)
	private java.lang.Boolean isLotteryDrawn;

	/**订单是否中奖(0未中奖、1已中奖)*/
	@NotNull(message = "订单是否中奖(0未中奖、1已中奖)不可为空")
	@ApiModelProperty(value = "订单是否中奖(0未中奖、1已中奖)", required = true)
	private java.lang.Boolean isWinning;

	/**订单是否已领中奖(0未领、1已领)*/
	@NotNull(message = "订单是否已领中奖(0未领、1已领)不可为空")
	@ApiModelProperty(value = "订单是否已领中奖(0未领、1已领)", required = true)
	private java.lang.Boolean isClaimLottery;

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

	/**账户邮箱*/
	@ApiModelProperty(value = "账户邮箱")
	private java.lang.String email;

	/**账户昵称*/
	@ApiModelProperty(value = "账户昵称")
	private java.lang.String accountName;

	/**账户头像URL*/
	@ApiModelProperty(value = "账户头像URL")
	private java.lang.String headUrl;

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

	/**本轮中奖者账户邮箱*/
	@ApiModelProperty(value = "本轮中奖者账户邮箱")
	private java.lang.String winningAccountEmail;
}

