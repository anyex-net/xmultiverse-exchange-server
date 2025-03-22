/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 提现历史 实体请求对象
 * <p>File：ReqWithdrawalHistory.java</p>
 * <p>Title: ReqWithdrawalHistory</p>
 * <p>Description:ReqWithdrawalHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "提现历史请求对象")
public class ReqWithdrawalHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**币种(BTC、ETH、USDT)*/
	@NotEmpty(message = "币种(BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2, required = true)
	private String currency;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@NotEmpty(message = "区块链(BTC、ETH、TRON、BSC)不可为空")
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3, required = true)
	private String blockchain;

	/**提地址*/
	@NotEmpty(message = "提地址不可为空")
	@ApiModelProperty(value = "提地址", position = 4, required = true)
	private String fromAddress;

	/**收地址*/
	@NotEmpty(message = "收地址不可为空")
	@ApiModelProperty(value = "收地址", position = 5, required = true)
	private String toAddress;

	/**提现数量*/
	@NotNull(message = "提现数量不可为空")
	@ApiModelProperty(value = "提现数量", position = 6, required = true)
	private java.math.BigDecimal amount;

	/**提现手续费*/
	@NotNull(message = "提现手续费不可为空")
	@ApiModelProperty(value = "提现手续费", position = 7, required = true)
	private java.math.BigDecimal fee;

	/**交易ID*/
	@ApiModelProperty(value = "交易ID", position = 8)
	private String transId;

	/**提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)*/
	@NotEmpty(message = "提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)不可为空")
	@ApiModelProperty(value = "提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)", position = 9, required = true)
	private String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private String remark;

//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 11, required = true)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 12)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 13)
//	private java.lang.Long updateTime;
//
//	/**复核人*/
//	@ApiModelProperty(value = "复核人", position = 14)
//	private java.lang.String checkBy;
//
//	/**复核时间*/
//	@ApiModelProperty(value = "复核时间", position = 15)
//	private java.lang.Long checkTime;
}

