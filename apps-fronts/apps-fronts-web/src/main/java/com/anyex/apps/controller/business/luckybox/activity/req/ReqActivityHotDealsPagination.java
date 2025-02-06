package com.anyex.apps.controller.business.luckybox.activity.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "活动半价购买分页请求对象")
public class ReqActivityHotDealsPagination extends Pagination
{
}