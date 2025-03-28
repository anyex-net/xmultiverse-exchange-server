/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 资金账户余额 实体请求对象
 * <p>File：ReqBalances.java</p>
 * <p>Title: ReqBalances</p>
 * <p>Description:ReqBalances</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "资金账户余额请求对象")
public class ReqBalances implements Serializable
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;

	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2)
	private String currency;

	/**余额*/
	@ApiModelProperty(value = "余额", position = 3)
	private java.math.BigDecimal balance;

	/**冻结(不可用)*/
	@ApiModelProperty(value = "冻结(不可用)", position = 4)
	private java.math.BigDecimal frozenBal;

	/**可用余额*/
	@ApiModelProperty(value = "可用余额", position = 5)
	private java.math.BigDecimal availBal;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 6)
	private String remark;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 7)
	private java.lang.Long updateTime;
}

