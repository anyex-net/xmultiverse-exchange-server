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
 * RWA机构SPV产品 分页请求对象
 * <p>File：ReqRwaInstSpvProduct.java</p>
 * <p>Title: ReqRwaInstSpvProduct</p>
 * <p>Description:ReqRwaInstSpvProduct</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV产品分页请求对象")
public class ReqRwaInstSpvProductPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**机构SPV发起人ID*/
	@ApiModelProperty(value = "机构SPV发起人ID", position = 2)
	private java.lang.Long instSpvPromoterId;

	/**机构SPV公司ID*/
	@ApiModelProperty(value = "机构SPV公司ID", position = 3)
	private java.lang.Long instSpvCompanyId;

	/**产品编号*/
	@ApiModelProperty(value = "产品编号", position = 4)
	private java.lang.String productNo;

	/**代币名称*/
	@ApiModelProperty(value = "代币名称", position = 5)
	private java.lang.String tokenName;

	/**代币Logo*/
	@ApiModelProperty(value = "代币Logo", position = 6)
	private java.lang.String tokenLogo;

	/**代币发行数量*/
	@ApiModelProperty(value = "代币发行数量", position = 7)
	private java.math.BigDecimal tokenIssueNumber;

	/**募集币种*/
	@ApiModelProperty(value = "募集币种", position = 8)
	private java.lang.String raiseCurrency;

	/**募集金额*/
	@ApiModelProperty(value = "募集金额", position = 9)
	private java.math.BigDecimal raiseAmount;

	/**资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额*/
	@ApiModelProperty(value = "资产期末估值, 预估收益率=(期末估值-募集金额)/募集金额", position = 10)
	private java.math.BigDecimal assetEndValuation;

	/**发行天数*/
	@ApiModelProperty(value = "发行天数", position = 11)
	private java.lang.Integer issueDays;

	/**申购开始日期*/
	@ApiModelProperty(value = "申购开始日期", position = 12)
	private java.util.Date purchaseStartDate;

	/**申购结束日期*/
	@ApiModelProperty(value = "申购结束日期", position = 13)
	private java.util.Date purchaseEndDate;

	/**运营开始日期*/
	@ApiModelProperty(value = "运营开始日期", position = 14)
	private java.util.Date operationStarDate;

	/**运营结束日期*/
	@ApiModelProperty(value = "运营结束日期", position = 15)
	private java.util.Date operationEndDate;

	/**募集成立条件比例*/
	@ApiModelProperty(value = "募集成立条件比例", position = 16)
	private java.math.BigDecimal raiseEstablishedRatio;

	/**公司资产名称*/
	@ApiModelProperty(value = "公司资产名称", position = 17)
	private java.lang.String companyAssetName;

	/**公司资产描述*/
	@ApiModelProperty(value = "公司资产描述", position = 18)
	private java.lang.String companyAssetDesc;

	/**公司募集用途*/
	@ApiModelProperty(value = "公司募集用途", position = 19)
	private java.lang.String companyRaiseUse;

	/**分红比例*/
	@ApiModelProperty(value = "分红比例", position = 20)
	private java.math.BigDecimal dividendRatio;

	/**分红频率*/
	@ApiModelProperty(value = "分红频率", position = 21)
	private java.lang.String dividendFrequency;

	/**分红冻结天数*/
	@ApiModelProperty(value = "分红冻结天数", position = 22)
	private java.lang.Integer dividendFreezeDays;

	/**募集对应保证金比例*/
	@ApiModelProperty(value = "募集对应保证金比例", position = 23)
	private java.math.BigDecimal raiseMarginRatio;

	/**募集对应保证金状态(0未缴、1已缴)*/
	@ApiModelProperty(value = "募集对应保证金状态(0未缴、1已缴)", position = 24)
	private java.lang.Integer raiseMarginState;

	/**已申购总数量*/
	@ApiModelProperty(value = "已申购总数量", position = 25)
	private java.math.BigDecimal purchasedSumAmount;

	/**状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)*/
	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝、3合约部署中、4待开放、5申购中、6发行失败、7运营中、8已到期)", position = 26)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 27)
	private java.lang.String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 28)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 29)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 30)
//	private java.lang.Long updateTime;
//
//	/**复核人*/
//	@ApiModelProperty(value = "复核人", position = 31)
//	private java.lang.String checkBy;
//
//	/**复核时间*/
//	@ApiModelProperty(value = "复核时间", position = 32)
//	private java.lang.Long checkTime;
}

