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
 * 商品品牌表 实体对象
 * <p>File：GoodsBrand.java</p>
 * <p>Title: GoodsBrand</p>
 * <p>Description:GoodsBrand</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品品牌表")
public class GoodsBrand extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**品牌名称*/
	@NotNull(message = "品牌名称不可为空")
	@ApiModelProperty(value = "品牌名称", required = true)
	private java.lang.String name;

	/**品牌Logo图片Url*/
	@ApiModelProperty(value = "品牌Logo图片Url")
	private java.lang.String logoImageUrl;

	/**品牌首字母*/
	@NotNull(message = "品牌首字母不可为空")
	@ApiModelProperty(value = "品牌首字母", required = true)
	private java.lang.String letter;

	/**状态(是否启用)*/
	@NotNull(message = "状态(是否启用)不可为空")
	@ApiModelProperty(value = "状态(是否启用)", required = true)
	private java.lang.Boolean status;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

