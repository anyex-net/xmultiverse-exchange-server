package com.anyex.apps.controller.business.luckybox.game.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "游戏奖品请求对象")
public class ReqGamePrizePagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**游戏ID*/
    @ApiModelProperty(value = "游戏ID")
    private java.lang.Long gameId;

    /**奖品名称*/
    @ApiModelProperty(value = "奖品名称")
    private java.lang.String prizeName;

    /**奖品图片Url*/
    @ApiModelProperty(value = "奖品图片Url")
    private java.lang.String prizeImgUrl;

    /**商品价格*/
    @ApiModelProperty(value = "商品价格")
    private java.math.BigDecimal price;

    /**成本*/
    @ApiModelProperty(value = "成本")
    private java.math.BigDecimal cost;

    /**百份中奖数量*/
    @ApiModelProperty(value = "百份中奖数量")
    private java.lang.Integer percentWinningAmount;

    /**奖励金额*/
    @ApiModelProperty(value = "奖励金额")
    private java.lang.Integer rewardBalance;

    /**游戏是否启用(0未启用、1启用)*/
    @ApiModelProperty(value = "游戏是否启用(0未启用、1启用)")
    private java.lang.Boolean status;

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