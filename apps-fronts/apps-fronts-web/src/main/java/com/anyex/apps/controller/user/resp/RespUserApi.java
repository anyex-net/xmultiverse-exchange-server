/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户API返回对象
 * <p>File：RespUserApi.java</p>
 * <p>Title: RespUserApi</p>
 * <p>Description:RespUserApi</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "用户API返回对象")
public class RespUserApi implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**apiKey*/
	@ApiModelProperty(value = "apiKey", position = 1)
	private String apiKey;

	/**私钥*/
	@ApiModelProperty(value = "私钥", position = 2)
	private String priKey;
}

