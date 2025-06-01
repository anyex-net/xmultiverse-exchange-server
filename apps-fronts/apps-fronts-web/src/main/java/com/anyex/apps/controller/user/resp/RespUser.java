/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息返回对象
 * <p>File：RespUser.java</p>
 * <p>Title: RespUser</p>
 * <p>Description:RespUser</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "用户信息返回对象")
public class RespUser implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**UID*/
	@NotNull(message = "UID不可为空")
	@ApiModelProperty(value = "UID", position = 1, required = true)
	private Long uid;

	/**用户名*/
	@NotEmpty(message = "用户名不可为空")
	@ApiModelProperty(value = "用户名", position = 2, required = true)
	private String userName;

	/**用户头像*/
	@NotEmpty(message = "用户头像不可为空")
	@ApiModelProperty(value = "用户头像", position = 3, required = true)
	private String avatar;

	/**登录密码*/
	@JsonIgnore
	@NotEmpty(message = "登录密码不可为空")
	@ApiModelProperty(value = "登录密码", position = 4, required = true)
	private String loginPwd;

	/**交易密码*/
	@ApiModelProperty(value = "交易密码", position = 5)
	private String tradePwd;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱", position = 6)
	private String email;

	/**国家地区*/
	@ApiModelProperty(value = "国家地区", position = 7)
	private String country;

	/**手机号*/
	@ApiModelProperty(value = "手机号", position = 8)
	private String mobileNo;

	/**Google验证器私钥*/
	@ApiModelProperty(value = "Google验证器私钥", position = 9)
	private String gaAuthKey;

	/**注册所在地或IP*/
	@ApiModelProperty(value = "注册所在地或IP", position = 10)
	private String location;

	/**状态(0:正常、1:冻结、2:注销)*/
	@NotNull(message = "状态(0:正常、1:冻结、2:注销)不可为空")
	@ApiModelProperty(value = "状态(0:正常、1:冻结、2:注销)", position = 11, required = true)
	private Integer state;

	/**解冻时间*/
	@ApiModelProperty(value = "解冻时间", position = 12)
	private Long thawTime;

	/**安全验证策略*/
	@ApiModelProperty(value = "安全验证策略(0密码、1短信、2GA、3短信或GA)", position = 13)
	private Integer securityPolicy;

	/**交易验证策略*/
	@ApiModelProperty(value = "交易验证策略", position = 14)
	private Integer tradePolicy;

//	/**邀请码*/
//	@ApiModelProperty(value = "邀请码", position = 15)
//	private String inviteCode;
//
//	/**推荐码*/
//	@ApiModelProperty(value = "推荐码", position = 16)
//	private String referralCode;
//
//	/**来源web、app*/
//	@ApiModelProperty(value = "来源web、app", position = 17)
//	private String source;
//
//	/**是否风评*/
//	@ApiModelProperty(value = "是否风评", position = 18)
//	private Integer riskEvaluation;

	/**认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)*/
	@ApiModelProperty(value = "认证状态(0:未认证、1:已认证个人KYC、2:已认证机构投资者、3:已认证机构SPV发起人)", position = 19)
	private Integer certState;

	/**语言*/
	@ApiModelProperty(value = "语言", position = 20)
	private String lang;

	/**本地货币*/
	@ApiModelProperty(value = "本地货币", position = 21)
	private String localCurrency;

	/**稳定币偏好*/
	@ApiModelProperty(value = "稳定币偏好", position = 22)
	private String stableCoinPreference;

	/**用户等级*/
	@ApiModelProperty(value = "用户等级", position = 22)
	private String userLevel;

//	/**备注*/
//	@ApiModelProperty(value = "备注", position = 23)
//	private String remark;
//
//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 26, required = true)
//	private Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 27)
//	private String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 28)
//	private Long updateTime;

}

