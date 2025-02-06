/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 交易日 实体对象
 * <p>File：SysTradeDay.java</p>
 * <p>Title: SysTradeDay</p>
 * <p>Description:SysTradeDay</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "交易日")
public class SysTradeDay extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**日期YYYYMMDD*/
	@NotEmpty(message = "日期YYYYMMDD不可为空")
	@ApiModelProperty(value = "日期YYYYMMDD", required = true)
	private String date;

	/**类型(0非交易日、1交易日)*/
	@NotNull(message = "类型(0非交易日、1交易日)不可为空")
	@ApiModelProperty(value = "类型(0非交易日、1交易日)", required = true)
	private Integer type;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
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

