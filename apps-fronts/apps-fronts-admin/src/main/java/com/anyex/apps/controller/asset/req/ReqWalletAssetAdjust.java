/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钱包资产调整记录表 实体请求对象
 * <p>File：ReqWalletAssetAdjust.java</p>
 * <p>Title: ReqWalletAssetAdjust</p>
 * <p>Description:ReqWalletAssetAdjust</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产调整记录表请求对象")
public class ReqWalletAssetAdjust extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private Long accountId;

	/**币种(法币、BTC、ETH、USD)*/
	@NotEmpty(message = "币种(法币、BTC、ETH、USD)不可为空")
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)", required = true)
	private String currency;

	/**调整交易编号*/
	@NotEmpty(message = "调整交易编号不可为空")
	@ApiModelProperty(value = "调整交易编号", required = true)
	private String adjustTrxNo;

	/**调整类型(强增assetAdjustAdd、强减assetAdjustSub)*/
	@NotEmpty(message = "调整类型(强增assetAdjustAdd、强减assetAdjustSub)不可为空")
	@ApiModelProperty(value = "调整类型(强增assetAdjustAdd、强减assetAdjustSub)", required = true)
	private String adjustType;

	/**调整金额*/
	@NotNull(message = "调整金额不可为空")
	@ApiModelProperty(value = "调整金额", required = true)
	private java.math.BigDecimal adjustBalance;

	/**凭证附件URL*/
	@ApiModelProperty(value = "凭证附件URL")
	private String attachment;

	/**状态(0无效、1有效)*/
	@NotNull(message = "状态(0无效、1有效)不可为空")
	@ApiModelProperty(value = "状态(0无效、1有效)", required = true)
	private Boolean status;

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


}

