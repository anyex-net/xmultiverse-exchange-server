/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * RWA机构SPV公司 实体请求对象
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
@ApiModel(description = "RWA机构SPV公司请求对象")
public class ReqRwaInstSpvCompany extends GenericEntity
{
	private static final long serialVersionUID = 1L;

//	/**用户ID*/
//	@NotNull(message = "用户ID不可为空")
//	@ApiModelProperty(value = "用户ID", position = 1, required = true)
//	private Long userId;

//	/**机构SPV发起人ID*/
//	@NotNull(message = "机构SPV发起人ID不可为空")
//	@ApiModelProperty(value = "机构SPV发起人ID", position = 2, required = true)
//	private Long instSpvPromoterId;

	/**公司名称*/
	@NotEmpty(message = "公司名称不可为空")
	@ApiModelProperty(value = "公司名称", position = 3, required = true)
	private String spvCompanyName;

	/**公司类型*/
	@NotEmpty(message = "公司类型不可为空")
	@ApiModelProperty(value = "公司类型", position = 4, required = true)
	private String spvCompanyType;

	/**公司行业*/
	@NotEmpty(message = "公司行业不可为空")
	@ApiModelProperty(value = "公司行业", position = 5, required = true)
	private String spvCompanyIndustry;

	/**公司注册编号*/
	@NotEmpty(message = "公司注册编号不可为空")
	@ApiModelProperty(value = "公司注册编号", position = 6, required = true)
	private String spvCompanyRegistrNo;

	/**公司注册证书图片*/
	@NotEmpty(message = "公司注册证书图片不可为空")
	@ApiModelProperty(value = "公司注册证书图片", position = 7, required = true)
	private String spvCompanyRegistrImg;

	/**公司所在国家地区*/
	@NotEmpty(message = "公司所在国家地区不可为空")
	@ApiModelProperty(value = "公司所在国家地区", position = 8, required = true)
	private String spvCompanyCountry;

	/**公司联系邮箱*/
	@NotEmpty(message = "公司联系邮箱不可为空")
	@ApiModelProperty(value = "公司联系邮箱", position = 9, required = true)
	private String spvCompanyEmail;

	/**公司联系电话*/
	@NotEmpty(message = "公司联系电话不可为空")
	@ApiModelProperty(value = "公司联系电话", position = 10, required = true)
	private String spvCompanyMobileNo;

	/**公司地址*/
	@NotEmpty(message = "公司地址不可为空")
	@ApiModelProperty(value = "公司地址", position = 11, required = true)
	private String spvCompanyAddress;

	/**公司介绍*/
	@NotEmpty(message = "公司介绍不可为空")
	@ApiModelProperty(value = "公司介绍", position = 12, required = true)
	private String spvCompanyDesc;

//	/**状态(0未审核、1审核通过、2审核拒绝)*/
//	@NotEmpty(message = "状态(0未审核、1审核通过、2审核拒绝)不可为空")
//	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 13, required = true)
//	private String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 14)
	private String remark;

//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 15, required = true)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 16)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 17)
//	private java.lang.Long updateTime;
//
//	/**复核人*/
//	@ApiModelProperty(value = "复核人", position = 18)
//	private java.lang.String checkBy;
//
//	/**复核时间*/
//	@ApiModelProperty(value = "复核时间", position = 19)
//	private java.lang.Long checkTime;
}

