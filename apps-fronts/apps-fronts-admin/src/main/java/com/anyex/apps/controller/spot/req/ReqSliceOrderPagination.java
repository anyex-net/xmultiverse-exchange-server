/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.spot.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * slice_order_example 分页请求对象
 * <p>File：ReqSliceOrderExample.java</p>
 * <p>Title: ReqSliceOrderExample</p>
 * <p>Description:ReqSliceOrderExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "slice_order_example分页请求对象")
public class ReqSliceOrderPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**t*/
	@ApiModelProperty(value = "t", position = 1)
	private Short t;

	/**side*/
	@ApiModelProperty(value = "side", position = 2)
	private Short side;

	/**createTime*/
	@ApiModelProperty(value = "createTime", position = 3)
	private Double createTime;

	/**updateTime*/
	@ApiModelProperty(value = "updateTime", position = 4)
	private Double updateTime;

	/**userId*/
	@ApiModelProperty(value = "userId", position = 5)
	private Integer userId;

	/**market*/
	@ApiModelProperty(value = "market", position = 6)
	private String market;

	/**price*/
	@ApiModelProperty(value = "price", position = 7)
	private java.math.BigDecimal price;

	/**amount*/
	@ApiModelProperty(value = "amount", position = 8)
	private java.math.BigDecimal amount;

	/**takerFee*/
	@ApiModelProperty(value = "takerFee", position = 9)
	private java.math.BigDecimal takerFee;

	/**makerFee*/
	@ApiModelProperty(value = "makerFee", position = 10)
	private java.math.BigDecimal makerFee;

	/**left*/
	@ApiModelProperty(value = "left", position = 11)
	private java.math.BigDecimal left;

	/**freeze*/
	@ApiModelProperty(value = "freeze", position = 12)
	private java.math.BigDecimal freeze;

	/**dealStock*/
	@ApiModelProperty(value = "dealStock", position = 13)
	private java.math.BigDecimal dealStock;

	/**dealMoney*/
	@ApiModelProperty(value = "dealMoney", position = 14)
	private java.math.BigDecimal dealMoney;

	/**dealFee*/
	@ApiModelProperty(value = "dealFee", position = 15)
	private java.math.BigDecimal dealFee;


}

