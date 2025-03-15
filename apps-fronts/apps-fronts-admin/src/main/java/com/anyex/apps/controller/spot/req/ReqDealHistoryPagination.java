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
 * deal_history_example 分页请求对象
 * <p>File：ReqDealHistoryExample.java</p>
 * <p>Title: ReqDealHistoryExample</p>
 * <p>Description:ReqDealHistoryExample</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "deal_history_example分页请求对象")
public class ReqDealHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@ApiModelProperty(value = "time", position = 1)
	private Double time;

	/**userId*/
	@ApiModelProperty(value = "userId", position = 2)
	private Integer userId;

	/**dealId*/
	@ApiModelProperty(value = "dealId", position = 3)
	private Long dealId;

	/**orderId*/
	@ApiModelProperty(value = "orderId", position = 4)
	private Long orderId;

	/**dealOrderId*/
	@ApiModelProperty(value = "dealOrderId", position = 5)
	private Long dealOrderId;

	/**role*/
	@ApiModelProperty(value = "role", position = 6)
	private Short role;

	/**price*/
	@ApiModelProperty(value = "price", position = 7)
	private java.math.BigDecimal price;

	/**amount*/
	@ApiModelProperty(value = "amount", position = 8)
	private java.math.BigDecimal amount;

	/**deal*/
	@ApiModelProperty(value = "deal", position = 9)
	private java.math.BigDecimal deal;

	/**fee*/
	@ApiModelProperty(value = "fee", position = 10)
	private java.math.BigDecimal fee;

	/**dealFee*/
	@ApiModelProperty(value = "dealFee", position = 11)
	private java.math.BigDecimal dealFee;


}

