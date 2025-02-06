/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.account.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "账户地址请求对象")
public class ReqAccountAddress extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**收件人姓名*/
	@NotNull(message = "收件人姓名不可为空")
	@ApiModelProperty(value = "收件人姓名", required = true)
	private String name;

	/**手机号码*/
	@NotNull(message = "手机号码不可为空")
	@ApiModelProperty(value = "手机号码", required = true)
	private String mobile;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**区域*/
	@NotNull(message = "区域不可为空")
	@ApiModelProperty(value = "区域", required = true)
	private String area;

	/**地址*/
	@NotNull(message = "地址不可为空")
	@ApiModelProperty(value = "地址", required = true)
	private String address;

	/**地标*/
	@NotNull(message = "地标不可为空")
	@ApiModelProperty(value = "地标", required = true)
	private String landmark;

	/**是否默认地址(0否、1是)*/
	@NotNull(message = "是否默认地址(0否、1是)不可为空")
	@ApiModelProperty(value = "是否默认地址(0否、1是)", required = true)
	private Boolean prime;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;
}

