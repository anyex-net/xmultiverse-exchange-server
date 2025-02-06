package com.anyex.apps.controller.business.luckybox.order.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "游戏订单记录分页请求对象")
public class ReqOrder4GamePagination extends Pagination
{
}