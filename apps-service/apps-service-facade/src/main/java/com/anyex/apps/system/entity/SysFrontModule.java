/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.entity;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 前端功能模块表 实体对象
 * <p>File：FrontModule.java</p>
 * <p>Title: FrontModule</p>
 * <p>Description:FrontModule</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "前端功能模块表")
public class SysFrontModule extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**功能类别*/
	@NotNull(message = "功能类别不可为空")
	@ApiModelProperty(value = "功能类别")
	private String moduleClass;

	/**功能编码*/
	@NotNull(message = "功能编码不可为空")
	@ApiModelProperty(value = "功能编码", required = true)
	private String moduleCode;

	/**功能名称*/
	@NotNull(message = "功能名称不可为空")
	@ApiModelProperty(value = "功能名称", required = true)
	private String moduleName;

	/**功能图标url*/
	@NotNull(message = "功能图标url不可为空")
	@ApiModelProperty(value = "功能图标url", required = true)
	private String moduleIconUrl;

	/**功能具体跳转url*/
	@NotNull(message = "功能具体跳转url不可为空")
	@ApiModelProperty(value = "功能具体跳转url")
	private String moduleJumpUrl;

	/**排序号*/
	@NotNull(message = "排序号不可为空")
	@ApiModelProperty(value = "排序号", required = true)
	private Integer sortNum;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建人*/
	@NotNull(message = "创建人不可为空")
	@ApiModelProperty(value = "创建人", required = true)
	private Long createBy;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createDate;

	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private Long updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateDate;
}

