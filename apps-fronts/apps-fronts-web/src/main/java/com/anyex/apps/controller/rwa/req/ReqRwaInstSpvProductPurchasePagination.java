/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品申购记录 分页请求对象
 * <p>File：ReqRwaInstSpvProductPurchase.java</p>
 * <p>Title: ReqRwaInstSpvProductPurchase</p>
 * <p>Description:ReqRwaInstSpvProductPurchase</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品申购记录分页请求对象")
public class ReqRwaInstSpvProductPurchasePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;
//
//	/**机构投资者ID*/
//	@ApiModelProperty(value = "机构投资者ID", position = 2)
//	private Long instInvestorId;

	/**机构SPV产品ID*/
	@ApiModelProperty(value = "机构SPV产品ID", position = 3)
	private Long instSpvProductId;

	/**申购币种*/
	@ApiModelProperty(value = "申购币种", position = 4)
	private String purchaseCurrency;

//	/**申购价格*/
//	@ApiModelProperty(value = "申购价格", position = 5)
//	private java.math.BigDecimal purchasePrice;
//
//	/**申购数量*/
//	@ApiModelProperty(value = "申购数量", position = 6)
//	private java.math.BigDecimal purchaseAmount;

	/**状态(成功success、处理中pending、失败failed)*/
	@ApiModelProperty(value = "状态(成功success、处理中pending、失败failed)", position = 7)
	private String state;

//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 8)
//	private String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 9)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 10)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 11)
//	private java.lang.Long updateTime;
}

