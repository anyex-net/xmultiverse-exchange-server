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
 * APP下载信息 分页请求对象
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
@ApiModel(description = "APP下载信息分页请求对象")
public class ReqAppDownloadInfoPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**IP地址*/
	@ApiModelProperty(value = "IP地址")
	private java.lang.String ip;

	/**来源Url*/
	@ApiModelProperty(value = "来源Url")
	private java.lang.String sourceUrl;
}

