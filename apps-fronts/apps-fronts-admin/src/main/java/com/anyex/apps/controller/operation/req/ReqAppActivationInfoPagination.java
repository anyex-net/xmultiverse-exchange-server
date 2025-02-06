/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP激活信息 分页请求对象
 * <p>File：ReqAppActivationInfo.java</p>
 * <p>Title: ReqAppActivationInfo</p>
 * <p>Description:ReqAppActivationInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "APP激活信息分页请求对象")
public class ReqAppActivationInfoPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**IP地址*/
	@ApiModelProperty(value = "IP地址")
	private java.lang.String ip;

	/**设备编号*/
	@ApiModelProperty(value = "设备编号")
	private java.lang.String deviceId;

	/**app版本*/
	@ApiModelProperty(value = "app版本")
	private java.lang.String appVersion;

	/**来源*/
	@ApiModelProperty(value = "来源")
	private java.lang.String source;
}

