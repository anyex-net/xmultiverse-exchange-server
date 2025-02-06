/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户邀请奖励表 分页请求对象
 * <p>File：ReqAccountInviteRewards.java</p>
 * <p>Title: ReqAccountInviteRewards</p>
 * <p>Description:ReqAccountInviteRewards</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户邀请奖励表分页请求对象")
public class ReqAccountInviteRewardsPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**注册账户ID*/
	@ApiModelProperty(value = "注册账户ID")
	private Long registerAccountId;

	/**注册账户邮箱*/
	@ApiModelProperty(value = "注册账户邮箱")
	private String registerEmail;

	/**邀请账户ID*/
	@ApiModelProperty(value = "邀请账户ID")
	private Long inviteAccountId;

	/**邀请账户邮箱*/
	@ApiModelProperty(value = "邀请账户邮箱")
	private String inviteEmail;

	/**奖励账户ID*/
	@ApiModelProperty(value = "奖励账户ID")
	private Long rewardsAccountId;

	/**奖励账户邮箱*/
	@ApiModelProperty(value = "奖励账户邮箱")
	private String rewardsEmail;

	/**奖励账户对应直接下级账户ID(带来收益账户)*/
	@ApiModelProperty(value = "奖励账户对应直接下级账户ID(带来收益账户)")
	private Long rewardsSubAccountId;

	/**奖励账户对应直接下级邮箱(带来收益账户)*/
	@ApiModelProperty(value = "奖励账户对应直接下级邮箱(带来收益账户)")
	private String rewardsSubEmail;

	/**奖励比例*/
	@ApiModelProperty(value = "奖励比例")
	private String rewardsRate;

	/**奖励金额*/
	@ApiModelProperty(value = "奖励金额")
	private java.math.BigDecimal rewardsBalance;

	/**奖励级别(1、2、3)*/
	@ApiModelProperty(value = "奖励级别(1、2、3)")
	private Integer rewardsLevel;

	/**奖励标签(账户Id逗号隔开)*/
	@ApiModelProperty(value = "奖励标签(账户Id逗号隔开)")
	private String rewardsTag;

	/**状态(0未奖励、1已奖励)*/
	@ApiModelProperty(value = "状态(0未奖励、1已奖励)")
	private Integer rewardsStatus;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	@ApiModelProperty(value = "账户编号")
	private java.lang.Long unid;

	@ApiModelProperty(value = "ID")
	private java.lang.Long id;

}

