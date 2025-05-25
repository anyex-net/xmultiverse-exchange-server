/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户邀请返佣奖励配置 分页请求对象
 * <p>File：ReqUserInviteRewardConfig.java</p>
 * <p>Title: ReqUserInviteRewardConfig</p>
 * <p>Description:ReqUserInviteRewardConfig</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户邀请返佣奖励配置分页请求对象")
public class ReqUserInviteRewardConfigPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**币种(BTC、ETH、USDT)*/
	@ApiModelProperty(value = "币种(BTC、ETH、USDT)", position = 1)
	private java.lang.String currency;

	/**交易手续费累积1(起)*/
	@ApiModelProperty(value = "交易手续费累积1(起)", position = 2)
	private java.math.BigDecimal tradeFeeSum1;

	/**交易手续费累积2(终)*/
	@ApiModelProperty(value = "交易手续费累积2(终)", position = 3)
	private java.math.BigDecimal tradeFeeSum2;

	/**奖励等级*/
	@ApiModelProperty(value = "奖励等级", position = 4)
	private java.lang.String rewardLevel;

	/**奖励折扣(交易手续费)*/
	@ApiModelProperty(value = "奖励折扣(交易手续费)", position = 5)
	private java.math.BigDecimal rewardDiscount;

	/**状态(0不可用、1可用)*/
	@ApiModelProperty(value = "状态(0不可用、1可用)", position = 6)
	private java.lang.Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 7)
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 8)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 9)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 10)
	private java.lang.Long updateTime;


}

