/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user_deal_history_example 实体请求对象
 * <p>File：ReqUserDealHistoryExample.java</p>
 * <p>Title: ReqUserDealHistoryExample</p>
 * <p>Description:ReqUserDealHistoryExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "user_deal_history_example请求对象")
public class ReqUserDealHistory extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@NotNull(message = "time不可为空")
	@ApiModelProperty(value = "time", position = 1, required = true)
	private Double time;

	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "userId", position = 2, required = true)
	private Integer userId;

	/**market*/
	@NotEmpty(message = "market不可为空")
	@ApiModelProperty(value = "market", position = 3, required = true)
	private String market;

	/**dealId*/
	@NotNull(message = "dealId不可为空")
	@ApiModelProperty(value = "dealId", position = 4, required = true)
	private Long dealId;

	/**orderId*/
	@NotNull(message = "orderId不可为空")
	@ApiModelProperty(value = "orderId", position = 5, required = true)
	private Long orderId;

	/**dealOrderId*/
	@NotNull(message = "dealOrderId不可为空")
	@ApiModelProperty(value = "dealOrderId", position = 6, required = true)
	private Long dealOrderId;

	/**side*/
	@NotNull(message = "side不可为空")
	@ApiModelProperty(value = "side", position = 7, required = true)
	private Short side;

	/**role*/
	@NotNull(message = "role不可为空")
	@ApiModelProperty(value = "role", position = 8, required = true)
	private Short role;

	/**price*/
	@NotNull(message = "price不可为空")
	@ApiModelProperty(value = "price", position = 9, required = true)
	private java.math.BigDecimal price;

	/**amount*/
	@NotNull(message = "amount不可为空")
	@ApiModelProperty(value = "amount", position = 10, required = true)
	private java.math.BigDecimal amount;

	/**deal*/
	@NotNull(message = "deal不可为空")
	@ApiModelProperty(value = "deal", position = 11, required = true)
	private java.math.BigDecimal deal;

	/**fee*/
	@NotNull(message = "fee不可为空")
	@ApiModelProperty(value = "fee", position = 12, required = true)
	private java.math.BigDecimal fee;

	/**dealFee*/
	@NotNull(message = "dealFee不可为空")
	@ApiModelProperty(value = "dealFee", position = 13, required = true)
	private java.math.BigDecimal dealFee;


}

