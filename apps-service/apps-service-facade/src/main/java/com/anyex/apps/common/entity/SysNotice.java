/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
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
 * 平台公告表 实体对象
 * <p>File：Notice.java</p>
 * <p>Title: Notice</p>
 * <p>Description:Notice</p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "平台公告表")
public class SysNotice extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**语言类型(zh_CN简体、zh_HK繁体、en_US英文)*/
	@NotNull(message = "语言类型(zh_CN简体、zh_HK繁体、en_US英文)不可为空")
	@ApiModelProperty(value = "语言类型(zh_CN简体、zh_HK繁体、en_US英文)", required = true)
	private String langType;

	/**标题*/
	@NotNull(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
	private String title;

	/**图片*/
	@NotNull(message = "图片不可为空")
	@ApiModelProperty(value = "图片", required = true)
	private String imageUrl;

	/**内容*/
	@NotNull(message = "内容不可为空")
	@ApiModelProperty(value = "内容", required = true)
	private String content;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**状态(0未发布、1已发布)*/
	@NotNull(message = "状态(0未发布、1已发布)不可为空")
	@ApiModelProperty(value = "状态(0未发布、1已发布)", required = true)
	private Boolean status;

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

	/**发布人*/
	@ApiModelProperty(value = "发布人")
	private Long publishBy;

	/**发布时间*/
	@ApiModelProperty(value = "发布时间")
	private Long publishDate;

	///////////////////////////////////
	/**排序字段*/
	@ApiModelProperty(value = "排序字段")
	private String orderBy;
}

