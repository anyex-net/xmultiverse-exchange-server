/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 产品SPU表 实体对象
 * <p>File：GoodsSpu.java</p>
 * <p>Title: GoodsSpu</p>
 * <p>Description:GoodsSpu</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "产品SPU表")
public class GoodsSpu extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**标题*/
	@NotNull(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
	private java.lang.String title;

	/**副标题*/
	@NotNull(message = "副标题不可为空")
	@ApiModelProperty(value = "副标题", required = true)
	private java.lang.String subTitle;

	/**分类ID*/
	@NotNull(message = "分类ID不可为空")
	@ApiModelProperty(value = "分类ID", required = true)
	private java.lang.Long categoryId;

	/**品牌ID*/
	@NotNull(message = "品牌ID不可为空")
	@ApiModelProperty(value = "品牌ID", required = true)
	private java.lang.Long brandId;

	/**品类ID*/
	@NotNull(message = "品类ID不可为空")
	@ApiModelProperty(value = "品类ID", required = true)
	private java.lang.Long spgId;

	/**是否上架*/
	@NotNull(message = "是否上架不可为空")
	@ApiModelProperty(value = "是否上架", required = true)
	private java.lang.Boolean saleable;

	/**是否有效*/
	@NotNull(message = "是否有效不可为空")
	@ApiModelProperty(value = "是否有效", required = true)
	private java.lang.Boolean valid;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

