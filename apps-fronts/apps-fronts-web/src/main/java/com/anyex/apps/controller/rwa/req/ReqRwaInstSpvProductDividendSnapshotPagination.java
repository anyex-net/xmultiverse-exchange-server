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
 * RWA机构SPV产品投资者分红快照 分页请求对象
 * <p>File：ReqRwaInstSpvProductDividendSnapshot.java</p>
 * <p>Title: ReqRwaInstSpvProductDividendSnapshot</p>
 * <p>Description:ReqRwaInstSpvProductDividendSnapshot</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品投资者分红快照分页请求对象")
public class ReqRwaInstSpvProductDividendSnapshotPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private Long userId;

	/**机构投资者ID*/
	@ApiModelProperty(value = "机构投资者ID", position = 2)
	private Long instInvestorId;

	/**机构SPV产品ID*/
	@ApiModelProperty(value = "机构SPV产品ID", position = 3)
	private Long instSpvProductId;

	/**机构SPV产品分红记录编号*/
	@ApiModelProperty(value = "机构SPV产品分红记录编号", position = 4)
	private String instSpvProductDividendNo;

	/**链上钱包地址*/
	@ApiModelProperty(value = "链上钱包地址", position = 5)
	private String walletAddress;

	/**平台分成持币数量*/
	@ApiModelProperty(value = "平台分成持币数量", position = 6)
	private java.math.BigDecimal holdAmount;

	/**平台分成金额*/
	@ApiModelProperty(value = "平台分成金额", position = 7)
	private java.math.BigDecimal dividendAmount;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 8)
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 9)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 10)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 11)
	private Long updateTime;


}

