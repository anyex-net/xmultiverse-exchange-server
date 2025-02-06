/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.entity;

import com.anyex.apps.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社交粉丝(关注我的) 实体对象
 * <p>File：SnsFans.java</p>
 * <p>Title: SnsFans</p>
 * <p>Description:SnsFans</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交粉丝(关注我的)")
public class SnsFans extends GenericEntity
{
	private static final long serialVersionUID = 1L;

	// user1 关注 user2
	/**用户Id user2*/
	@NotEmpty(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", required = true)
	private String userId;

	/**关注者用户Id user1*/
	@NotEmpty(message = "关注者用户Id不可为空")
	@ApiModelProperty(value = "关注者用户Id", required = true)
	private String followerUserId;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private String remark;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;

	@ApiModelProperty(value = "是否已读",required = true)
	private Integer isRead;
}

