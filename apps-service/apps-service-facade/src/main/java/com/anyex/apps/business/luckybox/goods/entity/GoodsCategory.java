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

import java.util.List;

/**
 * 商品分类表 实体对象
 * <p>File：GoodsCategory.java</p>
 * <p>Title: GoodsCategory</p>
 * <p>Description:GoodsCategory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品分类表")
public class GoodsCategory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**分类名称*/
	@NotNull(message = "分类名称不可为空")
	@ApiModelProperty(value = "分类名称", required = true)
	private java.lang.String name;

	/**上级分类ID*/
	@ApiModelProperty(value = "上级分类ID")
	private java.lang.Long parentId;

	/**排序*/
	@NotNull(message = "排序不可为空")
	@ApiModelProperty(value = "排序", required = true)
	private java.lang.Integer sort;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;

	///////////////////////////////////
	/**子节点*/
	@ApiModelProperty(value = "子节点")
	private List<GoodsCategory> children;
}

