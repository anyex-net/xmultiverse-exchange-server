/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 社交帖子
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "社交帖子评论")
public class SnsPostCommentModel
{
	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "是否已点赞 0否 1是")
	private Integer isLike;

	@ApiModelProperty(value = "帖子ID")
	private Long postId;

	@ApiModelProperty(value = "评论ID")
	private Long commentId;

	@ApiModelProperty(value = "评论时间")
	private Long createTime;

	@ApiModelProperty(value = "评论数量")
	private Integer commentLikeNum;

	@ApiModelProperty(value = "被评论的评论内容")
	private String topCommentContent;

	@ApiModelProperty(value = "评论内容")
	private String commentContent;

	@ApiModelProperty(value = "评论用户ID")
	private String commentUserId;

	@ApiModelProperty(value = "评论用户名")
	private String commentAccountName;

	@ApiModelProperty(value = "评论用户头像")
	private String commentHeadUrl;

	@ApiModelProperty(value = "发帖用户ID")
	private String postUserId;

	@ApiModelProperty(value = "发帖用户名")
	private String postUserAccountName;

	@ApiModelProperty(value = "发帖用户头像")
	private String postUserHeadUrl;

	@ApiModelProperty(value = "被评论用户ID")
	private String topUserId;

	@ApiModelProperty(value = "被评论用户名")
	private String topUserAccountName;

	@ApiModelProperty(value = "被评论用户头像")
	private String topUserHeadUrl;

	@ApiModelProperty(value = "是否有子评论")
	private Boolean hasSubComments;

	@ApiModelProperty(value = "子评论数量")
	private Integer subCommentsCnt;

	@ApiModelProperty(value = "0未读 1已读")
	private Integer isRead;

	/**帖子文本内容*/
	@ApiModelProperty(value = "帖子文本内容")
	private java.lang.String postTextContent;

	/**帖子图片URL*/
	@ApiModelProperty(value = "帖子图片URL")
	private java.lang.String postImageUrl;

	/**0匿名、1公开*/
	@ApiModelProperty(value = "0匿名、1公开")
	private java.lang.Integer openness;

	/**0公开、1仅限好友、2仅限粉丝、3仅限自己*/
	@ApiModelProperty(value = "0公开、1仅限好友、2仅限粉丝、3仅限自己")
	private java.lang.Integer viewer;

	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

}

