package com.anyex.apps.controller.asset.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "钱包资产转账记录分页请求对象")
public class ReqWalletAssetTransactionsPagination extends Pagination
{
    @ApiModelProperty(value = "ID")
    private Long id;

    /**账户ID*/
    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    /**币种(法币、BTC、ETH、USD)*/
    @ApiModelProperty(value = "币种(法币、BTC、ETH、USD)")
    private String currency;

    /**交易类型(充值deposit、提现withDraw)*/
    @ApiModelProperty(value = "交易类型(充值deposit、提现withDraw)")
    private String trxType;

    /**转账金额*/
    @ApiModelProperty(value = "转账金额")
    private java.math.BigDecimal trxAmount;

    /**转账时间*/
    @ApiModelProperty(value = "转账时间")
    private Long trxTime;

    /**转账编号*/
    @ApiModelProperty(value = "转账编号")
    private String trxNo;

    /**转账状态(成功success、处理中pending、失败failed)*/
    @ApiModelProperty(value = "转账状态(成功success、处理中pending、失败failed)")
    private String trxStatus;

    /**转账描述*/
    @ApiModelProperty(value = "转账描述")
    private String trxDesc;

    /**转账渠道*/
    @ApiModelProperty(value = "转账渠道")
    private String trxChannel;

    /**账户类型(BANK、WALLET)*/
    @ApiModelProperty(value = "账户类型(BANK、WALLET)")
    private String trxAccountType;

    /**收款账号(手机号码)*/
    @ApiModelProperty(value = "收款账号(手机号码)")
    private String trxAccountNo;

    /**收款姓名*/
    @ApiModelProperty(value = "收款姓名")
    private String trxAccountName;

    /**银行名字*/
    @ApiModelProperty(value = "银行名字")
    private String trxBankName;

    /**国际银行账户号码(InternationalBankAccountNumber)*/
    @ApiModelProperty(value = "国际银行账户号码(InternationalBankAccountNumber)")
    private String trxIban;

    /**身份证号码*/
    @ApiModelProperty(value = "身份证号码")
    private String trxCnic;

    /**邮箱*/
    @ApiModelProperty(value = "邮箱")
    private String trxEmail;

    /**手机号码*/
    @ApiModelProperty(value = "手机号码")
    private String trxMobile;
}