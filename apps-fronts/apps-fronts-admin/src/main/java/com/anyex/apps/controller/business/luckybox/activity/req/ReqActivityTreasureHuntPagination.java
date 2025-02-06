package com.anyex.apps.controller.business.luckybox.activity.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动一元夺宝分页请求对象")
public class ReqActivityTreasureHuntPagination extends Pagination
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

    /**夺宝价格(金额)*/
    @ApiModelProperty(value = "夺宝价格(金额)")
    private java.math.BigDecimal treasurePrice;

    /**夺宝一轮总份数*/
    @ApiModelProperty(value = "夺宝一轮总份数")
    private java.lang.Integer treasureSumNum;

    /**夺宝一轮机器人份数*/
    @ApiModelProperty(value = "夺宝一轮机器人份数")
    private java.lang.Integer treasureRobotNum;

    /**夺宝总轮数*/
    @ApiModelProperty(value = "夺宝总轮数")
    private java.lang.Integer treasureSumRound;

    /**夺宝开始时间*/
    @ApiModelProperty(value = "夺宝开始时间")
    private java.lang.Long treasureStartTime;

    /**夺宝结束时间*/
    @ApiModelProperty(value = "夺宝结束时间")
    private java.lang.Long treasureEndTime;

    /**夺宝活动是否启用(0未启用、1启用)*/
    @ApiModelProperty(value = "夺宝活动是否启用(0未启用、1启用)")
    private java.lang.Boolean status;

    /**夺宝当前轮数*/
    @ApiModelProperty(value = "夺宝当前轮数")
    private java.lang.Integer actCurrentRound;

    /**夺宝当前轮已购买份数*/
    @ApiModelProperty(value = "夺宝当前轮已购买份数")
    private java.lang.Integer actCurrentPurchasedNum;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}