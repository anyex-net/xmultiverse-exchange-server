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
 * 用户返佣记录 分页请求对象
 * <p>File：ReqUserRebate.java</p>
 * <p>Title: ReqUserRebate</p>
 * <p>Description:ReqUserRebate</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户返佣记录分页请求对象")
public class ReqUserRebatePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**邀请人用户ID（若系统为默认邀请人，可设为0或特定值）*/
	@ApiModelProperty(value = "邀请人用户ID（若系统为默认邀请人，可设为0或特定值）", position = 1)
	private Long inviterId;

	/**被邀请人用户ID（必须唯一，不能重复被邀请）*/
	@ApiModelProperty(value = "被邀请人用户ID（必须唯一，不能重复被邀请）", position = 2)
	private Long inviteeId;

	/**关联交易ID（如订单ID）*/
	@ApiModelProperty(value = "关联交易ID（如订单ID）", position = 3)
	private Long tradeId;

//	/**交易金额*/
//	@ApiModelProperty(value = "交易金额", position = 4)
//	private java.math.BigDecimal tradeAmount;
//
//	/**手续费金额（真实产生）*/
//	@ApiModelProperty(value = "手续费金额（真实产生）", position = 5)
//	private java.math.BigDecimal feeAmount;
//
//	/**返佣比例（如 0.2 表示20%）*/
//	@ApiModelProperty(value = "返佣比例（如 0.2 表示20%）", position = 6)
//	private java.math.BigDecimal rebateRate;
//
//	/**实际返佣金额（fee_amount × rebate_rate）*/
//	@ApiModelProperty(value = "实际返佣金额（fee_amount × rebate_rate）", position = 7)
//	private java.math.BigDecimal rebateAmount;

	/**状态（如 pending, settled, canceled）*/
	@ApiModelProperty(value = "状态（如 pending, settled, canceled）", position = 8)
	private String status;

//	/**结算日期（可为空，直到结算时写入）*/
//	@ApiModelProperty(value = "结算日期（可为空，直到结算时写入）", position = 9)
//	private java.util.Date settleDate;
//
//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 10)
//	private Long createTime;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 11)
//	private Long updateTime;


}

