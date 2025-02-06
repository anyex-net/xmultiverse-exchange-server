package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产分页请求对象")
public class ReqWalletAssetPagination extends Pagination
{
    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private java.lang.Long accountId;

    /**币种(法币、BTC、ETH、USDT)*/
    @ApiModelProperty(value = "币种(法币、BTC、ETH、USDT)")
    private java.lang.String currency;

    /**余额*/
    @ApiModelProperty(value = "余额")
    private java.math.BigDecimal balance;

    /**冻结(不可用)*/
    @ApiModelProperty(value = "冻结(不可用)")
    private java.math.BigDecimal frozenBal;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private java.lang.Long updateTime;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}