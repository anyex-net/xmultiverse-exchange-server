/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户交易产品收藏 实体对象
 * <p>File：UserInstrumentsFavorite.java</p>
 * <p>Title: UserInstrumentsFavorite</p>
 * <p>Description:UserInstrumentsFavorite</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户交易产品收藏")
public class UserInstrumentsFavorite extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION*/
	@NotEmpty(message = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION不可为空")
	@ApiModelProperty(value = "产品类型 币币SPOT 币币杠杆MARGIN 永续合约SWAP 交割合约FUTURES 期权OPTION", position = 2, required = true)
	private String instType;

	/**产品ID 如BTC-USD-SWAP*/
	@NotEmpty(message = "产品ID 如BTC-USD-SWAP不可为空")
	@ApiModelProperty(value = "产品ID 如BTC-USD-SWAP", position = 3, required = true)
	private String instId;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 4)
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 5, required = true)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 6)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 7)
	private Long updateTime;
}

