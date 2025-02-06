/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.SignableEntity;
import com.anyex.apps.consts.CharsetConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.UnsupportedEncodingException;

/**
 * 账户表 实体对象
 * <p>File：Account.java</p>
 * <p>Title: Account</p>
 * <p>Description:Account</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户表")
public class Account extends SignableEntity
{
	private static final long serialVersionUID = 1L;

	/**账户编号*/
	@NotNull(message = "账户编号不可为空")
	@ApiModelProperty(value = "账户编号", required = true)
	private java.lang.Long unid;

	@NotNull(message = "IM userId不可为空")
	@ApiModelProperty(value = "IM userId", required = true)
	private java.lang.String userId;

	/**国家默认86(巴基斯坦92)*/
	@NotNull(message = "国家默认86(巴基斯坦92)不可为空")
	@ApiModelProperty(value = "国家默认86(巴基斯坦92)", required = true)
	private java.lang.String country;

	/**手机号码*/
	@ApiModelProperty(value = "手机号码")
	private java.lang.String mobile;

	/**账户昵称*/
	@ApiModelProperty(value = "账户昵称")
	private java.lang.String accountName;

	/**账户姓名*/
	@ApiModelProperty(value = "账户姓名")
	private java.lang.String realName;

	/**CNIC*/
	@ApiModelProperty(value = "CNIC")
	private java.lang.String cnic;

	/**登录密码*/
	@JsonIgnore
	@NotNull(message = "登录密码不可为空")
	@ApiModelProperty(value = "登录密码", required = true)
	private java.lang.String loginPwd;

	/**头像URL*/
	@NotNull(message = "头像URL不可为空")
	@ApiModelProperty(value = "头像URL", required = true)
	private java.lang.String headUrl;

	/**邮箱*/
	@NotNull(message = "邮箱不可为空")
	@ApiModelProperty(value = "邮箱", required = true)
	private java.lang.String email;

	/**生日*/
	@ApiModelProperty(value = "生日")
	private java.lang.String birth;

	/**性别(0男、1女)*/
	@ApiModelProperty(value = "性别(0男、1女)")
	private java.lang.Boolean gender;

	/**设备编号*/
	@ApiModelProperty(value = "设备编号")
	private java.lang.String deviceId;

	/**IP地址*/
	@ApiModelProperty(value = "IP地址")
	private java.lang.String ip;

	/**最新位置经度*/
	@ApiModelProperty(value = "最新位置经度")
	private java.lang.String lng;

	/**最新位置维度*/
	@ApiModelProperty(value = "最新位置维度")
	private java.lang.String lat;

	@ApiModelProperty(value = "城市")
	private java.lang.String city;

	/**邀请码*/
	@NotNull(message = "邀请码不可为空")
	@ApiModelProperty(value = "邀请码", required = true)
	private java.lang.String invitationCode;

	/**推荐码*/
	@ApiModelProperty(value = "推荐码")
	private java.lang.String referralCode;

	/**来源*/
	@ApiModelProperty(value = "来源")
	private java.lang.String source;

	/**状态(0:正常、1:冻结、2:注销)*/
	@NotNull(message = "状态(0:正常、1:冻结、2:注销)不可为空")
	@ApiModelProperty(value = "状态(0:正常、1:冻结、2:注销)", required = true)
	private java.lang.Integer status;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private java.lang.Long updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;


	private boolean online = false;

	@Override
	protected byte[] acquiresSignValue() throws UnsupportedEncodingException
	{
		StringBuffer sign = new StringBuffer(String.valueOf(id)).append(country);
		sign.append(unid).append(email);
		return sign.toString().getBytes(CharsetConst.CHARSET_UT);
	}
}

