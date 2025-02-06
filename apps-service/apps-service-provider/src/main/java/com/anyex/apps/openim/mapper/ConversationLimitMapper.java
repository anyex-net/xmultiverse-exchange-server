/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.openim.entity.ConversationLimit;

/**
 * 会话限制 持久层接口
 * <p>File：ConversationLimitMapper.java </p>
 * <p>Title: ConversationLimitMapper </p>
 * <p>Description:ConversationLimitMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ConversationLimitMapper extends GenericMapper<ConversationLimit>
{

}
