/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 充值地址 实体请求对象
 * <p>File：ReqDepositAddress.java</p>
 * <p>Title: ReqDepositAddress</p>
 * <p>Description:ReqDepositAddress</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "充值地址请求对象")
public class ReqDepositAddress implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@NotNull(message = "用户ID不可为空")
//	@ApiModelProperty(value = "用户ID", position = 1, required = true)
//	private Long userId;

	/**币种(BTC、ETH、USDT)*/
	@NotEmpty(message = "币种(BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2, required = true)
	private String currency;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@NotEmpty(message = "区块链(BTC、ETH、TRON、BSC)不可为空")
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3, required = true)
	private String blockchain;

//	/**用户充值地址*/
//	@NotEmpty(message = "用户充值地址不可为空")
//	@ApiModelProperty(value = "用户充值地址", position = 4, required = true)
//	private String depositAddress;
//
//	/**累计充值(包含未确认)*/
//	@NotNull(message = "累计充值(包含未确认)不可为空")
//	@ApiModelProperty(value = "累计充值(包含未确认)", position = 5, required = true)
//	private java.math.BigDecimal accDeposit;
//
//	/**未确认累计充值*/
//	@NotNull(message = "未确认累计充值不可为空")
//	@ApiModelProperty(value = "未确认累计充值", position = 6, required = true)
//	private java.math.BigDecimal unconfAccDeposit;
//
//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 9)
//	private String remark;

//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 10, required = true)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 11)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 12)
//	private java.lang.Long updateTime;
}

