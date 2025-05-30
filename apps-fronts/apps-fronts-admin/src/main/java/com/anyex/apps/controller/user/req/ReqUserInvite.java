/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户邀请关系 实体请求对象
 * <p>File：ReqUserInvite.java</p>
 * <p>Title: ReqUserInvite</p>
 * <p>Description:ReqUserInvite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户邀请关系请求对象")
public class ReqUserInvite extends GenericEntity
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

	/**邀请方式（如链接邀请、推荐码邀请）*/
	@NotEmpty(message = "邀请方式（如链接邀请、推荐码邀请）不可为空")
	@ApiModelProperty(value = "邀请方式（如链接邀请、推荐码邀请）", position = 3, required = true)
	private String inviteType;

	/**使用的推荐码（如果有的话）*/
	@ApiModelProperty(value = "使用的推荐码（如果有的话）", position = 4)
	private String inviteCodeUsed;

	/**是否为有效邀请（是否完成实名认证后才算有效）*/
	@NotNull(message = "是否为有效邀请（是否完成实名认证后才算有效）不可为空")
	@ApiModelProperty(value = "是否为有效邀请（是否完成实名认证后才算有效）", position = 5, required = true)
	private Integer isValid;

	/**邀请时间（即被邀请人注册时间）*/
	@NotNull(message = "邀请时间（即被邀请人注册时间）不可为空")
	@ApiModelProperty(value = "邀请时间（即被邀请人注册时间）", position = 6, required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 7)
	private Long updateTime;


}

