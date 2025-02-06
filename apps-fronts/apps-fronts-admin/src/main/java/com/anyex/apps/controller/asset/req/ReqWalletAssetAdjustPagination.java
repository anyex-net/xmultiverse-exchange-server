/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.asset.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 钱包资产调整记录表 分页请求对象
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
@ApiModel(description = "钱包资产调整记录表分页请求对象")
public class ReqWalletAssetAdjustPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@ApiModelProperty(value = "账户ID")
	private Long accountId;

	/**币种(法币、BTC、ETH、USD)*/
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)")
	private String currency;

	/**调整交易编号*/
	@ApiModelProperty(value = "调整交易编号")
	private String adjustTrxNo;

	/**调整类型(强增assetAdjustAdd、强减assetAdjustSub)*/
	@ApiModelProperty(value = "调整类型(强增assetAdjustAdd、强减assetAdjustSub)")
	private String adjustType;

	/**调整金额*/
	@ApiModelProperty(value = "调整金额")
	private java.math.BigDecimal adjustBalance;

	/**状态(0无效、1有效)*/
	@ApiModelProperty(value = "状态(0无效、1有效)")
	private Boolean status;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;


}

