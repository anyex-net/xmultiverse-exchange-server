/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.rwa.resp;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * RWA机构SPV公司 实体请求对象
 * <p>File：ReqRwaInstSpvCompany.java</p>
 * <p>Title: ReqRwaInstSpvCompany</p>
 * <p>Description:ReqRwaInstSpvCompany</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "RWA机构SPV公司返回信息")
public class RespRwaInstSpvCompany extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	/**公司名称*/
	@NotEmpty(message = "公司名称不可为空")
	@ApiModelProperty(value = "公司名称", position = 3, required = true)
	private String spvCompanyName;
}

