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
 * RWA认证机构SPV发起人 实体请求对象
 * <p>File：ReqRwaCertInstSpvPromoter.java</p>
 * <p>Title: ReqRwaCertInstSpvPromoter</p>
 * <p>Description:ReqRwaCertInstSpvPromoter</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "RWA认证机构SPV发起人请求对象")
public class ReqRwaCertInstSpvPromoter extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotNull(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", position = 1, required = true)
	private java.lang.Long userId;

	/**发起人公司名称*/
	@NotEmpty(message = "发起人公司名称不可为空")
	@ApiModelProperty(value = "发起人公司名称", position = 2, required = true)
	private java.lang.String promoterCoName;

	/**发起人公司类型*/
	@NotEmpty(message = "发起人公司类型不可为空")
	@ApiModelProperty(value = "发起人公司类型", position = 3, required = true)
	private java.lang.String promoterCoType;

	/**发起人公司注册编号*/
	@NotEmpty(message = "发起人公司注册编号不可为空")
	@ApiModelProperty(value = "发起人公司注册编号", position = 4, required = true)
	private java.lang.String promoterCoRegistrNo;

	/**发起人公司注册证书图片*/
	@NotEmpty(message = "发起人公司注册证书图片不可为空")
	@ApiModelProperty(value = "发起人公司注册证书图片", position = 5, required = true)
	private java.lang.String promoterCoRegistrImg;

	/**发起人公司所在国家地区*/
	@NotEmpty(message = "发起人公司所在国家地区不可为空")
	@ApiModelProperty(value = "发起人公司所在国家地区", position = 6, required = true)
	private java.lang.String promoterCoCountry;

	/**发起人公司联系邮箱*/
	@NotEmpty(message = "发起人公司联系邮箱不可为空")
	@ApiModelProperty(value = "发起人公司联系邮箱", position = 7, required = true)
	private java.lang.String promoterCoEmail;

	/**发起人公司联系电话*/
	@NotEmpty(message = "发起人公司联系电话不可为空")
	@ApiModelProperty(value = "发起人公司联系电话", position = 8, required = true)
	private java.lang.String promoterCoMobileNo;

	/**代理人姓名*/
	@NotEmpty(message = "代理人姓名不可为空")
	@ApiModelProperty(value = "代理人姓名", position = 9, required = true)
	private java.lang.String agentName;

	/**代理人国家地区*/
	@NotEmpty(message = "代理人国家地区不可为空")
	@ApiModelProperty(value = "代理人国家地区", position = 10, required = true)
	private java.lang.String agentRegion;

	/**代理人证件类型*/
	@NotEmpty(message = "代理人证件类型不可为空")
	@ApiModelProperty(value = "代理人证件类型", position = 11, required = true)
	private java.lang.String agentPassportType;

	/**代理人证件号码*/
	@NotEmpty(message = "代理人证件号码不可为空")
	@ApiModelProperty(value = "代理人证件号码", position = 12, required = true)
	private java.lang.String agentPassportNo;

	/**代理人证件照片1*/
	@NotEmpty(message = "代理人证件照片1不可为空")
	@ApiModelProperty(value = "代理人证件照片1", position = 13, required = true)
	private java.lang.String agentPassportImg1;

	/**代理人证件照片2*/
	@ApiModelProperty(value = "代理人证件照片2", position = 14)
	private java.lang.String agentPassportImg2;

	/**代理人证件照片3*/
	@ApiModelProperty(value = "代理人证件照片3", position = 15)
	private java.lang.String agentPassportImg3;

	/**代理人授权文件*/
	@NotEmpty(message = "代理人授权文件不可为空")
	@ApiModelProperty(value = "代理人授权文件", position = 16, required = true)
	private java.lang.String agentAuthorizationFile;

	/**代理人联系邮箱*/
	@NotEmpty(message = "代理人联系邮箱不可为空")
	@ApiModelProperty(value = "代理人联系邮箱", position = 17, required = true)
	private java.lang.String agentEmail;

	/**代理人联系电话*/
	@NotEmpty(message = "代理人联系电话不可为空")
	@ApiModelProperty(value = "代理人联系电话", position = 18, required = true)
	private java.lang.String agentMobileNo;

	/**状态(0未审核、1审核通过、2审核拒绝)*/
	@NotEmpty(message = "状态(0未审核、1审核通过、2审核拒绝)不可为空")
	@ApiModelProperty(value = "状态(0未审核、1审核通过、2审核拒绝)", position = 19, required = true)
	private java.lang.String state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 20)
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", position = 21, required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 22)
	private java.lang.String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 23)
	private java.lang.Long updateTime;

	/**复核人*/
	@ApiModelProperty(value = "复核人", position = 24)
	private java.lang.String checkBy;

	/**复核时间*/
	@ApiModelProperty(value = "复核时间", position = 25)
	private java.lang.Long checkTime;
}

