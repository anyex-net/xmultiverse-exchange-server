package com.anyex.apps.controller.business.luckybox.activity.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动半价购买分页请求对象")
public class ReqActivityHotDealsPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**活动名称*/
    @ApiModelProperty(value = "活动名称")
    private java.lang.String activityName;

    /**商品ID*/
    @ApiModelProperty(value = "商品ID")
    private java.lang.Long skuId;

    /**产品ID*/
    @ApiModelProperty(value = "产品ID")
    private java.lang.Long spuId;

    /**活动价格(金额)*/
    @ApiModelProperty(value = "活动价格(金额)")
    private java.math.BigDecimal activityPrice;

    /**活动尾款金额*/
    @ApiModelProperty(value = "活动尾款金额")
    private java.math.BigDecimal balancePayment;

    /**活动一轮总份数*/
    @ApiModelProperty(value = "活动一轮总份数")
    private java.lang.Integer activitySumNum;

    /**活动一轮机器人份数*/
    @ApiModelProperty(value = "活动一轮机器人份数")
    private java.lang.Integer activityRobotNum;

    /**活动总轮数*/
    @ApiModelProperty(value = "活动总轮数")
    private java.lang.Long activitySumRound;

    /**活动总库存*/
    @ApiModelProperty(value = "活动总库存")
    private java.lang.Long activitySumStock;

    /**活动开始时间*/
    @ApiModelProperty(value = "活动开始时间")
    private java.lang.Long activityStartTime;

    /**活动结束时间*/
    @ApiModelProperty(value = "活动结束时间")
    private java.lang.Long activityEndTime;

    /**活动是否启用(0未启用、1启用)*/
    @ApiModelProperty(value = "活动是否启用(0未启用、1启用)")
    private java.lang.Boolean status;

    /**活动当前轮数*/
    @ApiModelProperty(value = "活动当前轮数")
    private java.lang.Integer actCurrentRound;

    /**活动当前轮已购买份数*/
    @ApiModelProperty(value = "活动当前轮已购买份数")
    private java.lang.Integer actCurrentPurchasedNum;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}