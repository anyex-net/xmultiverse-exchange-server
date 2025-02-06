/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.entity;

import javax.validation.constraints.NotNull;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 账户地址表 实体对象
 * <p>File：AccountAddress.java</p>
 * <p>Title: AccountAddress</p>
 * <p>Description:AccountAddress</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户地址表")
public class AccountAddress extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	private java.lang.Long accountId;

	/**收件人姓名*/
	@NotNull(message = "收件人姓名不可为空")
	@ApiModelProperty(value = "收件人姓名", required = true)
	private java.lang.String name;

	/**手机号码*/
	@NotNull(message = "手机号码不可为空")
	@ApiModelProperty(value = "手机号码", required = true)
	private java.lang.String mobile;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private java.lang.String email;

	/**区域*/
	@NotNull(message = "区域不可为空")
	@ApiModelProperty(value = "区域", required = true)
	private java.lang.String area;

	/**地址*/
	@NotNull(message = "地址不可为空")
	@ApiModelProperty(value = "地址", required = true)
	private java.lang.String address;

	/**地标*/
	@NotNull(message = "地标不可为空")
	@ApiModelProperty(value = "地标", required = true)
	private java.lang.String landmark;

	/**是否默认地址(0否、1是)*/
	@NotNull(message = "是否默认地址(0否、1是)不可为空")
	@ApiModelProperty(value = "是否默认地址(0否、1是)", required = true)
	private java.lang.Boolean prime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;
}

