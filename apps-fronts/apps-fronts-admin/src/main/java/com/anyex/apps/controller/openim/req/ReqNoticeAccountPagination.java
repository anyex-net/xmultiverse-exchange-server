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
 * 通知账号 分页请求对象
 * <p>File：ReqNoticeAccount.java</p>
 * <p>Title: ReqNoticeAccount</p>
 * <p>Description:ReqNoticeAccount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "通知账号分页请求对象")
public class ReqNoticeAccountPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID")
	private String userId;

	/**昵称*/
	@ApiModelProperty(value = "昵称")
	private String nickname;
}

