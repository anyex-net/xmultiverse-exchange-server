/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.model;

import com.anyex.apps.social.entity.SnsPost;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * 社交点赞
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@ApiModel(description = "社交帖子点赞")
public class SnsPostLikeModel extends SnsPost
{
	@ApiModelProperty(value = "0未读 1已读")
	private Integer isRead;

	@ApiModelProperty(value = "点赞用户userId")
	private String likeUserId;

	@ApiModelProperty(value = "点赞账户")
	private String likeUserName;

	@ApiModelProperty(value = "点赞账户头像")
	private String likeUserHeadUrl;

	@ApiModelProperty(value = "点赞Id")
	private Long likeId;



}

