/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易日 分页请求对象
 * <p>File：ReqSysTradeDay.java</p>
 * <p>Title: ReqSysTradeDay</p>
 * <p>Description:ReqSysTradeDay</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "交易日分页请求对象")
public class ReqSysTradeDayPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**日期YYYYMMDD*/
	@ApiModelProperty(value = "日期YYYYMMDD")
	private String date;

	/**类型(0非交易日、1交易日)*/
	@ApiModelProperty(value = "类型(0非交易日、1交易日)")
	private Integer type;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**创建人名字*/
	@ApiModelProperty(value = "创建人名字")
	private String createName;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	/**更新人名字*/
	@ApiModelProperty(value = "更新人名字")
	private String updateName;


}

