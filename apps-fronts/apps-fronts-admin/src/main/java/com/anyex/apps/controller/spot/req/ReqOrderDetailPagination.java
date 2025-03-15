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
 * order_detail_example 分页请求对象
 * <p>File：ReqOrderDetailExample.java</p>
 * <p>Title: ReqOrderDetailExample</p>
 * <p>Description:ReqOrderDetailExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "order_detail_example分页请求对象")
public class ReqOrderDetailPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**createTime*/
	@ApiModelProperty(value = "createTime", position = 1)
	private Double createTime;

	/**finishTime*/
	@ApiModelProperty(value = "finishTime", position = 2)
	private Double finishTime;

	/**userId*/
	@ApiModelProperty(value = "userId", position = 3)
	private Integer userId;

	/**market*/
	@ApiModelProperty(value = "market", position = 4)
	private String market;

	/**source*/
	@ApiModelProperty(value = "source", position = 5)
	private String source;

	/**t*/
	@ApiModelProperty(value = "t", position = 6)
	private Short t;

	/**side*/
	@ApiModelProperty(value = "side", position = 7)
	private Short side;

	/**price*/
	@ApiModelProperty(value = "price", position = 8)
	private java.math.BigDecimal price;

	/**amount*/
	@ApiModelProperty(value = "amount", position = 9)
	private java.math.BigDecimal amount;

	/**takerFee*/
	@ApiModelProperty(value = "takerFee", position = 10)
	private java.math.BigDecimal takerFee;

	/**makerFee*/
	@ApiModelProperty(value = "makerFee", position = 11)
	private java.math.BigDecimal makerFee;

	/**dealStock*/
	@ApiModelProperty(value = "dealStock", position = 12)
	private java.math.BigDecimal dealStock;

	/**dealMoney*/
	@ApiModelProperty(value = "dealMoney", position = 13)
	private java.math.BigDecimal dealMoney;

	/**dealFee*/
	@ApiModelProperty(value = "dealFee", position = 14)
	private java.math.BigDecimal dealFee;


}

