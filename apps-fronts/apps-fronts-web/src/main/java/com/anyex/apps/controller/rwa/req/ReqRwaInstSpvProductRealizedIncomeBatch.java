package com.anyex.apps.controller.rwa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.util.List;


@Data
@ApiModel(description = "批量插入RWA机构SPV产品实际收入请求对象")
public class ReqRwaInstSpvProductRealizedIncomeBatch {

    @ApiModelProperty(value = "批量请求实体", required = true)
    private List<ReqRwaInstSpvProductRealizedIncome> batchRequests;
}
