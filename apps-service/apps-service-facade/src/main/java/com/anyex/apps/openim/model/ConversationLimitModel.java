/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ConversationLimitModel
{
    @ApiModelProperty(value = "会话ID", position = 1  )
    private Boolean currentUserStatus;

    @ApiModelProperty(value = "会话ID", position = 2 )
    private Boolean allUserStatus;

}
