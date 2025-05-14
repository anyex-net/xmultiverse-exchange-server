package com.anyex.exchange.contract.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReqDividend implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "分红金额不可为空")
    @ApiModelProperty(value = "分红金额", position = 1, required = true)
    private BigDecimal amount;

    @NotNull(message = "分红合约地址不可为空")
    @ApiModelProperty(value = "分红合约地址", position = 1, required = true)
    private String contract_address;

    @NotNull(message = "项目代币合约地址不可为空")
    @ApiModelProperty(value = "项目代币合约地址", position = 1, required = true)
    private String project_address;
}
