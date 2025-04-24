/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品实际收入 实体请求对象
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
@ApiModel(description = "RWA机构SPV产品实际收入请求对象")
public class ReqRwaInstSpvProductRealizedIncome extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**机构SPV产品ID*/
	@NotNull(message = "机构SPV产品ID不可为空")
	@ApiModelProperty(value = "机构SPV产品ID", position = 2, required = true)
	private Long instSpvProductId;

	/**收入分成日期*/
	@NotNull(message = "收入分成日期不可为空")
	@ApiModelProperty(value = "收入分成日期", position = 3, required = true)
	private java.util.Date incomeDistributionDate;

	/**收入金额*/
	@NotNull(message = "收入金额不可为空")
	@ApiModelProperty(value = "收入金额", position = 4, required = true)
	private java.math.BigDecimal incomeAmount;

	/**收入币种*/
	@NotEmpty(message = "收入币种不可为空")
	@ApiModelProperty(value = "收入币种", position = 5, required = true)
	private String incomeCurrency;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 6)
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 7, required = true)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 8)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 9)
	private Long updateTime;


}

