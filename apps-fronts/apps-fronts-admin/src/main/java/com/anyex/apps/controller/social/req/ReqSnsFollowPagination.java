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
 * 社交关注(我关注的) 分页请求对象
 * <p>File：ReqSnsFollow.java</p>
 * <p>Title: ReqSnsFollow</p>
 * <p>Description:ReqSnsFollow</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交关注(我关注的)分页请求对象")
public class ReqSnsFollowPagination extends Pagination
{
	private static final long serialVersionUID = 1L;
	
	/**关注者用户Id*/
	@ApiModelProperty(value = "关注者用户Id")
	private String userId;

	/**被关注者用户Id*/
	@ApiModelProperty(value = "被关注者用户Id")
	private String followedUserId;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;


}

