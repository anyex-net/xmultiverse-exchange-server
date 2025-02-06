/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
@ApiModel(description = "全群禁言/解除禁言")
public class ReqGroupMute
{
	@NotNull
	private String groupID;

	@NotNull
	private Boolean status;
}

