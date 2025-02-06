/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.openim.entity.ConversationLimit;
import com.anyex.apps.openim.model.ConversationLimitModel;

/**
 * 会话限制 服务接口
 * <p>File：ConversationLimitService.java </p>
 * <p>Title: ConversationLimitService </p>
 * <p>Description:ConversationLimitService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface ConversationLimitService extends GenericService<ConversationLimit>
{
    ConversationLimitModel canChartCheck(String conversationID, String fromUserId, String toUserId);

    void canChartRecord(String conversationID,String fromUserId,String toUserId);

}
