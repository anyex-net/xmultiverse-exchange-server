/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户返佣记录 实体对象
 * <p>File：UserRebate.java</p>
 * <p>Title: UserRebate</p>
 * <p>Description:UserRebate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户返佣记录")
public class UserRebate extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**邀请人用户ID（若系统为默认邀请人，可设为0或特定值）*/
	@NotNull(message = "邀请人用户ID（若系统为默认邀请人，可设为0或特定值）不可为空")
	@ApiModelProperty(value = "邀请人用户ID（若系统为默认邀请人，可设为0或特定值）", position = 1, required = true)
	private Long inviterId;

	/**被邀请人用户ID（必须唯一，不能重复被邀请）*/
	@NotNull(message = "被邀请人用户ID（必须唯一，不能重复被邀请）不可为空")
	@ApiModelProperty(value = "被邀请人用户ID（必须唯一，不能重复被邀请）", position = 2, required = true)
	private Long inviteeId;

	/**关联交易ID（如订单ID）*/
	@NotNull(message = "关联交易ID（如订单ID）不可为空")
	@ApiModelProperty(value = "关联交易ID（如订单ID）", position = 3, required = true)
	private Long tradeId;

	/**交易金额*/
	@NotNull(message = "交易金额不可为空")
	@ApiModelProperty(value = "交易金额", position = 4, required = true)
	private java.math.BigDecimal tradeAmount;

	/**手续费金额（真实产生）*/
	@NotNull(message = "手续费金额（真实产生）不可为空")
	@ApiModelProperty(value = "手续费金额（真实产生）", position = 5, required = true)
	private java.math.BigDecimal feeAmount;

	/**返佣比例（如 0.2 表示20%）*/
	@NotNull(message = "返佣比例（如 0.2 表示20%）不可为空")
	@ApiModelProperty(value = "返佣比例（如 0.2 表示20%）", position = 6, required = true)
	private java.math.BigDecimal rebateRate;

	/**实际返佣金额（fee_amount × rebate_rate）*/
	@NotNull(message = "实际返佣金额（fee_amount × rebate_rate）不可为空")
	@ApiModelProperty(value = "实际返佣金额（fee_amount × rebate_rate）", position = 7, required = true)
	private java.math.BigDecimal rebateAmount;

	/**状态（如 pending, settled, canceled）*/
	@NotEmpty(message = "状态（如 pending, settled, canceled）不可为空")
	@ApiModelProperty(value = "状态（如 pending, settled, canceled）", position = 8, required = true)
	private String status;

	/**结算日期（可为空，直到结算时写入）*/
	@NotNull(message = "结算日期（可为空，直到结算时写入）不可为空")
	@ApiModelProperty(value = "结算日期（可为空，直到结算时写入）", position = 9, required = true)
	private java.util.Date settleDate;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 10, required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 11)
	private Long updateTime;
}

