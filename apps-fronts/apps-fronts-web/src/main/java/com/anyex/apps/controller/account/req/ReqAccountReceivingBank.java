/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(description = "账户收款银行请求对象")
public class ReqAccountReceivingBank extends GenericEntity
{
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

//	@NotNull(message = "状态不可为空")
//	@ApiModelProperty(value = "状态(0未验证、1验证成功、2验证失败)", required = true)
//	private Integer status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
}

