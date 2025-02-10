/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品分红记录 分页请求对象
 * <p>File：ReqRwaInstSpvProductDividend.java</p>
 * <p>Title: ReqRwaInstSpvProductDividend</p>
 * <p>Description:ReqRwaInstSpvProductDividend</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品分红记录分页请求对象")
public class ReqRwaInstSpvProductDividendPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**机构投资者ID*/
	@ApiModelProperty(value = "机构投资者ID", position = 2)
	private java.lang.Long instInvestorId;

	/**机构SPV产品ID*/
	@ApiModelProperty(value = "机构SPV产品ID", position = 3)
	private java.lang.Long instSpvProductId;

	/**分红币种*/
	@ApiModelProperty(value = "分红币种", position = 4)
	private java.lang.String dividendCurrency;

	/**分红金额*/
	@ApiModelProperty(value = "分红金额", position = 5)
	private java.math.BigDecimal dividendAmount;

	/**状态(成功success、处理中pending、失败failed)*/
	@ApiModelProperty(value = "状态(成功success、处理中pending、失败failed)", position = 6)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 7)
	private java.lang.String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 8)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 9)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 10)
//	private java.lang.Long updateTime;
}

