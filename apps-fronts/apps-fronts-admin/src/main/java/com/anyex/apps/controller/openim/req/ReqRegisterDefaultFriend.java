/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注册默认好友 实体请求对象
 * <p>File：ReqRegisterDefaultFriend.java</p>
 * <p>Title: ReqRegisterDefaultFriend</p>
 * <p>Description:ReqRegisterDefaultFriend</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "注册默认好友请求对象")
public class ReqRegisterDefaultFriend extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotEmpty(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", required = true)
	private String userId;

	/**昵称*/
	@NotEmpty(message = "昵称不可为空")
	@ApiModelProperty(value = "昵称", required = true)
	private String nickname;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String faceUrl;


}

