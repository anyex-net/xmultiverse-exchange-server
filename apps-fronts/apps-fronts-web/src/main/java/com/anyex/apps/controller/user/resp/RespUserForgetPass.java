/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.resp;

import com.anyex.apps.consts.CharsetConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * 用户信息 实体对象
 * <p>File：RespUserForgetPass.java</p>
 * <p>Title: RespUserForgetPass</p>
 * <p>Description:RespUserForgetPass</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
public class RespUserForgetPass implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**userId*/
	@NotNull(message = "userId不可为空")
	@ApiModelProperty(value = "用户Id", position = 0, required = true)
	private String userId;

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

	/**安全验证策略*/
	@ApiModelProperty(value = "安全验证策略(0密码、1短信、2GA、3短信或GA)", position = 10)
	private Integer securityPolicy;

	/**交易验证策略*/
	@ApiModelProperty(value = "交易验证策略", position = 11)
	private Integer tradePolicy;
}

