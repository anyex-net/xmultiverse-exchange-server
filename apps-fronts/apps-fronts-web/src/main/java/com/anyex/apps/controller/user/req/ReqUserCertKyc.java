/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户认证个人KYC 实体请求对象
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
@ApiModel(description = "用户认证个人KYC请求对象")
public class ReqUserCertKyc extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private Long userId;

	/**姓氏*/
	@NotEmpty(message = "姓氏不可为空")
	@ApiModelProperty(value = "姓氏", position = 2, required = true)
	private String surName;

	/**名字*/
	@NotEmpty(message = "名字不可为空")
	@ApiModelProperty(value = "名字", position = 3, required = true)
	private String realName;

	/**国家地区*/
	@NotEmpty(message = "国家地区不可为空")
	@ApiModelProperty(value = "国家地区", position = 4, required = true)
	private String region;

	/**证件类型*/
	@NotEmpty(message = "证件类型不可为空")
	@ApiModelProperty(value = "证件类型", position = 5, required = true)
	private String passportType;

	/**证件号码*/
	@NotEmpty(message = "证件号码不可为空")
	@ApiModelProperty(value = "证件号码", position = 6, required = true)
	private String passportNo;

	/**证件照片1*/
	@NotEmpty(message = "证件照片1不可为空")
	@ApiModelProperty(value = "证件照片1", position = 7, required = true)
	private String passportImg1;

	/**证件照片2*/
	@NotEmpty(message = "证件照片2不可为空")
	@ApiModelProperty(value = "证件照片2", position = 8, required = true)
	private String passportImg2;

	/**证件照片3*/
	@NotEmpty(message = "证件照片3不可为空")
	@ApiModelProperty(value = "证件照片3", position = 9, required = true)
	private String passportImg3;


	/**备注*/
	@ApiModelProperty(value = "备注", position = 11)
	private String remark;
}

