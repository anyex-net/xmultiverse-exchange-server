/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户邀请奖励表 实体请求对象
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
@ApiModel(description = "账户邀请奖励表请求对象")
public class ReqAccountInviteRewards extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**注册账户ID*/
	@NotNull(message = "注册账户ID不可为空")
	@ApiModelProperty(value = "注册账户ID", required = true)
	private Long registerAccountId;

	/**注册账户邮箱*/
	@NotEmpty(message = "注册账户邮箱不可为空")
	@ApiModelProperty(value = "注册账户邮箱", required = true)
	private String registerEmail;

	/**邀请账户ID*/
	@NotNull(message = "邀请账户ID不可为空")
	@ApiModelProperty(value = "邀请账户ID", required = true)
	private Long inviteAccountId;

	/**邀请账户邮箱*/
	@NotEmpty(message = "邀请账户邮箱不可为空")
	@ApiModelProperty(value = "邀请账户邮箱", required = true)
	private String inviteEmail;

	/**奖励账户ID*/
	@NotNull(message = "奖励账户ID不可为空")
	@ApiModelProperty(value = "奖励账户ID", required = true)
	private Long rewardsAccountId;

	/**奖励账户邮箱*/
	@NotEmpty(message = "奖励账户邮箱不可为空")
	@ApiModelProperty(value = "奖励账户邮箱", required = true)
	private String rewardsEmail;

	/**奖励账户对应直接下级账户ID(带来收益账户)*/
	@NotNull(message = "奖励账户对应直接下级账户ID(带来收益账户)不可为空")
	@ApiModelProperty(value = "奖励账户对应直接下级账户ID(带来收益账户)", required = true)
	private Long rewardsSubAccountId;

	/**奖励账户对应直接下级邮箱(带来收益账户)*/
	@NotEmpty(message = "奖励账户对应直接下级邮箱(带来收益账户)不可为空")
	@ApiModelProperty(value = "奖励账户对应直接下级邮箱(带来收益账户)", required = true)
	private String rewardsSubEmail;

	/**奖励比例*/
	@NotEmpty(message = "奖励比例不可为空")
	@ApiModelProperty(value = "奖励比例", required = true)
	private String rewardsRate;

	/**奖励金额*/
	@NotNull(message = "奖励金额不可为空")
	@ApiModelProperty(value = "奖励金额", required = true)
	private java.math.BigDecimal rewardsBalance;

	/**奖励级别(1、2、3)*/
	@NotNull(message = "奖励级别(1、2、3)不可为空")
	@ApiModelProperty(value = "奖励级别(1、2、3)", required = true)
	private Integer rewardsLevel;

	/**奖励标签(账户Id逗号隔开)*/
	@NotEmpty(message = "奖励标签(账户Id逗号隔开)不可为空")
	@ApiModelProperty(value = "奖励标签(账户Id逗号隔开)", required = true)
	private String rewardsTag;

	/**状态(0未奖励、1已奖励)*/
	@NotNull(message = "状态(0未奖励、1已奖励)不可为空")
	@ApiModelProperty(value = "状态(0未奖励、1已奖励)", required = true)
	private Integer rewardsStatus;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;


}

