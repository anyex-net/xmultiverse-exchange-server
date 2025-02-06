/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户属性表 实体请求对象
 * <p>File：ReqAttribute.java</p>
 * <p>Title: ReqAttribute</p>
 * <p>Description:ReqAttribute</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户属性表请求对象")
public class ReqAttribute extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@NotEmpty(message = "用户ID不可为空")
	@ApiModelProperty(value = "用户ID", required = true)
	private String userId;

	/**账户名*/
	@NotEmpty(message = "账户名不可为空")
	@ApiModelProperty(value = "账户名", required = true)
	private String account;

	/**手机号*/
	@ApiModelProperty(value = "手机号")
	private String phoneNumber;

	/**手机区域*/
	@NotEmpty(message = "手机区域不可为空")
	@ApiModelProperty(value = "手机区域", required = true)
	private String areaCode;

	/**邮箱*/
	@NotEmpty(message = "邮箱不可为空")
	@ApiModelProperty(value = "邮箱", required = true)
	private String email;

	/**昵称*/
	@NotEmpty(message = "昵称不可为空")
	@ApiModelProperty(value = "昵称", required = true)
	private String nickname;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String faceUrl;

	/**性别*/
	@NotNull(message = "性别不可为空")
	@ApiModelProperty(value = "性别", required = true)
	private Short gender;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.util.Date createTime;

	/**修改时间*/
	@NotNull(message = "修改时间不可为空")
	@ApiModelProperty(value = "修改时间", required = true)
	private java.util.Date changeTime;

	/**生日*/
	@NotNull(message = "生日不可为空")
	@ApiModelProperty(value = "生日", required = true)
	private java.util.Date birthTime;

	/**级别*/
	@NotNull(message = "级别不可为空")
	@ApiModelProperty(value = "级别", required = true)
	private Integer level;

	/**震动提醒*/
	@NotNull(message = "震动提醒不可为空")
	@ApiModelProperty(value = "震动提醒", required = true)
	private Short allowVibration;

	/**消息提示*/
	@NotNull(message = "消息提示不可为空")
	@ApiModelProperty(value = "消息提示", required = true)
	private Short allowBeep;

	/**允许添加好友*/
	@NotNull(message = "允许添加好友不可为空")
	@ApiModelProperty(value = "允许添加好友", required = true)
	private Short allowAddFriend;

	/**全局接收消息选项*/
	@NotNull(message = "全局接收消息选项不可为空")
	@ApiModelProperty(value = "全局接收消息选项", required = true)
	private Short globalRecvMsgOpt;

	/**注册类型*/
	@NotNull(message = "注册类型不可为空")
	@ApiModelProperty(value = "注册类型", required = true)
	private Short registerType;


}

