/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.shop.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 店铺表 实体对象
 * <p>File：Shop.java</p>
 * <p>Title: Shop</p>
 * <p>Description:Shop</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "店铺表")
public class Shop extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**城市*/
	@NotNull(message = "城市不可为空")
	@ApiModelProperty(value = "城市", required = true)
	private java.lang.String city;

	/**地址*/
	@NotNull(message = "地址不可为空")
	@ApiModelProperty(value = "地址", required = true)
	private java.lang.String address;

	/**电话*/
	@NotNull(message = "电话不可为空")
	@ApiModelProperty(value = "电话", required = true)
	private java.lang.String tel;
}

