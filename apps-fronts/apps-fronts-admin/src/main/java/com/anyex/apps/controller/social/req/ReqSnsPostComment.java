/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.social.req;

import com.anyex.apps.bean.GenericEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 社交帖子评论 实体请求对象
 * <p>File：ReqSnsPostComment.java</p>
 * <p>Title: ReqSnsPostComment</p>
 * <p>Description:ReqSnsPostComment</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "社交帖子评论请求对象")
public class ReqSnsPostComment extends GenericEntity
{
	private static final long serialVersionUID = 1L;
	
	/**用户Id*/
	@NotNull(message = "用户Id不可为空")
	@ApiModelProperty(value = "用户Id", required = true)
	private java.lang.Long userId;

	/**帖子Id*/
	@NotNull(message = "帖子Id不可为空")
	@ApiModelProperty(value = "帖子Id", required = true)
	private java.lang.Long postId;

	/**评论用户Id*/
	@NotNull(message = "评论用户Id不可为空")
	@ApiModelProperty(value = "评论用户Id", required = true)
	private java.lang.Long commentUserId;

	/**评论内容*/
	@NotEmpty(message = "评论内容不可为空")
	@ApiModelProperty(value = "评论内容", required = true)
	private java.lang.String commentContent;

	/**评论点赞数量*/
	@ApiModelProperty(value = "评论点赞数量")
	private java.lang.Integer commentLikeNum;

	/**父级评论的ID(如果是根评论则为NULL)*/
	@ApiModelProperty(value = "父级评论的ID(如果是根评论则为NULL)")
	private java.lang.String replyTo;

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

