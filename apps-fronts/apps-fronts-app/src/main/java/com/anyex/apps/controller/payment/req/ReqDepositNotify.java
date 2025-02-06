package com.anyex.apps.controller.payment.req;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 支付回调请求对象
 */
@Data
public class ReqDepositNotify {

    /**
     * tradeNo string YES Merchant system unique order number
     * payAmount string YES 实际的⽀付⾦额
     * payStatus string YES 1- 代表⽀付成功 0-失败
     */

    @JSONField(name = "payStatus")
    @ApiModelProperty(value = "状态",required = true)
    @NotNull(message = "状态不能为空")
    private String payStatus;

    @ApiModelProperty(value = "订单号",required = true)
    @NotNull(message = "订单号不能为空")
    private String tradeNo;

    @ApiModelProperty(value = "实际的⽀付⾦额",required = true)
    @NotNull(message = "实际的⽀付⾦额不能为空")
    private String payAmount;



}
