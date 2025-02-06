package com.anyex.apps.controller.asset.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "资产调整对象")
public class ReqWalletAdjust
{
    /**账户ID*/
    @NotNull
    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    /**币种(法币、BTC、ETH、USDT)*/
    /*@ApiModelProperty(value = "币种(法币、BTC、ETH、USDT)")
    private String currency;*/

    @NotNull
    @ApiModelProperty(value = "调整方向 1增加 -1减少")
    private Integer direction;

    /**调整金额*/
    @NotNull
    @Min(1)
    @Max(100000000)
    @ApiModelProperty(value = "调整金额")
    private java.math.BigDecimal amount;

    /**凭证附件URL*/
    @ApiModelProperty(value = "凭证附件URL")
    private java.lang.String attachment;

    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
}