/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * app设备 实体对象
 * <p>File：AppDevice.java</p>
 * <p>Title: AppDevice</p>
 * <p>Description:AppDevice</p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "app设备")
public class SysAppDevice extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**账户id*/
	@NotNull(message = "账户id不可为空")
	@ApiModelProperty(value = "账户id", required = true)
	private Long accountId;

	/**设备类型(ios、android、client)*/
	@NotNull(message = "设备类型(ios、android、client)不可为空")
	@ApiModelProperty(value = "设备类型(ios、android、client)", required = true)
	private String deviceType;

	/**设备名字*/
	@NotNull(message = "设备名字不可为空")
	@ApiModelProperty(value = "设备名字", required = true)
	private String deviceName;

	/**设备编码*/
	@NotNull(message = "设备编码不可为空")
	@ApiModelProperty(value = "设备编码", required = true)
	private String deviceNumber;

	/**IP地址*/
	@NotNull(message = "IP地址不可为空")
	@ApiModelProperty(value = "IP地址", required = true)
	private String ipAddress;

	/**版本号*/
	@NotNull(message = "版本号不可为空")
	@ApiModelProperty(value = "版本号", required = true)
	private String appVersion;

	/**build版本号*/
	@NotNull(message = "build版本号不可为空")
	@ApiModelProperty(value = "build版本号", required = true)
	private String buildVersion;

	/**最后登录时间*/
	@NotNull(message = "最后登录时间不可为空")
	@ApiModelProperty(value = "最后登录时间", required = true)
	private Long lastLoginDate;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createDate;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
}

