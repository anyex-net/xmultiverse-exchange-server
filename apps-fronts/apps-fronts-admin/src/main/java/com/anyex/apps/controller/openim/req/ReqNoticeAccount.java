/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知账号 实体请求对象
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
@ApiModel(description = "通知账号请求对象")
public class ReqNoticeAccount extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotEmpty(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", required = true)
	private String userId;

	/**昵称*/
	@NotEmpty(message = "昵称不可为空")
	@ApiModelProperty(value = "昵称", required = true)
	private String nickname;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String faceUrl;


}

