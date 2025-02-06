/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "游戏信息")
public class ReqGame extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**游戏名称*/
	@NotNull(message = "游戏名称不可为空")
	@ApiModelProperty(value = "游戏名称", required = true)
	private java.lang.String name;

	/**游戏图片url*/
	@NotNull(message = "游戏图片url不可为空")
	@ApiModelProperty(value = "游戏图片url", required = true)
	private java.lang.String gameImgUrl;

	/**游戏参与筹码(金额)*/
	@NotNull(message = "游戏参与筹码(金额)不可为空")
	@ApiModelProperty(value = "游戏参与筹码(金额)", required = true)
	private java.math.BigDecimal gameChips;

	/**游戏是否启用(0未启用、1启用)*/
	@NotNull(message = "游戏是否启用(0未启用、1启用)不可为空")
	@ApiModelProperty(value = "游戏是否启用(0未启用、1启用)", required = true)
	private java.lang.Boolean status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

