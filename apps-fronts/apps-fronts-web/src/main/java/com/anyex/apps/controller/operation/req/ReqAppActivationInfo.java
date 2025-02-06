/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * APP激活信息 实体请求对象
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
@ApiModel(description = "APP激活信息请求对象")
public class ReqAppActivationInfo extends GenericEntity
{
	/**设备编号*/
	@NotEmpty(message = "设备编号不可为空")
	@ApiModelProperty(value = "设备编号", required = true)
	private String deviceId;

	/**app版本*/
	@NotEmpty(message = "app版本不可为空")
	@ApiModelProperty(value = "app版本", required = true)
	private String appVersion;

	/**来源*/
	@ApiModelProperty(value = "来源")
	private String source;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
}

