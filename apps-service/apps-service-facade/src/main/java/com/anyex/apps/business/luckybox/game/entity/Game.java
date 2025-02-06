/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 游戏信息表 实体对象
 * <p>File：Game.java</p>
 * <p>Title: Game</p>
 * <p>Description:Game</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "游戏信息表")
public class Game extends GenericEntity
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
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

