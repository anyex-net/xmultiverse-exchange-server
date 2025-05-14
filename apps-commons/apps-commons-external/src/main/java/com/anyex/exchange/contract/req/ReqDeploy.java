package com.anyex.exchange.contract.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReqDeploy implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "代币全称不可为空")
    @ApiModelProperty(value = "代币全称", position = 1, required = true)
    private String token_name;

    @NotNull(message = "代币缩写不可为空")
    @ApiModelProperty(value = "代币缩写", position = 2)
    private String token_symbol;

    @NotNull(message = "总量（带18位精度）不可为空")
    @ApiModelProperty(value = "总量（带18位精度）", position = 3)
    private String total_supply;

//    @NotNull(message = "支付代币地址(USDT)不可为空")
    @ApiModelProperty(value = "支付代币地址(USDT)", position = 4)
    private String payment_token;
}
