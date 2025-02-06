/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 前端角色权限信息表 实体对象
 * <p>File：FrontRoleRes.java</p>
 * <p>Title: FrontRoleRes</p>
 * <p>Description:FrontRoleRes</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "前端角色权限信息表")
public class SysFrontRoleRes extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**角色ID*/
	@NotNull(message = "角色ID不可为空")
	@ApiModelProperty(value = "角色ID", required = true)
	private Long roleId;

	/**功能模块ID*/
	@NotNull(message = "功能模块ID不可为空")
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "功能模块ID", required = true)
	private Long moduleId;
}

