/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 访问日志 实体对象
 * <p>File：AccessLog.java</p>
 * <p>Title: AccessLog</p>
 * <p>Description:AccessLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "访问日志")
public class SysAccessLog extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**用户名称*/
	@ApiModelProperty(value = "用户名称")
	private String userName;

	/**模块*/
	@ApiModelProperty(value = "模块")
	private String module;

	/**方法*/
	@ApiModelProperty(value = "方法")
	private String method;

	/**类型*/
	@ApiModelProperty(value = "类型")
	private String type;

	/**描述*/
	@ApiModelProperty(value = "描述")
	private String remark;

	/**请求参数*/
	@ApiModelProperty(value = "请求参数")
	private String reqParam;

	/**返回参数*/
	@ApiModelProperty(value = "返回参数")
	private String respParam;

	/**请求URI*/
	@ApiModelProperty(value = "请求URI")
	private String uri;

	/**ip*/
	@ApiModelProperty(value = "ip")
	private String ip;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createDate;
}

