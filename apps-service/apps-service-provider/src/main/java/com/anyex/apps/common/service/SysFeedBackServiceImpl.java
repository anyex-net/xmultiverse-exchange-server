/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysFeedBack;
import com.anyex.apps.common.mapper.SysFeedBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FeedBack 服务实现类
 * <p>File：FeedBackServiceImpl.java </p>
 * <p>Title: FeedBackServiceImpl </p>
 * <p>Description:FeedBackServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysFeedBackServiceImpl extends GenericServiceImpl<SysFeedBack> implements SysFeedBackService
{
    protected SysFeedBackMapper feedBackMapper;
    
    @Autowired
    public SysFeedBackServiceImpl(SysFeedBackMapper feedBackMapper)
    {
        super(feedBackMapper);
        this.feedBackMapper = feedBackMapper;
    }
}
