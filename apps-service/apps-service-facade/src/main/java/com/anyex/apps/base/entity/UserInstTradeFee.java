/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户交易手续费费率 实体对象
 * <p>File：UserInstTradeFee.java</p>
 * <p>Title: UserInstTradeFee</p>
 * <p>Description:UserInstTradeFee</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户交易手续费费率")
public class UserInstTradeFee extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private java.lang.Long userId;

	/**产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION*/
	@NotEmpty(message = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION不可为空")
	@ApiModelProperty(value = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION", position = 2, required = true)
	private java.lang.String instType;

	/**产品Id 如BTC-USD-SWAP*/
	@NotEmpty(message = "产品Id 如BTC-USD-SWAP不可为空")
	@ApiModelProperty(value = "产品Id 如BTC-USD-SWAP", position = 3, required = true)
	private java.lang.String instId;

	/**标的指数 仅适用于instType为交割/永续/期权 如BTC-USD*/
	@ApiModelProperty(value = "标的指数 仅适用于instType为交割/永续/期权 如BTC-USD", position = 4)
	private java.lang.String uly;

	/**手续费等级*/
	@NotEmpty(message = "手续费等级不可为空")
	@ApiModelProperty(value = "手续费等级", position = 5, required = true)
	private java.lang.String level;

	/**吃单手续费率 永续和交割合约时 为币本位U本位合约费率*/
	@NotNull(message = "吃单手续费率 永续和交割合约时 为币本位U本位合约费率不可为空")
	@ApiModelProperty(value = "吃单手续费率 永续和交割合约时 为币本位U本位合约费率", position = 6, required = true)
	private java.math.BigDecimal taker;

	/**挂单手续费率 永续和交割合约时 为币本位U本位合约费率*/
	@NotNull(message = "挂单手续费率 永续和交割合约时 为币本位U本位合约费率不可为空")
	@ApiModelProperty(value = "挂单手续费率 永续和交割合约时 为币本位U本位合约费率", position = 7, required = true)
	private java.math.BigDecimal maker;

	/**交割手续费率*/
	@NotNull(message = "交割手续费率不可为空")
	@ApiModelProperty(value = "交割手续费率", position = 8, required = true)
	private java.math.BigDecimal delivery;

	/**行权手续费率*/
	@NotNull(message = "行权手续费率不可为空")
	@ApiModelProperty(value = "行权手续费率", position = 9, required = true)
	private java.math.BigDecimal exercise;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 11, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 12)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 13)
	private java.lang.Long updateTime;
}

