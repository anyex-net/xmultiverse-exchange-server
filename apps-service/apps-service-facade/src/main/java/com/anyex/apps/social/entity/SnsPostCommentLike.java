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
 * 社交帖子评论点赞 实体对象
 * <p>File：SnsPostCommentLike.java</p>
 * <p>Title: SnsPostCommentLike</p>
 * <p>Description:SnsPostCommentLike</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交帖子评论点赞")
public class SnsPostCommentLike extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**帖子Id*/
	@NotNull(message = "帖子Id不可为空")
	@ApiModelProperty(value = "帖子Id", required = true)
	private java.lang.Long postId;

	/**帖子评论Id*/
	@NotNull(message = "帖子评论Id不可为空")
	@ApiModelProperty(value = "帖子评论Id", required = true)
	private java.lang.Long postCommentId;

	/**点赞用户Id*/
	@NotNull(message = "点赞用户Id不可为空")
	@ApiModelProperty(value = "点赞用户Id", required = true)
	private java.lang.String likeUserId;

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

