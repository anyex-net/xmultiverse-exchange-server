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
 * RWA机构SPV公司 分页请求对象
 * <p>File：ReqRwaInstSpvCompany.java</p>
 * <p>Title: ReqRwaInstSpvCompany</p>
 * <p>Description:ReqRwaInstSpvCompany</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA机构SPV公司分页请求对象")
public class ReqRwaInstSpvCompanyPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@ApiModelProperty(value = "用户ID", position = 1)
//	private Long userId;

//	/**机构SPV发起人ID*/
//	@ApiModelProperty(value = "机构SPV发起人ID", position = 2)
//	private Long instSpvPromoterId;

	/**公司名称*/
	@ApiModelProperty(value = "公司名称", position = 3)
	private String spvCompanyName;

//	/**公司类型*/
//	@ApiModelProperty(value = "公司类型", position = 4)
//	private String spvCompanyType;
//
//	/**公司行业*/
//	@ApiModelProperty(value = "公司行业", position = 5)
//	private String spvCompanyIndustry;

	/**公司注册编号*/
	@ApiModelProperty(value = "公司注册编号", position = 6)
	private String spvCompanyRegistrNo;

//	/**公司注册证书图片*/
//	@ApiModelProperty(value = "公司注册证书图片", position = 7)
//	private String spvCompanyRegistrImg;
//
//	/**公司所在国家地区*/
//	@ApiModelProperty(value = "公司所在国家地区", position = 8)
//	private String spvCompanyCountry;

//	/**公司联系邮箱*/
//	@ApiModelProperty(value = "公司联系邮箱", position = 9)
//	private String spvCompanyEmail;
//
//	/**公司联系电话*/
//	@ApiModelProperty(value = "公司联系电话", position = 10)
//	private String spvCompanyMobileNo;
//
//	/**公司地址*/
//	@ApiModelProperty(value = "公司地址", position = 11)
//	private String spvCompanyAddress;
//
//	/**公司介绍*/
//	@ApiModelProperty(value = "公司介绍", position = 12)
//	private String spvCompanyDesc;

//	/**状态(0未审核、1审核通过、2审核拒绝)*/
//	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 13)
//	private String state;

//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 14)
//	private String remark;

}

