/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群组查询对象
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "群组分页请求对象")
public class ReqMsgPagination extends Pagination
{
	private static final long serialVersionUID = 1L;

	/**
	 * 发送者ID
	 */
	private String sendID;
	/**
	 * 接收者ID
	 */
	private String recvID;
	private Integer msgType;
	private String sendTime;
	private Integer sessionType;
}

