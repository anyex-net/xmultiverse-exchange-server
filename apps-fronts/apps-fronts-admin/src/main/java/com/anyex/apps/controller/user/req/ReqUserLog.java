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
 * 用户日志 实体请求对象
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
@ApiModel(description = "用户日志请求对象")
public class ReqUserLog extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**用户名字*/
	@ApiModelProperty(value = "用户名字", position = 2)
	private String userName;

	/**系统名称*/
	@NotEmpty(message = "系统名称不可为空")
	@ApiModelProperty(value = "系统名称", position = 3, required = true)
	private String systemName;

	/**操作类型(login登录、setting安全设置)*/
	@NotEmpty(message = "操作类型(login登录、setting安全设置)不可为空")
	@ApiModelProperty(value = "操作类型(login登录、setting安全设置)", position = 4, required = true)
	private String opType;

	/**IP地址*/
	@NotEmpty(message = "IP地址不可为空")
	@ApiModelProperty(value = "IP地址", position = 5, required = true)
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
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 10, required = true)
	private Long createTime;
}

