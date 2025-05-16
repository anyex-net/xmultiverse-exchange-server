/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RWA机构SPV产品投资者分红快照 实体对象
 * <p>File：RwaInstSpvProductDividendSnapshot.java</p>
 * <p>Title: RwaInstSpvProductDividendSnapshot</p>
 * <p>Description:RwaInstSpvProductDividendSnapshot</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品投资者分红快照")
public class RwaInstSpvProductDividendSnapshot extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**机构投资者ID*/
	@ApiModelProperty(value = "机构投资者ID", position = 2, required = true)
	private Long instInvestorId;

	/**机构SPV产品ID*/
	@NotNull(message = "机构SPV产品ID不可为空")
	@ApiModelProperty(value = "机构SPV产品ID", position = 3, required = true)
	private Long instSpvProductId;

	/**机构SPV产品分红记录编号*/
	@NotEmpty(message = "机构SPV产品分红记录编号不可为空")
	@ApiModelProperty(value = "机构SPV产品分红记录编号", position = 4, required = true)
	private String instSpvProductDividendNo;

	/**链上钱包地址*/
	@NotEmpty(message = "链上钱包地址不可为空")
	@ApiModelProperty(value = "链上钱包地址", position = 5, required = true)
	private String walletAddress;


	@NotNull(message = "链上持币数量不可为空")
	@ApiModelProperty(value = "链上持币数量", position = 6, required = true)
	private java.math.BigDecimal chainHoldAmount;

	@NotNull(message = "链上分成金额不可为空")
	@ApiModelProperty(value = "链上分成金额", position = 7, required = true)
	private java.math.BigDecimal chainDividendAmount;

	/**平台分成持币数量*/
	@NotNull(message = "平台分成持币数量不可为空")
	@ApiModelProperty(value = "平台分成持币数量", position = 8, required = true)
	private java.math.BigDecimal holdAmount;

	/**平台分成金额*/
	@NotNull(message = "平台分成金额不可为空")
	@ApiModelProperty(value = "平台分成金额", position = 9, required = true)
	private java.math.BigDecimal dividendAmount;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 10)
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 11, required = true)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 12)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 13)
	private Long updateTime;


}

