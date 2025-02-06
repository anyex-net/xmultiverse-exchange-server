/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 账户LngLat更新请求对象
 * <p>File：ReqAccountLngLatUpdate.java</p>
 * <p>Title: ReqAccountLngLatUpdate</p>
 * <p>Description: ReqAccountLngLatUpdate</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAccountLngLatUpdate implements Serializable
{
	/**最新位置经度*/
	@NotEmpty(message = "最新位置经度不可为空")
	@ApiModelProperty(value = "最新位置经度", required = true)
	private String lng;

	/**最新位置维度*/
	@NotEmpty(message = "最新位置维度不可为空")
	@ApiModelProperty(value = "最新位置维度", required = true)
	private String lat;
}

