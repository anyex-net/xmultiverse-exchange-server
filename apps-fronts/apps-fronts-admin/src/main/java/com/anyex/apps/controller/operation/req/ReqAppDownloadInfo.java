/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.operation.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * APP下载信息 实体请求对象
 * <p>File：ReqAppDownloadInfo.java</p>
 * <p>Title: ReqAppDownloadInfo</p>
 * <p>Description:ReqAppDownloadInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "APP下载信息请求对象")
public class ReqAppDownloadInfo extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**IP地址*/
	@NotEmpty(message = "IP地址不可为空")
	@ApiModelProperty(value = "IP地址", required = true)
	private java.lang.String ip;

	/**来源Url*/
	@NotEmpty(message = "来源Url不可为空")
	@ApiModelProperty(value = "来源Url", required = true)
	private java.lang.String sourceUrl;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

