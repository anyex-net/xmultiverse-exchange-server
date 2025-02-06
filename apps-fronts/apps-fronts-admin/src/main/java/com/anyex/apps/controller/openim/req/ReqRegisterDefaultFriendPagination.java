/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注册默认好友 分页请求对象
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
@ApiModel(description = "注册默认好友分页请求对象")
public class ReqRegisterDefaultFriendPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID")
	private String userId;

	/**昵称*/
	@ApiModelProperty(value = "昵称")
	private String nickname;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String faceUrl;


}

