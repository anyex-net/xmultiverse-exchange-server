package com.anyex.apps.controller.business.luckybox.order.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "活动订单记录分页请求对象")
public class ReqOrder4ActivityPagination extends Pagination
{
    /**商品活动类型(TreasureHunt、HotDeals等)*/
    @ApiModelProperty(value = "商品活动类型(TreasureHunt、HotDeals等)")
    private String activityType;

    /**订单是否中奖(0未中奖、1已中奖)*/
    @ApiModelProperty(value = "订单是否中奖(0未中奖、1已中奖)")
    private Boolean isWinning;
}