/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA账户余额 实体请求对象
 * <p>File：ReqRwaBalances.java</p>
 * <p>Title: ReqRwaBalances</p>
 * <p>Description:ReqRwaBalances</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA账户余额请求对象")
public class ReqRwaBalances extends GenericEntity
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

	/**余额*/
	@NotNull(message = "余额不可为空")
	@ApiModelProperty(value = "余额", position = 3, required = true)
	private java.math.BigDecimal balance;

	/**冻结(不可用)*/
	@NotNull(message = "冻结(不可用)不可为空")
	@ApiModelProperty(value = "冻结(不可用)", position = 4, required = true)
	private java.math.BigDecimal frozenBal;

	/**可用余额*/
	@NotNull(message = "可用余额不可为空")
	@ApiModelProperty(value = "可用余额", position = 5, required = true)
	private java.math.BigDecimal availBal;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 6)
	private java.lang.String remark;

//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 7)
//	private java.lang.Long updateTime;
}

