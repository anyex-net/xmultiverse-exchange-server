package com.anyex.apps.controller.payment.req;

import com.anyex.apps.consts.GlobalConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 提现请求对象
 */
@Data
public class ReqWithdraw {

    /*@ApiModelProperty(value = "支付通道",required = true)
    @NotNull(message = "支付通道不能为空")
    private String trxChannel = GlobalConst.PAYMENT_CHANNEL_GLOBALPAY;*/

    @ApiModelProperty(value = "提现金额",required = true)
    @Min(value = 1,message = "金额不能小于1")
    @Max(value = 99999999,message = "金额不能大于99999999")
    @NotNull(message = "提现金额不能为空")
    private Long amount;

    @ApiModelProperty(value = "银行卡id",required = true)
    @NotNull(message = "银行卡id不能为空")
    private Long bankRecordId;

}
