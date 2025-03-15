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
 * user_deal_history_example 分页请求对象
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
@ApiModel(description = "user_deal_history_example分页请求对象")
public class ReqUserDealHistoryPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**time*/
	@ApiModelProperty(value = "time", position = 1)
	private Double time;

	/**userId*/
	@ApiModelProperty(value = "userId", position = 2)
	private Integer userId;

	/**market*/
	@ApiModelProperty(value = "market", position = 3)
	private String market;

	/**dealId*/
	@ApiModelProperty(value = "dealId", position = 4)
	private Long dealId;

	/**orderId*/
	@ApiModelProperty(value = "orderId", position = 5)
	private Long orderId;

	/**dealOrderId*/
	@ApiModelProperty(value = "dealOrderId", position = 6)
	private Long dealOrderId;

	/**side*/
	@ApiModelProperty(value = "side", position = 7)
	private Short side;

	/**role*/
	@ApiModelProperty(value = "role", position = 8)
	private Short role;

	/**price*/
	@ApiModelProperty(value = "price", position = 9)
	private java.math.BigDecimal price;

	/**amount*/
	@ApiModelProperty(value = "amount", position = 10)
	private java.math.BigDecimal amount;

	/**deal*/
	@ApiModelProperty(value = "deal", position = 11)
	private java.math.BigDecimal deal;

	/**fee*/
	@ApiModelProperty(value = "fee", position = 12)
	private java.math.BigDecimal fee;

	/**dealFee*/
	@ApiModelProperty(value = "dealFee", position = 13)
	private java.math.BigDecimal dealFee;


}

