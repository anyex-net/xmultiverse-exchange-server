package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产流水分页请求对象")
public class ReqWalletAssetFlowsPagination extends Pagination
{
    /**业务分类(收入revenue、支出expend等)*/
    @ApiModelProperty(value = "业务分类(收入revenue、支出expend等)")
    private String businessCategory;

    /**业务类型(充值deposit、提现withDraw等)*/
    @ApiModelProperty(value = "业务类型(充值deposit、提现withDraw等)")
    private String businessType;
}