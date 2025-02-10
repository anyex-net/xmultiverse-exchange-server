/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 充值交易历史 分页请求对象
 * <p>File：ReqDepositTransHistory.java</p>
 * <p>Title: ReqDepositTransHistory</p>
 * <p>Description:ReqDepositTransHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "充值交易历史分页请求对象")
public class ReqDepositTransHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2)
	private java.lang.String currency;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3)
	private java.lang.String blockchain;

	/**用户充值地址*/
	@ApiModelProperty(value = "用户充值地址", position = 4)
	private java.lang.String depositAddress;

	/**交易ID*/
	@ApiModelProperty(value = "交易ID", position = 5)
	private java.lang.String transId;

	/**充值数量*/
	@ApiModelProperty(value = "充值数量", position = 6)
	private java.math.BigDecimal amount;

	/**网络手续费*/
	@ApiModelProperty(value = "网络手续费", position = 7)
	private java.math.BigDecimal netFee;

	/**确认状态(unconfirm未确认、confirmed已确认)*/
	@ApiModelProperty(value = "确认状态(unconfirm未确认、confirmed已确认)", position = 8)
	private java.lang.String confirmState;

	/**充值入账状态(undeposit未入账、deposited已入账)*/
	@ApiModelProperty(value = "充值入账状态(undeposit未入账、deposited已入账)", position = 9)
	private java.lang.String depositState;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 11)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 12)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 13)
	private java.lang.Long updateTime;
}

