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
public class SnsPostLocationQueryModel extends SnsPost
{

	@ApiModelProperty(value = "访问者userId")
	private String viewerUserId;

	@ApiModelProperty(value = "访问者Lng")
	private String viewerLng;

	@ApiModelProperty(value = "访问者Lat")
	private String viewerLat;

	@ApiModelProperty(value = "距离（米）")
	private Integer distance;
}

