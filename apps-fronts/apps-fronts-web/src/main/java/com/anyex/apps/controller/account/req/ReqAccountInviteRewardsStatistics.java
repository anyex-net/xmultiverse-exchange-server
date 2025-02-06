/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "查询返佣统计对象")
public class ReqAccountInviteRewardsStatistics
{
	@NotNull(message = "返佣级别不可为空")
	@Range(min = 1, max =3,message = "返佣级别范围1-3")
	@ApiModelProperty(value = "返佣级别(1,2,3)", required = true)
	private Integer level;

	@ApiModelProperty(value = "二级返佣账户id level=2时传入")
	private Long secondAccountId;

	@ApiModelProperty(value = "三级返佣账户id level=3时传入")
	private Long thirdAccountId;
}

