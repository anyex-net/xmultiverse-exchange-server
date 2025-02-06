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

import javax.validation.constraints.NotNull;

/**
 * RoleData 实体对象
 * <p>File：RoleData.java</p>
 * <p>Title: RoleData</p>
 * <p>Description:RoleData</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RoleData")
public class SysRoleData extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**角色Id*/
	@NotNull(message = "角色Id不可为空")
	@ApiModelProperty(value = "角色Id", required = true)
	private Long roleId;

	/**机构Id*/
	@NotNull(message = "机构Id不可为空")
	@ApiModelProperty(value = "机构Id", required = true)
	private Long orgId;

	public SysRoleData()
	{
		super();
	}

	public SysRoleData(Long roleId, Long orgId)
	{
		this.roleId = roleId;
		this.orgId = orgId;
	}
}

