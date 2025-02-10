/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资金账户交易历史 实体对象
 * <p>File：BalancesTransHistory.java</p>
 * <p>Title: BalancesTransHistory</p>
 * <p>Description:BalancesTransHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资金账户交易历史")
public class BalancesTransHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private java.lang.Long userId;

	/**币种(BTC、ETH、USDT)*/
	@NotEmpty(message = "币种(BTC、ETH、USDT)不可为空")
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2, required = true)
	private java.lang.String currency;

	/**类型(充值、提现、冻结、解冻、转入、转出)*/
	@NotEmpty(message = "类型(充值、提现、冻结、解冻、转入、转出)不可为空")
	@ApiModelProperty(value = "类型(充值、提现、冻结、解冻、转入、转出)", position = 3, required = true)
	private java.lang.String type;

	/**前余额*/
	@NotNull(message = "前余额不可为空")
	@ApiModelProperty(value = "前余额", position = 4, required = true)
	private java.math.BigDecimal beforeBal;

	/**发生数量*/
	@NotNull(message = "发生数量不可为空")
	@ApiModelProperty(value = "发生数量", position = 5, required = true)
	private java.math.BigDecimal changeAmt;

	/**后余额*/
	@NotNull(message = "后余额不可为空")
	@ApiModelProperty(value = "后余额", position = 6, required = true)
	private java.math.BigDecimal afterBal;

	/**原业务ID*/
	@ApiModelProperty(value = "原业务ID", position = 7)
	private java.lang.String businessId;

	/**转出账户*/
	@ApiModelProperty(value = "转出账户", position = 8)
	private java.lang.String fromAcct;

	/**转入账户*/
	@ApiModelProperty(value = "转入账户", position = 9)
	private java.lang.String toAcct;

	/**状态(成功success、处理中pending、失败failed)*/
	@NotEmpty(message = "状态(成功success、处理中pending、失败failed)不可为空")
	@ApiModelProperty(value = "状态(成功success、处理中pending、失败failed)", position = 10, required = true)
	private java.lang.String state;

	/**交易描述*/
	@NotEmpty(message = "交易描述不可为空")
	@ApiModelProperty(value = "交易描述", position = 11, required = true)
	private java.lang.String transDesc;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 12)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 13, required = true)
	private java.lang.Long createTime;
}

