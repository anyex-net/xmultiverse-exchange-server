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
 * 钱包资产打赏礼物记录 分页请求对象
 * <p>File：ReqWalletAssetTipGift.java</p>
 * <p>Title: ReqWalletAssetTipGift</p>
 * <p>Description:ReqWalletAssetTipGift</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "钱包资产打赏礼物记录分页请求对象")
public class ReqWalletAssetTipGiftPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**来源账户ID*/
	@ApiModelProperty(value = "来源账户ID")
	private java.lang.Long fromAccountId;

	/**去处账户ID*/
	@ApiModelProperty(value = "去处账户ID")
	private java.lang.Long toAccountId;

	/**币种(法币、BTC、ETH、USD)*/
	@ApiModelProperty(value = "币种(法币、BTC、ETH、USD)")
	private java.lang.String currency;

	/**交易编号*/
	@ApiModelProperty(value = "交易编号")
	private java.lang.String trxNo;

	/**金额*/
	@ApiModelProperty(value = "金额")
	private java.math.BigDecimal trxBalance;

	/**手续费*/
	@ApiModelProperty(value = "手续费")
	private java.math.BigDecimal trxFee;

	/**状态(0无效、1有效)*/
	@ApiModelProperty(value = "状态(0无效、1有效)")
	private java.lang.Boolean status;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
}

