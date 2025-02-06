/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 账户收款银行表 实体对象
 * <p>File：AccountReceivingBank.java</p>
 * <p>Title: AccountReceivingBank</p>
 * <p>Description:AccountReceivingBank</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户收款银行表")
public class AccountReceivingBank extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private Long accountId;

	/**账户类型(BANK、WALLET)*/
	@NotEmpty(message = "账户类型(BANK、WALLET)不可为空")
	@ApiModelProperty(value = "账户类型(BANK、WALLET)", required = true)
	private String accountType;

	/**收款账号(手机号码)*/
	@NotEmpty(message = "收款账号(手机号码)不可为空")
	@ApiModelProperty(value = "收款账号(手机号码)", required = true)
	private String accountNo;

	/**收款姓名*/
	@NotEmpty(message = "收款姓名不可为空")
	@ApiModelProperty(value = "收款姓名", required = true)
	private String accountName;

	/**银行名字*/
	@NotEmpty(message = "银行名字不可为空")
	@ApiModelProperty(value = "银行名字", required = true)
	private String bankName;

	/**国际银行账户号码(InternationalBankAccountNumber)*/
	@NotEmpty(message = "国际银行账户号码(InternationalBankAccountNumber)不可为空")
	@ApiModelProperty(value = "国际银行账户号码(InternationalBankAccountNumber)", required = true)
	private String iban;

	/**身份证号码*/
	@NotEmpty(message = "身份证号码不可为空")
	@ApiModelProperty(value = "身份证号码", required = true)
	private String cnic;

	/**邮箱*/
	@NotEmpty(message = "邮箱不可为空")
	@ApiModelProperty(value = "邮箱", required = true)
	private String email;

	/**手机号码*/
	@NotEmpty(message = "手机号码不可为空")
	@ApiModelProperty(value = "手机号码", required = true)
	private String mobile;

	@NotNull(message = "状态不可为空")
	@ApiModelProperty(value = "状态(0未验证、1验证成功、2验证失败)", required = true)
	private Integer status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/////////////////////////////////
	/**账户实际类型(BANK、EASYPAISA、JAZZCASH)*/
	@ApiModelProperty(value = "账户实际类型(BANK、EASYPAISA、JAZZCASH)")
	private String accountActualType;
}

