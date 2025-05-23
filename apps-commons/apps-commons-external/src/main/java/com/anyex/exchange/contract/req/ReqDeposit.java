package com.anyex.exchange.contract.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReqDeposit implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "分红合约地址不可为空")
    @ApiModelProperty(value = "分红合约地址", position = 1, required = true)
    private String contract_address;

    //    @NotNull(message = "支付代币地址(USDT)不可为空")
    @ApiModelProperty(value = "支付代币地址(USDT)", position = 2)
    private String payment_token;

    @NotNull(message = "存入金额不可为空")
    @ApiModelProperty(value = "存入金额", position = 3, required = true)
    private Integer deposited_total;
}
