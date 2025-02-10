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
 * 用户信息 分页请求对象
 * <p>File：ReqUser.java</p>
 * <p>Title: ReqUser</p>
 * <p>Description:ReqUser</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户信息分页请求对象")
public class ReqUserPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**UID*/
	@ApiModelProperty(value = "UID", position = 1)
	private java.lang.Long uid;

	/**用户名*/
	@ApiModelProperty(value = "用户名", position = 2)
	private java.lang.String userName;

	/**用户头像*/
	@ApiModelProperty(value = "用户头像", position = 3)
	private java.lang.String avatar;

	/**登录密码*/
	@ApiModelProperty(value = "登录密码", position = 4)
	private java.lang.String loginPwd;

	/**交易密码*/
	@ApiModelProperty(value = "交易密码", position = 5)
	private java.lang.String tradePwd;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱", position = 6)
	private java.lang.String email;

	/**国家地区*/
	@ApiModelProperty(value = "国家地区", position = 7)
	private java.lang.String country;

	/**手机号*/
	@ApiModelProperty(value = "手机号", position = 8)
	private java.lang.String mobileNo;

	/**Google验证器私钥*/
	@ApiModelProperty(value = "Google验证器私钥", position = 9)
	private java.lang.String gaAuthKey;

	/**注册所在地或IP*/
	@ApiModelProperty(value = "注册所在地或IP", position = 10)
	private java.lang.String location;

	/**状态(0:正常、1:冻结、2:注销)*/
	@ApiModelProperty(value = "状态(0:正常、1:冻结、2:注销)", position = 11)
	private java.lang.String state;

	/**解冻时间*/
	@ApiModelProperty(value = "解冻时间", position = 12)
	private java.lang.Long thawTime;

	/**安全验证策略*/
	@ApiModelProperty(value = "安全验证策略", position = 13)
	private java.lang.Integer securityPolicy;

	/**交易验证策略*/
	@ApiModelProperty(value = "交易验证策略", position = 14)
	private java.lang.Integer tradePolicy;

	/**邀请码*/
	@ApiModelProperty(value = "邀请码", position = 15)
	private java.lang.String inviteCode;

	/**推荐码*/
	@ApiModelProperty(value = "推荐码", position = 16)
	private java.lang.String referralCode;

	/**来源web、app*/
	@ApiModelProperty(value = "来源web、app", position = 17)
	private java.lang.String source;

	/**是否风评*/
	@ApiModelProperty(value = "是否风评", position = 18)
	private java.lang.Integer riskEvaluation;

	/**认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)*/
	@ApiModelProperty(value = "认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)", position = 19)
	private java.lang.Integer certState;

	/**语言*/
	@ApiModelProperty(value = "语言", position = 20)
	private java.lang.String lang;

	/**本地货币*/
	@ApiModelProperty(value = "本地货币", position = 21)
	private java.lang.String localCurrency;

	/**稳定币偏好*/
	@ApiModelProperty(value = "稳定币偏好", position = 22)
	private java.lang.String stableCoinPreference;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 23)
	private java.lang.String remark;

//	/**创建时间*/
//	@ApiModelProperty(value = "创建时间", position = 26)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 27)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 28)
//	private java.lang.Long updateTime;
}

