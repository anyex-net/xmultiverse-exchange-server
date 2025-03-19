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
 * 用户日志 分页请求对象
 * <p>File：ReqUserLog.java</p>
 * <p>Title: ReqUserLog</p>
 * <p>Description:ReqUserLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户日志分页请求对象")
public class ReqUserLogPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private Long userId;

	/**用户名字*/
	@ApiModelProperty(value = "用户名字", position = 2)
	private String userName;

	/**系统名称*/
	@ApiModelProperty(value = "系统名称", position = 3)
	private String systemName;

	/**操作类型(login登录、setting安全设置)*/
	@ApiModelProperty(value = "操作类型(login登录、setting安全设置)", position = 4)
	private String opType;

	/**IP地址*/
	@ApiModelProperty(value = "IP地址", position = 5)
	private String ipAddr;

	/**证件号码*/
	@ApiModelProperty(value = "证件号码", position = 6)
	private String rigonName;

	/**URL地址*/
	@ApiModelProperty(value = "URL地址", position = 7)
	private String url;

	/**内容*/
	@ApiModelProperty(value = "内容", position = 8)
	private String content;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 9)
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 10)
	private Long createTime;
}

