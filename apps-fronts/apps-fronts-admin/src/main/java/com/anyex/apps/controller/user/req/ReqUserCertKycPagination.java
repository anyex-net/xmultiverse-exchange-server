/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户认证个人KYC 分页请求对象
 * <p>File：ReqUserCertKyc.java</p>
 * <p>Title: ReqUserCertKyc</p>
 * <p>Description:ReqUserCertKyc</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户认证个人KYC分页请求对象")
public class ReqUserCertKycPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private java.lang.Long userId;

	/**姓氏*/
	@ApiModelProperty(value = "姓氏", position = 2)
	private java.lang.String surName;

	/**名字*/
	@ApiModelProperty(value = "名字", position = 3)
	private java.lang.String realName;

	/**国家地区*/
	@ApiModelProperty(value = "国家地区", position = 4)
	private java.lang.String region;

	/**证件类型*/
	@ApiModelProperty(value = "证件类型", position = 5)
	private java.lang.String passportType;

	/**证件号码*/
	@ApiModelProperty(value = "证件号码", position = 6)
	private java.lang.String passportNo;

	/**证件照片1*/
	@ApiModelProperty(value = "证件照片1", position = 7)
	private java.lang.String passportImg1;

	/**证件照片2*/
	@ApiModelProperty(value = "证件照片2", position = 8)
	private java.lang.String passportImg2;

	/**证件照片3*/
	@ApiModelProperty(value = "证件照片3", position = 9)
	private java.lang.String passportImg3;

	/**状态(0未审核、1审核通过、2审核拒绝)*/
	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 10)
	private java.lang.Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 11)
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 12)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 13)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 14)
	private java.lang.Long updateTime;

	/**复核人*/
	@ApiModelProperty(value = "复核人", position = 15)
	private java.lang.String checkBy;

	/**复核时间*/
	@ApiModelProperty(value = "复核时间", position = 16)
	private java.lang.Long checkTime;
}

