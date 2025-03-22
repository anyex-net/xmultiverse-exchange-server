/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提现历史 分页请求对象
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
@ApiModel(description = "提现历史分页请求对象")
public class ReqWithdrawalHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;

	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2)
	private String currency;

	/**区块链(BTC、ETH、TRON、BSC)*/
	@ApiModelProperty(value = "区块链(BTC、ETH、TRON、BSC)", position = 3)
	private String blockchain;

	/**提地址*/
	@ApiModelProperty(value = "提地址", position = 4)
	private String fromAddress;

	/**收地址*/
	@ApiModelProperty(value = "收地址", position = 5)
	private String toAddress;

//	/**提现数量*/
//	@ApiModelProperty(value = "提现数量", position = 6)
//	private java.math.BigDecimal amount;
//
//	/**提现手续费*/
//	@ApiModelProperty(value = "提现手续费", position = 7)
//	private java.math.BigDecimal fee;

	/**交易ID*/
	@ApiModelProperty(value = "交易ID", position = 8)
	private String transId;

	/**提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)*/
	@ApiModelProperty(value = "提现状态(canceled已撤销、applied已申请、checked已复核、exported已汇出)", position = 9)
	private String state;

//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 10)
//	private String remark;
//
//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 11)
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

