/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 */
@Data
@ApiModel(description = "账户邀请关系对象")
@AllArgsConstructor
@NoArgsConstructor
public class AccountInvitedModel
{
	@ApiModelProperty(value = "邀请人ID")
	private  Long inviteId;

	@ApiModelProperty(value = "邀请人UID")
	private  Long inviteUnid;

	@ApiModelProperty(value = "邀请人Email")
	private  String inviteEmail;

	@ApiModelProperty(value = "被邀请人ID")
	private  Long id;

	/**账户编号*/
	@ApiModelProperty(value = "被邀请人UID")
	private java.lang.Long unid;

	/**邮箱*/
	@ApiModelProperty(value = "被邀请人邮箱")
	private java.lang.String email;


	/**邀请码*/
	@ApiModelProperty(value = "邀请码")
	private java.lang.String invitationCode;

	/**推荐码*/
	@ApiModelProperty(value = "被邀请人推荐码")
	private java.lang.String referralCode;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;


}

