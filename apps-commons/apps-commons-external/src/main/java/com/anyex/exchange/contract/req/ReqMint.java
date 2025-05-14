package com.anyex.exchange.contract.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReqMint implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "接收地址不可为空")
    @ApiModelProperty(value = "接收地址", position = 1, required = true)
    private String recipient_address;

    @NotNull(message = "项目代币合约地址不可为空")
    @ApiModelProperty(value = "项目代币合约地址", position = 1, required = true)
    private String contract_address;

    @NotNull(message = "项目代币数量不可为空")
    @ApiModelProperty(value = "项目代币数量", position = 1, required = true)
    private BigDecimal amount;
}
