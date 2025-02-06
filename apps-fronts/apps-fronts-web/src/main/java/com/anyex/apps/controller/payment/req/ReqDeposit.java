package com.anyex.apps.controller.payment.req;

import com.anyex.apps.consts.GlobalConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 充值拉起支付请求对象
 */
@Data
public class ReqDeposit {

    /*@ApiModelProperty(value = "支付通道",required = true)
    @NotNull(message = "支付通道不能为空")
    private String trxChannel = GlobalConst.PAYMENT_CHANNEL_WIVPAY;*/

    @ApiModelProperty(value = "充值金额",required = true)
    @NotNull(message = "充值金额不能为空")
    @DecimalMin(value = "0.01",message = "最小值为0.01" )
    private Double amount;

    @ApiModelProperty(value = "钱包类型(esaypaisa,jazzcash)",required = true)
    @NotNull(message = "钱包类型不能为空")
    private String walletType;

    @ApiModelProperty(value = "手机号",required = true)
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "身份证",required = true)
    @NotNull(message = "身份证不能为空")
    private String cnic;

 /*   @ApiModelProperty(value = "email",required = true)
    @NotNull(message = "email is not null")
    private String email;

    @ApiModelProperty(value = "realname",required = true)
    @NotNull(message = "realname is not null")
    private String realname;

    @ApiModelProperty(value = "accountType",required = true)
    @NotNull(message = "accountType is not null")
    private String accountType;*/


}
