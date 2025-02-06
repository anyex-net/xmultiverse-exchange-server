/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim.req;

import com.anyex.apps.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 检查与他人会话限制
 * <p>File：ReqConversationLimit.java</p>
 * <p>Title: ReqConversationLimit</p>
 * <p>Description:ReqConversationLimit</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "检查与他人会话限制")
public class ReqConversationLimit
{
    @ApiModelProperty(value = "会话ID", position = 1 , required = true )
    @NotBlank(message = "会话ID不能为空")
    private String conversationId;

    @ApiModelProperty(value = "会话ID", position = 1 , required = true )
    @NotBlank(message = "会话ID不能为空")
    private String toUserId;

}
