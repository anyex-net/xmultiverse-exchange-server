package com.anyex.apps.controller.business.luckybox.activity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "参加活动一元夺宝请求对象")
public class ReqAttendActivityTreasureHunt implements Serializable
{
    /**商品活动ID*/
    @NotNull(message = "商品活动ID不可为空")
    @ApiModelProperty(value = "商品活动ID", required = true)
    private java.lang.Long activityId;

    /**活动购买份数*/
    @NotNull(message = "活动购买份数不可为空")
    @ApiModelProperty(value = "活动购买份数", required = true)
    private java.lang.Integer activityPurchaseNum;
}
