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
 * 用户API 实体请求对象
 * <p>File：ReqUserApi.java</p>
 * <p>Title: ReqUserApi</p>
 * <p>Description:ReqUserApi</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户API请求对象")
public class ReqUserApi extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
//	/**用户ID*/
//	@NotNull(message = "用户ID不可为空")
//	@ApiModelProperty(value = "用户ID", position = 1, required = true)
//	private Long userId;

	/**密钥类型(0:只读、1:交易、2:提币)*/
	@NotNull(message = "密钥类型(0:只读、1:交易、2:提币)不可为空")
	@ApiModelProperty(value = "密钥类型(0:只读、1:交易、2:提币)", position = 2, required = true)
	private Integer keyType;

//	/**apiKey*/
//	@NotEmpty(message = "apiKey不可为空")
//	@ApiModelProperty(value = "apiKey", position = 3, required = true)
//	private String apiKey;
//
//	/**公钥*/
//	@NotEmpty(message = "公钥不可为空")
//	@ApiModelProperty(value = "公钥", position = 4, required = true)
//	private String pubKey;
//
//	/**私钥*/
//	@NotEmpty(message = "私钥不可为空")
//	@ApiModelProperty(value = "私钥", position = 5, required = true)
//	private String priKey;
//
//	/**过期时间*/
//	@NotNull(message = "过期时间不可为空")
//	@ApiModelProperty(value = "过期时间", position = 6, required = true)
//	private Long closeTime;

	/**ip地址*/
	@NotEmpty(message = "ip地址不可为空")
	@ApiModelProperty(value = "ip地址", position = 7, required = true)
	private String ipGroup;

//	/**状态(0不可用、1可用)*/
//	@NotNull(message = "状态(0不可用、1可用)不可为空")
//	@ApiModelProperty(value = "状态(0不可用、1可用)", position = 8, required = true)
//	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 9)
	private String remark;

//	/**创建时间*/
//	@NotNull(message = "创建时间不可为空")
//	@ApiModelProperty(value = "创建时间", position = 10, required = true)
//	private java.lang.Long createTime;
//
//	/**更新人*/
//	@ApiModelProperty(value = "更新人", position = 11)
//	private java.lang.String updateBy;
//
//	/**更新时间*/
//	@ApiModelProperty(value = "更新时间", position = 12)
//	private java.lang.Long updateTime;
}

