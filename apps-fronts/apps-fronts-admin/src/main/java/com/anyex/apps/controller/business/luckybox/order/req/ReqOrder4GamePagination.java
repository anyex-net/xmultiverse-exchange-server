package com.anyex.apps.controller.business.luckybox.order.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "游戏订单记录分页请求对象")
public class ReqOrder4GamePagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**订单编号*/
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderTxNo;

    /**游戏ID*/
    @ApiModelProperty(value = "游戏ID")
    private java.lang.Long gameId;

    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private java.lang.Long accountId;

    /**游戏中奖奖品ID*/
    @ApiModelProperty(value = "游戏中奖奖品ID")
    private java.lang.Long gamePrizeId;

    /**游戏中奖奖品名称*/
    @ApiModelProperty(value = "游戏中奖奖品名称")
    private java.lang.String gamePrizeName;

    /**游戏中奖奖励金额*/
    @ApiModelProperty(value = "游戏中奖奖励金额")
    private java.lang.Integer gameRewardBalance;

    /**订单状态(0未中奖、1已中奖)*/
    @ApiModelProperty(value = "订单状态(0未中奖、1已中奖)")
    private java.lang.Boolean orderStatus;

    /**订单是否中奖(0未中奖、1已中奖)*/
    @ApiModelProperty(value = "订单是否中奖(0未中奖、1已中奖)")
    private java.lang.Boolean isWinning;

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