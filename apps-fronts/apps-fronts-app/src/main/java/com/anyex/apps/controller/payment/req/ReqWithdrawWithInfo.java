package com.anyex.apps.controller.payment.req;

import com.anyex.apps.consts.GlobalConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 带信息提现请求对象
 */
@Data
public class ReqWithdrawWithInfo {

    /*@ApiModelProperty(value = "支付通道",required = true)
    @NotNull(message = "支付通道不能为空")
    private String trxChannel = GlobalConst.PAYMENT_CHANNEL_GLOBALPAY;*/

    @ApiModelProperty(value = "提现金额",required = true)
    @NotNull(message = "提现金额不能为空")
    @Min(value = 1,message = "金额不能小于1")
    @Max(value = 99999999,message = "金额不能大于99999999")
    private Long amount;

    /**账户类型(BANK、WALLET)*/
    @NotEmpty(message = "账户类型(BANK、WALLET)不可为空")
    @ApiModelProperty(value = "账户类型(BANK、WALLET)", required = true)
    @Length(min=4, max=32,message = "账户类型长度4-32")
    private String accountType;

    /**收款账号(手机号码)*/
    @NotEmpty(message = "收款账号(手机号码)不可为空")
    @ApiModelProperty(value = "收款账号(手机号码)", required = true)
    @Length(min=4, max=32,message = "收款账号长度4-32")
    private String accountNo;

    /**收款姓名*/
    @NotEmpty(message = "收款姓名不可为空")
    @ApiModelProperty(value = "收款姓名", required = true)
    @Length(min=4, max=32,message = "收款姓名长度4-32")
    private String accountName;

    /**银行名字*/
    @NotEmpty(message = "银行名字不可为空")
    @ApiModelProperty(value = "银行名字", required = true)
    @Length(min=4, max=32,message = "银行名字长度4-32")
    private String bankName;

    /**国际银行账户号码(InternationalBankAccountNumber)*/
    @NotEmpty(message = "国际银行账户号码(InternationalBankAccountNumber)不可为空")
    @ApiModelProperty(value = "国际银行账户号码(InternationalBankAccountNumber)", required = true)
    @Length(min=4, max=30,message = "银行卡号长度4-30")
    private String iban;

    /**身份证号码*/
    @NotEmpty(message = "身份证号码不可为空")
    @ApiModelProperty(value = "身份证号码", required = true)
    @Length(min = 8,max = 32,message = "身份证长度3-32")
    @Digits(integer = 32,fraction = 0,message = "身份证必须为数字")
    private String cnic;

    /**邮箱*/
    @NotEmpty(message = "邮箱不可为空")
    @ApiModelProperty(value = "邮箱", required = true)
    @Length(min=8, max=32,message = "邮箱长度8-32")
    private String email;

    /**手机号码*/
    @NotEmpty(message = "手机号码不可为空")
    @ApiModelProperty(value = "手机号码", required = true)
    @Length(min = 9,max = 9,message = "长度必须是9位")
    @Digits(integer = 9,fraction = 0,message = "必须为9位整数")
    private String mobile;

}
