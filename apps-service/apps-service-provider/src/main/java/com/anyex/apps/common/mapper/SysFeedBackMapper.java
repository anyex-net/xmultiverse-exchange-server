/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysFeedBack;
import org.apache.ibatis.annotations.Mapper;

/**
 * FeedBack 持久层接口
 * <p>File：FeedBackMapper.java </p>
 * <p>Title: FeedBackMapper </p>
 * <p>Description:FeedBackMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysFeedBackMapper extends GenericMapper<SysFeedBack>
{
}
