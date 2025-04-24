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
 * RWA机构SPV产品实际收入 分页请求对象
 * <p>File：ReqRwaInstSpvProductRealizedIncome.java</p>
 * <p>Title: ReqRwaInstSpvProductRealizedIncome</p>
 * <p>Description:ReqRwaInstSpvProductRealizedIncome</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品实际收入分页请求对象")
public class ReqRwaInstSpvProductRealizedIncomePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;

	/**机构SPV产品ID*/
	@ApiModelProperty(value = "机构SPV产品ID", position = 2)
	private Long instSpvProductId;

	/**收入分成日期*/
	@ApiModelProperty(value = "收入分成日期", position = 3)
	private java.util.Date incomeDistributionDate;

	/**收入金额*/
	@ApiModelProperty(value = "收入金额", position = 4)
	private java.math.BigDecimal incomeAmount;

	/**收入币种*/
	@ApiModelProperty(value = "收入币种", position = 5)
	private String incomeCurrency;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 6)
	private String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 7)
//	private Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 8)
//	private String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 9)
//	private Long updateTime;


}

