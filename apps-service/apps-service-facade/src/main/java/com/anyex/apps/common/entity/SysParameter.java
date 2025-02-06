/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 参数配置 实体对象
 * <p>File：Parameter.java</p>
 * <p>Title: Parameter</p>
 * <p>Description:Parameter</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "参数配置")
public class SysParameter extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**系统名称*/
	@NotNull(message = "系统名称不可为空")
	@ApiModelProperty(value = "系统名称", required = true)
	private java.lang.String systemName;

	/**参数名称*/
	@NotNull(message = "参数名称不可为空")
	@ApiModelProperty(value = "参数名称", required = true)
	private java.lang.String parameterName;

	/**参数大类*/
	@NotNull(message = "参数大类不可为空")
	@ApiModelProperty(value = "参数大类", required = true)
	private java.lang.String division;

	/**参数类型*/
	@NotNull(message = "参数类型不可为空")
	@ApiModelProperty(value = "参数类型", required = true)
	private java.lang.String type;

	/**参数值值域*/
	@ApiModelProperty(value = "参数值值域")
	private java.lang.String valueBound;

	/**参数值*/
	@NotNull(message = "参数值不可为空")
	@ApiModelProperty(value = "参数值", required = true)
	private java.lang.String value;

	/**参数备注*/
	@NotNull(message = "参数备注不可为空")
	@ApiModelProperty(value = "参数备注", required = true)
	private java.lang.String remark;

	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private java.lang.Long createBy;

	/**创建时间*/
	//@NotNull(message = "创建时间不可为空")
	//@ApiModelProperty(value = "创建时间", required = true)
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createDate;

	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.Long updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateDate;
}

