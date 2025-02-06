/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户属性表 分页请求对象
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
@ApiModel(description = "用户属性表分页请求对象")
public class ReqAttributePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID")
	private String userId;

	/**账户名*/
	@ApiModelProperty(value = "账户名")
	private String account;

	/**手机号*/
	@ApiModelProperty(value = "手机号")
	private String phoneNumber;

	/**手机区域*/
	@ApiModelProperty(value = "手机区域")
	private String areaCode;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**昵称*/
	@ApiModelProperty(value = "昵称")
	private String nickname;

	/**头像*/
	@ApiModelProperty(value = "头像")
	private String faceUrl;

	/**性别*/
	@ApiModelProperty(value = "性别")
	private Short gender;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.util.Date changeTime;

	/**生日*/
	@ApiModelProperty(value = "生日")
	private java.util.Date birthTime;

	/**级别*/
	@ApiModelProperty(value = "级别")
	private Integer level;

	/**震动提醒*/
	@ApiModelProperty(value = "震动提醒")
	private Short allowVibration;

	/**消息提示*/
	@ApiModelProperty(value = "消息提示")
	private Short allowBeep;

	/**允许添加好友*/
	@ApiModelProperty(value = "允许添加好友")
	private Short allowAddFriend;

	/**全局接收消息选项*/
	@ApiModelProperty(value = "全局接收消息选项")
	private Short globalRecvMsgOpt;

	/**注册类型*/
	@ApiModelProperty(value = "注册类型")
	private Short registerType;


}

