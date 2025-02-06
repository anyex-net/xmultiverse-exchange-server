/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 钱包资产转账记录表 实体对象
 * <p>File：WalletAssetTransactions.java</p>
 * <p>Title: WalletAssetTransactions</p>
 * <p>Description:WalletAssetTransactions</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产转账记录表")
public class WalletAssetTransactions extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private Long accountId;

	/**币种(法币、BTC、ETH、USD)*/
	@NotNull(message = "币种(法币、BTC、ETH、USD)不可为空")
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)", required = true)
	private String currency;

	/**交易类型(充值deposit、提现withDraw)*/
	@NotNull(message = "交易类型(充值deposit、提现withDraw)不可为空")
	@ApiModelProperty(value = "交易类型(充值deposit、提现withDraw)", required = true)
	private String trxType;

	/**转账金额*/
	@NotNull(message = "转账金额不可为空")
	@ApiModelProperty(value = "转账金额", required = true)
	private java.math.BigDecimal trxAmount;

	@NotNull(message = "实际转账金额不可为空")
	@ApiModelProperty(value = "实际转账金额", required = true)
	private java.math.BigDecimal trxActAmount;

	@NotNull(message = "用户手续费不可为空")
	@ApiModelProperty(value = "用户手续费", required = true)
	private java.math.BigDecimal trxFee;

	/**转账时间*/
	@NotNull(message = "转账时间不可为空")
	@ApiModelProperty(value = "转账时间", required = true)
	private Long trxTime;

	/**转账编号*/
	@NotNull(message = "转账编号不可为空")
	@ApiModelProperty(value = "转账编号", required = true)
	private String trxNo;

	/**转账状态(成功success、处理中pending、失败failed)*/
	@NotNull(message = "转账状态(成功success、处理中pending、失败failed)不可为空")
	@ApiModelProperty(value = "转账状态(成功success、处理中pending、失败failed)", required = true)
	private String trxStatus;

	/**转账描述*/
	@NotNull(message = "转账描述不可为空")
	@ApiModelProperty(value = "转账描述", required = true)
	private String trxDesc;

	@ApiModelProperty(value = "转账查询描述")
	private String queryDesc;

	/**转账渠道*/
	@NotNull(message = "转账渠道不可为空")
	@ApiModelProperty(value = "转账渠道", required = true)
	private String trxChannel;

	/**账户类型(BANK、WALLET)*/
	@NotNull(message = "账户类型(BANK、WALLET)不可为空")
	@ApiModelProperty(value = "账户类型(BANK、WALLET)", required = true)
	private String trxAccountType;

	/**收款账号(手机号码)*/
	@NotNull(message = "收款账号(手机号码)不可为空")
	@ApiModelProperty(value = "收款账号(手机号码)", required = true)
	private String trxAccountNo;

	/**收款姓名*/
	@NotNull(message = "收款姓名不可为空")
	@ApiModelProperty(value = "收款姓名", required = true)
	private String trxAccountName;

	/**银行名字*/
	@ApiModelProperty(value = "银行名字")
	private String trxBankName;

	/**国际银行账户号码(InternationalBankAccountNumber)*/
	@ApiModelProperty(value = "国际银行账户号码(InternationalBankAccountNumber)")
	private String trxIban;

	/**身份证号码*/
	@NotNull(message = "身份证号码不可为空")
	@ApiModelProperty(value = "身份证号码", required = true)
	private String trxCnic;

	/**邮箱*/
	@NotNull(message = "邮箱不可为空")
	@ApiModelProperty(value = "邮箱", required = true)
	private String trxEmail;

	/**手机号码*/
	@NotNull(message = "手机号码不可为空")
	@ApiModelProperty(value = "手机号码", required = true)
	private String trxMobile;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "平台支付单号")
	private String platTrxNo;


}

