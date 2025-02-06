/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.shop.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "店铺")
public class ReqShop extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**城市*/
	@NotNull(message = "城市不可为空")
	@ApiModelProperty(value = "城市", required = true)
	private String city;

	/**地址*/
	@NotNull(message = "地址不可为空")
	@ApiModelProperty(value = "地址", required = true)
	private String address;

	/**电话*/
	@NotNull(message = "电话不可为空")
	@ApiModelProperty(value = "电话", required = true)
	private String tel;
}

