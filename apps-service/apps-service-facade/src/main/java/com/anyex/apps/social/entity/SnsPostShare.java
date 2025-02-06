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
 * 社交帖子分享 实体对象
 * <p>File：SnsPostShare.java</p>
 * <p>Title: SnsPostShare</p>
 * <p>Description:SnsPostShare</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交帖子分享")
public class SnsPostShare extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", required = true)
	private java.lang.String userId;

	/**帖子Id*/
	@NotNull(message = "帖子Id不可为空")
	@ApiModelProperty(value = "帖子Id", required = true)
	private java.lang.Long postId;

	/**分享用户Id*/
	@NotNull(message = "分享用户Id不可为空")
	@ApiModelProperty(value = "分享用户Id", required = true)
	private java.lang.String shareUserId;

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
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createTime;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private java.lang.Long updateTime;


}

