/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA账户交易历史 分页请求对象
 * <p>File：ReqRwaBalancesTransHistory.java</p>
 * <p>Title: ReqRwaBalancesTransHistory</p>
 * <p>Description:ReqRwaBalancesTransHistory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA账户交易历史分页请求对象")
public class ReqRwaBalancesTransHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 2)
	private java.lang.String currency;

	/**类型(转入、转出、冻结、解冻、申购、分红)*/
	@ApiModelProperty(value = "类型(转入、转出、冻结、解冻、申购、分红)", position = 3)
	private java.lang.String type;

	/**前余额*/
	@ApiModelProperty(value = "前余额", position = 4)
	private java.math.BigDecimal beforeBal;

	/**发生数量*/
	@ApiModelProperty(value = "发生数量", position = 5)
	private java.math.BigDecimal changeAmt;

	/**后余额*/
	@ApiModelProperty(value = "后余额", position = 6)
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
	@ApiModelProperty(value = "状态(成功success、处理中pending、失败failed)", position = 10)
	private java.lang.String state;

	/**交易描述*/
	@ApiModelProperty(value = "交易描述", position = 11)
	private java.lang.String transDesc;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 12)
	private java.lang.String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 13)
//	private java.lang.Long createTime;
}

