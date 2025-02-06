/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 钱包资产流水表 实体对象
 * <p>File：WalletAssetFlows.java</p>
 * <p>Title: WalletAssetFlows</p>
 * <p>Description:WalletAssetFlows</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产流水表")
public class WalletAssetFlows extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private java.lang.Long accountId;

	/**币种(法币、BTC、ETH、USD)*/
	@NotNull(message = "币种(法币、BTC、ETH、USD)不可为空")
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)", required = true)
	private java.lang.String currency;

	/**业务分类(收入revenue、支出expend等)*/
	@NotNull(message = "业务分类(收入revenue、支出expend等)不可为空")
	@ApiModelProperty(value = "业务分类(收入revenue、支出expend等)", required = true)
	private java.lang.String businessCategory;

	/**业务类型(充值deposit、提现withDraw等)*/
	@NotNull(message = "业务类型(充值deposit、提现withDraw等)不可为空")
	@ApiModelProperty(value = "业务类型(充值deposit、提现withDraw等)", required = true)
	private java.lang.String businessType;

	/**变动前余额*/
	@NotNull(message = "变动前余额不可为空")
	@ApiModelProperty(value = "变动前余额", required = true)
	private java.math.BigDecimal beforeBalance;

	/**变动发生额*/
	@NotNull(message = "变动发生额不可为空")
	@ApiModelProperty(value = "变动发生额", required = true)
	private java.math.BigDecimal balance;

	@NotNull(message = "手续费不可为空")
	@ApiModelProperty(value = "手续费", required = true)
	private java.math.BigDecimal fee;

	@NotNull(message = "变动方向不可为空")
	@ApiModelProperty(value = "变动方向",required = true)
	private java.lang.String direction;

	/**变动后余额*/
	@NotNull(message = "变动后余额不可为空")
	@ApiModelProperty(value = "变动后余额", required = true)
	private java.math.BigDecimal afterBalance;

	/**原业务Id*/
	@NotNull(message = "原业务Id不可为空")
	@ApiModelProperty(value = "原业务Id", required = true)
	private java.lang.Long orgBusinessId;

	/**原业务No*/
	@NotNull(message = "原业务No不可为空")
	@ApiModelProperty(value = "原业务No", required = true)
	private java.lang.String orgBusinessNo;

	/**状态(0无效、1有效)*/
	@NotNull(message = "状态(0无效、1有效)不可为空")
	@ApiModelProperty(value = "状态(0无效、1有效)", required = true)
	private java.lang.Boolean status;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
}
