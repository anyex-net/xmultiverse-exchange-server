package com.anyex.apps.pay.model;

import com.anyex.apps.consts.GlobalConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 提现账户资金情况
 */
@Data
@AllArgsConstructor
public class RespWithdrawAsset {

    @ApiModelProperty(value = "账户id")
    private Long accountId;

    @ApiModelProperty(value = "币种")
    private String currency = GlobalConst.CURRENCY_PKR;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "冻结余额")
    private BigDecimal frozenBalance;

    @ApiModelProperty(value = "可用余额")
    private BigDecimal enableBalance;

    @ApiModelProperty(value = "我的最大可提金额")
    private BigDecimal maxCanWithdrawAmount;

    @ApiModelProperty(value = "我的最小可提金额")
    private BigDecimal minCanWithdrawAmount;

    @ApiModelProperty(value = "我是否可以提现")
    private Boolean canWithdraw = true;

    @ApiModelProperty(value = "系统最大可提金额")
    private BigDecimal sysMaxCanWithdrawAmount;

    @ApiModelProperty(value = "系统最小可提金额")
    private BigDecimal sysMinCanWithdrawAmount;

    @ApiModelProperty(value = "提现手续费率")
    private BigDecimal feeRate;

}
