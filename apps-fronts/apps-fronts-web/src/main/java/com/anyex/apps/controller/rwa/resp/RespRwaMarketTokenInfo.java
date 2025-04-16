package com.anyex.apps.controller.rwa.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "RWA市场产品代币信息")
public class RespRwaMarketTokenInfo {

    /**代币名称*/
    @NotEmpty(message = "代币名称不可为空")
    @ApiModelProperty(value = "代币名称", position = 5, required = true)
    private java.lang.String tokenName;

    /**代币发行数量*/
    @NotNull(message = "代币发行数量不可为空")
    @ApiModelProperty(value = "代币发行数量", position = 7, required = true)
    private java.math.BigDecimal tokenIssueNumber;

    @ApiModelProperty(value = "持有人数", position = 7, required = true)
    private java.lang.Integer holderCount;

    @ApiModelProperty(value = "已分成金额", position = 7, required = true)
    private java.math.BigDecimal distributedAmount;
}
