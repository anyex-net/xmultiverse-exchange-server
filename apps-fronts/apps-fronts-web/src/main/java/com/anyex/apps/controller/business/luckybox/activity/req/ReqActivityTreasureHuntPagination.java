package com.anyex.apps.controller.business.luckybox.activity.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "活动一元夺宝分页请求对象")
public class ReqActivityTreasureHuntPagination extends Pagination
{
}