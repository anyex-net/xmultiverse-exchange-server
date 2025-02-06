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
 * 注册默认群 分页请求对象
 * <p>File：ReqRegisterDefaultGroup.java</p>
 * <p>Title: ReqRegisterDefaultGroup</p>
 * <p>Description:ReqRegisterDefaultGroup</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "注册默认群分页请求对象")
public class ReqRegisterDefaultGroupPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**群ID*/
	@ApiModelProperty(value = "群ID")
	private String groupId;

	/**群名称*/
	@ApiModelProperty(value = "群名称")
	private String groupName;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String groupFaceUrl;


}

