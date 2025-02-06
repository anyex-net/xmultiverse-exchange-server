/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 账户更新请求对象
 * <p>File：ReqAccountUpdate.java</p>
 * <p>Title: ReqAccountUpdate</p>
 * <p>Description: ReqAccountUpdate</p>
 * <p>Copyright: Copyright (c) 2017/7/5</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ReqAccountUpdate implements Serializable
{
	/**国家默认86(巴基斯坦92)*/
	@ApiModelProperty(value = "国家默认86(巴基斯坦92)")
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
	@ApiModelProperty(value = "登录密码")
	private java.lang.String loginPwd;

	/**头像URL*/
	@ApiModelProperty(value = "头像URL")
	private java.lang.String headUrl;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private java.lang.String email;

	/**生日*/
	@ApiModelProperty(value = "生日")
	private java.lang.String birth;

	/**性别(0男、1女)*/
	@ApiModelProperty(value = "性别(0男、1女)")
	private java.lang.Boolean gender;

	/**最新位置经度*/
	@ApiModelProperty(value = "最新位置经度")
	private java.lang.String lng;

	/**最新位置维度*/
	@ApiModelProperty(value = "最新位置维度")
	private java.lang.String lat;
}

