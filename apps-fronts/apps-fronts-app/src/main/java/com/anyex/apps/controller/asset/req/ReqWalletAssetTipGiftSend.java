package com.anyex.apps.controller.asset.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "送礼物")
public class ReqWalletAssetTipGiftSend
{

    @NotEmpty(message = "用户userId不可为空")
    @ApiModelProperty(value = "用户userId", required = true)
    private java.lang.String userId;

    @NotNull(message = "金额不可为空")
    @ApiModelProperty(value = "金额", required = true)
    private java.math.BigDecimal trxBalance;

    @NotEmpty(message = "礼物编号不可为空")
    @ApiModelProperty(value = "礼物编号", required = true)
    private java.lang.String giftNo;

}