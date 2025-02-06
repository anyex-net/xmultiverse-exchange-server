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
 * UserData 实体对象
 * <p>File：UserData.java</p>
 * <p>Title: UserData</p>
 * <p>Description:UserData</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "UserData")
public class SysUserData extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", required = true)
	private Long userId;

	/**机构Id*/
	@NotNull(message = "机构Id不可为空")
	@ApiModelProperty(value = "机构Id", required = true)
	private Long orgId;

	public SysUserData()
	{
		super();
	}

	public SysUserData(Long userId, Long orgId)
	{
		this.userId = userId;
		this.orgId = orgId;
	}
}

