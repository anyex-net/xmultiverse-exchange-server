/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.anyex.apps.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社交帖子分享 分页请求对象
 * <p>File：ReqSnsPostShare.java</p>
 * <p>Title: ReqSnsPostShare</p>
 * <p>Description:ReqSnsPostShare</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交帖子分享分页请求对象")
public class ReqSnsPostSharePagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**用户Id*/
	@ApiModelProperty(value = "用户Id")
	private java.lang.Long userId;

	/**帖子Id*/
	@ApiModelProperty(value = "帖子Id")
	private java.lang.Long postId;

	/**分享用户Id*/
	@ApiModelProperty(value = "分享用户Id")
	private java.lang.Long shareUserId;

	/**位置经度*/
	@ApiModelProperty(value = "位置经度")
	private java.lang.String lng;

	/**位置维度*/
	@ApiModelProperty(value = "位置维度")
	private java.lang.String lat;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;


}

