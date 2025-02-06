/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注册默认群 实体对象
 * <p>File：RegisterDefaultGroup.java</p>
 * <p>Title: RegisterDefaultGroup</p>
 * <p>Description:RegisterDefaultGroup</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "注册默认群")
public class RegisterDefaultGroup extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**群ID*/
	@NotEmpty(message = "群ID不可为空")
	@ApiModelProperty(value = "群ID", required = true)
	private String groupId;

	/**群名称*/
	@NotEmpty(message = "群名称不可为空")
	@ApiModelProperty(value = "群名称", required = true)
	private String groupName;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String groupFaceUrl;


}

