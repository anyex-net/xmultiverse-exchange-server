package com.anyex.apps.controller.business.luckybox.order.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "活动订单记录分页请求对象")
public class ReqOrder4ActivityPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**订单编号*/
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderTxNo;

    /**商品活动类型(TreasureHunt、HotDeals等)*/
    @ApiModelProperty(value = "商品活动类型(TreasureHunt、HotDeals等)")
    private java.lang.String activityType;

    /**商品活动ID*/
    @ApiModelProperty(value = "商品活动ID")
    private java.lang.Long activityId;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private java.lang.Long skuId;

    /**产品ID*/
    @ApiModelProperty(value = "产品ID")
    private java.lang.Long spuId;

    /**商品活动原价*/
    @ApiModelProperty(value = "商品活动原价")
    private BigDecimal activitySkuPrice;

    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private java.lang.Long accountId;

    /**订单活动价格(金额)*/
    @ApiModelProperty(value = "订单活动价格(金额)")
    private java.math.BigDecimal orderActPrice;

    /**订单活动尾款金额*/
    @ApiModelProperty(value = "订单活动尾款金额")
    private java.math.BigDecimal orderActBalancePayment;

    /**订单活动购买份数*/
    @ApiModelProperty(value = "订单活动购买份数")
    private java.lang.Integer orderActPurchaseNum;

    /**订单总金额*/
    @ApiModelProperty(value = "订单总金额")
    private java.math.BigDecimal orderSumBalance;

    /**订单状态(0未开奖、1未中奖、2已中奖无需支付尾款、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已支付尾款但缺货等值现金充抵、10不支付尾款直接抵扣返现)*/
    @ApiModelProperty(value = "订单状态(0未开奖、1未中奖、2已中奖无需支付尾款、3已中奖待支付尾款、4已支付尾款待发货、5已发货待收货、6已收货完成、9已支付尾款但缺货等值现金充抵、10不支付尾款直接抵扣返现)")
    private java.lang.Integer orderStatus;

    /**订单支付尾款钱包资产余额扣减金额(已支付尾款但缺货等值现金充抵情况下)*/
    @ApiModelProperty(value = "订单支付尾款钱包资产余额扣减金额(已支付尾款但缺货等值现金充抵情况下)")
    private java.math.BigDecimal balanceDeductionAmount;

    /**订单支付实际金额*/
    @ApiModelProperty(value = "订单支付实际金额")
    private java.math.BigDecimal paymentActualAmount;

    /**订单支付时间*/
    @ApiModelProperty(value = "订单支付时间")
    private java.lang.Long paymentTime;

    /**订单支付编号*/
    @ApiModelProperty(value = "订单支付编号")
    private java.lang.String paymentNo;

    /**订单支付状态(-1不用支付、0未支付、1已支付)*/
    @ApiModelProperty(value = "订单支付状态(-1不用支付、0未支付、1已支付)")
    private java.lang.Integer paymentStatus;

    /**订单支付描述*/
    @ApiModelProperty(value = "订单支付描述")
    private java.lang.String paymentDesc;

    /**活动一轮总份数*/
    @ApiModelProperty(value = "活动一轮总份数")
    private java.lang.Integer activitySumNum;

    /**活动总轮数*/
    @ApiModelProperty(value = "活动总轮数")
    private java.lang.Long activitySumRound;

    /**活动当前轮数*/
    @ApiModelProperty(value = "活动当前轮数")
    private java.lang.Long activityCurrentRound;

    /**订单是否开奖(0未开奖、1已开奖)*/
    @ApiModelProperty(value = "订单是否开奖(0未开奖、1已开奖)")
    private java.lang.Boolean isLotteryDrawn;

    /**订单是否中奖(0未中奖、1已中奖)*/
    @ApiModelProperty(value = "订单是否中奖(0未中奖、1已中奖)")
    private java.lang.Boolean isWinning;

    /**订单是否已领中奖(0未领、1已领)*/
    @ApiModelProperty(value = "订单是否已领中奖(0未领、1已领)")
    private java.lang.Boolean isClaimLottery;

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