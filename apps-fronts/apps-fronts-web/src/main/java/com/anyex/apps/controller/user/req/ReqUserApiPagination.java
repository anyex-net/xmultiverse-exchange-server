/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.user.req;

import com.anyex.apps.model.Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户API 分页请求对象
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
@ApiModel(description = "用户API分页请求对象")
public class ReqUserApiPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户ID*/
	@ApiModelProperty(value = "用户ID", position = 1)
	private Long userId;

	/**密钥类型(0:只读、1:交易、2:提币)*/
	@ApiModelProperty(value = "密钥类型(0:只读、1:交易、2:提币)", position = 2)
	private String keyType;

	/**apiKey*/
	@ApiModelProperty(value = "apiKey", position = 3)
	private String apiKey;

	/**公钥*/
	@ApiModelProperty(value = "公钥", position = 4)
	private String pubKey;

	/**私钥*/
	@ApiModelProperty(value = "私钥", position = 5)
	private String priKey;

	/**过期时间*/
	@ApiModelProperty(value = "过期时间", position = 6)
	private Long closeTime;

	/**ip地址*/
	@ApiModelProperty(value = "ip地址", position = 7)
	private String ipGroup;

	/**状态(0不可用、1可用)*/
	@ApiModelProperty(value = "状态(0不可用、1可用)", position = 8)
	private Integer state;

	/**备注*/
	@ApiModelProperty(value = "备注", position = 9)
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", position = 10)
	private Long createTime;

	/**更新人*/
	@ApiModelProperty(value = "更新人", position = 11)
	private String updateBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间", position = 12)
	private Long updateTime;
}

