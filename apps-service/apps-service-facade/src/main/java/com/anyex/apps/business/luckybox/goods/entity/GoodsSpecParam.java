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
 * 商品品类参数表 实体对象
 * <p>File：GoodsSpecParam.java</p>
 * <p>Title: GoodsSpecParam</p>
 * <p>Description:GoodsSpecParam</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "商品品类参数表")
public class GoodsSpecParam extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**品类Id*/
	@NotNull(message = "品类Id不可为空")
	@ApiModelProperty(value = "品类Id", required = true)
	private java.lang.Long spgId;

	/**参数名称*/
	@NotNull(message = "参数名称不可为空")
	@ApiModelProperty(value = "参数名称", required = true)
	private java.lang.String paramName;

	/**是否为数字参数*/
	@NotNull(message = "是否为数字参数不可为空")
	@ApiModelProperty(value = "是否为数字参数", required = true)
	private java.lang.Boolean isNumeric;

	/**单位(量词)*/
	@ApiModelProperty(value = "单位(量词)")
	private java.lang.String unit;

	/**参数值*/
	@ApiModelProperty(value = "参数值")
	private java.lang.String paramValue;
}

