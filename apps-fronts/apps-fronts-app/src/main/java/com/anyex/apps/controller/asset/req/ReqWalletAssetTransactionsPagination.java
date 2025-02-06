package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "钱包资产转账记录分页请求对象")
public class ReqWalletAssetTransactionsPagination extends Pagination
{
    /**交易类型(充值deposit、提现withDraw)*/
    @NotNull(message = "交易类型(充值deposit、提现withDraw)不可为空")
    @ApiModelProperty(value = "交易类型(充值deposit、提现withDraw)", required = true)
    private String trxType;
}