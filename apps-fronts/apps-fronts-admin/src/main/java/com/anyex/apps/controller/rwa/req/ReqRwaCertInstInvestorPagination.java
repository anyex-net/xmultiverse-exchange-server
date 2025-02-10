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
 * RWA认证机构投资者 分页请求对象
 * <p>File：ReqRwaCertInstInvestor.java</p>
 * <p>Title: ReqRwaCertInstInvestor</p>
 * <p>Description:ReqRwaCertInstInvestor</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA认证机构投资者分页请求对象")
public class ReqRwaCertInstInvestorPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**代理人姓名*/
	@ApiModelProperty(value = "代理人姓名", position = 2)
	private java.lang.String agentName;

	/**代理人国家地区*/
	@ApiModelProperty(value = "代理人国家地区", position = 3)
	private java.lang.String agentRegion;

	/**代理人证件类型*/
	@ApiModelProperty(value = "代理人证件类型", position = 4)
	private java.lang.String agentPassportType;

	/**代理人证件号码*/
	@ApiModelProperty(value = "代理人证件号码", position = 5)
	private java.lang.String agentPassportNo;

	/**代理人证件照片1*/
	@ApiModelProperty(value = "代理人证件照片1", position = 6)
	private java.lang.String agentPassportImg1;

	/**代理人证件照片2*/
	@ApiModelProperty(value = "代理人证件照片2", position = 7)
	private java.lang.String agentPassportImg2;

	/**代理人证件照片3*/
	@ApiModelProperty(value = "代理人证件照片3", position = 8)
	private java.lang.String agentPassportImg3;

	/**代理人联系邮箱*/
	@ApiModelProperty(value = "代理人联系邮箱", position = 9)
	private java.lang.String agentEmail;

	/**代理人联系电话*/
	@ApiModelProperty(value = "代理人联系电话", position = 10)
	private java.lang.String agentMobileNo;

	/**代理人授权文件*/
	@ApiModelProperty(value = "代理人授权文件", position = 11)
	private java.lang.String agentAuthorizationFile;

	/**公司名称*/
	@ApiModelProperty(value = "公司名称", position = 12)
	private java.lang.String companyName;

	/**公司类型*/
	@ApiModelProperty(value = "公司类型", position = 13)
	private java.lang.String companyType;

	/**公司业务类型*/
	@ApiModelProperty(value = "公司业务类型", position = 14)
	private java.lang.String companyBusinessType;

	/**公司注册编号*/
	@ApiModelProperty(value = "公司注册编号", position = 15)
	private java.lang.String companyRegistrNo;

	/**公司注册证书图片*/
	@ApiModelProperty(value = "公司注册证书图片", position = 16)
	private java.lang.String companyRegistrImg;

	/**公司所在国家地区*/
	@ApiModelProperty(value = "公司所在国家地区", position = 17)
	private java.lang.String companyCountry;

	/**公司注册地址*/
	@ApiModelProperty(value = "公司注册地址", position = 18)
	private java.lang.String companyRegistrAddress;

	/**公司成立日期*/
	@ApiModelProperty(value = "公司成立日期", position = 19)
	private java.util.Date companyFoundedDate;

	/**公司持牌编号*/
	@ApiModelProperty(value = "公司持牌编号", position = 20)
	private java.lang.String companyLicenseNumber;

	/**公司监管机构*/
	@ApiModelProperty(value = "公司监管机构", position = 21)
	private java.lang.String companyRegulator;

	/**公司AML反洗钱证明*/
	@ApiModelProperty(value = "公司AML反洗钱证明", position = 22)
	private java.lang.String companyAmlCertificate;

	/**状态(0未审核、1审核通过、2审核拒绝)*/
	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 23)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 24)
	private java.lang.String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 25)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 26)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 27)
//	private java.lang.Long updateTime;
//
//	/**复核人*/
//	@ApiModelProperty(value = "复核人", position = 28)
//	private java.lang.String checkBy;
//
//	/**复核时间*/
//	@ApiModelProperty(value = "复核时间", position = 29)
//	private java.lang.Long checkTime;
}

