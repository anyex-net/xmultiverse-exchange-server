package com.anyex.apps.controller.business.luckybox.game.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "游戏信息请求对象")
public class ReqGamePagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**游戏名称*/
    @ApiModelProperty(value = "游戏名称")
    private java.lang.String name;

    /**游戏图片url*/
    @ApiModelProperty(value = "游戏图片url")
    private java.lang.String gameImgUrl;

    /**游戏参与筹码(金额)*/
    @ApiModelProperty(value = "游戏参与筹码(金额)")
    private java.math.BigDecimal gameChips;

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