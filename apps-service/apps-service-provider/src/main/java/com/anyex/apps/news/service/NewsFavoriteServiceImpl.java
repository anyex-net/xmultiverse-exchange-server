/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.news.entity.NewsFavorite;
import com.anyex.apps.news.mapper.NewsFavoriteMapper;

/**
 * 资讯收藏 服务实现类
 * <p>File：NewsFavoriteServiceImpl.java </p>
 * <p>Title: NewsFavoriteServiceImpl </p>
 * <p>Description:NewsFavoriteServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class NewsFavoriteServiceImpl extends GenericServiceImpl<NewsFavorite> implements NewsFavoriteService
{
    protected NewsFavoriteMapper newsFavoriteMapper;

    @Autowired(required = false)
    public NewsFavoriteServiceImpl(NewsFavoriteMapper newsFavoriteMapper)
    {
        super(newsFavoriteMapper);
        this.newsFavoriteMapper = newsFavoriteMapper;
    }
}
