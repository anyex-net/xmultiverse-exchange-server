/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.news.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资讯点赞 实体请求对象
 * <p>File：ReqNewsLike.java</p>
 * <p>Title: ReqNewsLike</p>
 * <p>Description:ReqNewsLike</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资讯点赞请求对象")
public class ReqNewsLike extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**资讯Id*/
	@NotNull(message = "资讯Id不可为空")
	@ApiModelProperty(value = "资讯Id", required = true)
	private java.lang.Long newsId;

	/**点赞用户Id*/
	@NotNull(message = "点赞用户Id不可为空")
	@ApiModelProperty(value = "点赞用户Id", required = true)
	private java.lang.Long likeAccountId;

	/**位置经度*/
	@ApiModelProperty(value = "位置经度")
	private java.lang.String lng;

	/**位置维度*/
	@ApiModelProperty(value = "位置维度")
	private java.lang.String lat;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;
}

