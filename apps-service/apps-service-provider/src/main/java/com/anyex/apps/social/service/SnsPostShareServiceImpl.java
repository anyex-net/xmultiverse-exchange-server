/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsPostShare;
import com.anyex.apps.social.mapper.SnsPostShareMapper;

/**
 * 社交帖子分享 服务实现类
 * <p>File：SnsPostShareServiceImpl.java </p>
 * <p>Title: SnsPostShareServiceImpl </p>
 * <p>Description:SnsPostShareServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsPostShareServiceImpl extends GenericServiceImpl<SnsPostShare> implements SnsPostShareService
{
    protected SnsPostShareMapper snsPostShareMapper;

    @Autowired(required = false)
    public SnsPostShareServiceImpl(SnsPostShareMapper snsPostShareMapper)
    {
        super(snsPostShareMapper);
        this.snsPostShareMapper = snsPostShareMapper;
    }
}
