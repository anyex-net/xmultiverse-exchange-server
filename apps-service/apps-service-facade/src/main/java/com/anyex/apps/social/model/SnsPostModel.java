/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.model;

import com.anyex.apps.social.entity.SnsPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 社交帖子
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(description = "社交帖子")
public class SnsPostModel extends SnsPost
{

	@ApiModelProperty(value = "是否已点赞 0否 1是")
	private java.lang.Integer isLike;

	@ApiModelProperty(value = "是否已关注作者 0否 1是")
	private java.lang.Integer isFollow;

	@ApiModelProperty(value = "是否和作者是好友 0否 1是")
	private java.lang.Integer isFriend;

	@ApiModelProperty(value = "账户昵称")
	private String accountName = "Anonymous";

	@ApiModelProperty(value = "头像URL")
	private String headUrl  = "Anonymous";

}

