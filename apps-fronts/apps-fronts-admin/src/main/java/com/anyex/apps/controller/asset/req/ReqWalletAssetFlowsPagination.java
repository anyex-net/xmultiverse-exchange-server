package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产流水分页请求对象")
public class ReqWalletAssetFlowsPagination extends Pagination
{
    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private java.lang.Long accountId;

    /**币种(法币、BTC、ETH、USD)*/
    @ApiModelProperty(value = "币种(法币、BTC、ETH、USD)")
    private java.lang.String currency;

    /**业务分类(收入revenue、支出expend等)*/
    @ApiModelProperty(value = "业务分类(收入revenue、支出expend等)")
    private java.lang.String businessCategory;

    /**业务类型(充值deposit、提现withDraw等)*/
    @ApiModelProperty(value = "业务类型(充值deposit、提现withDraw等)")
    private java.lang.String businessType;

    /**变动前余额*/
    @ApiModelProperty(value = "变动前余额")
    private java.math.BigDecimal beforeBalance;

    /**变动发生额*/
    @ApiModelProperty(value = "变动发生额")
    private java.math.BigDecimal balance;

    @ApiModelProperty(value = "变动方向")
    private java.lang.String direction;

    /**变动后余额*/
    @ApiModelProperty(value = "变动后余额")
    private java.math.BigDecimal afterBalance;

    /**关联业务ID*/
    @ApiModelProperty(value = "关联业务ID")
    private java.lang.Long businessId;

    /**状态(0无效、1有效)*/
    @ApiModelProperty(value = "状态(0无效、1有效)")
    private java.lang.Boolean status;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private java.lang.Long createTime;

    /**更新时间*/
    @ApiModelProperty(value = "更新时间")
    private java.lang.Long updateTime;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}