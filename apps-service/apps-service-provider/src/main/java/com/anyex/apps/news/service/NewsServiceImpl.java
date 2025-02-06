/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.news.entity.News;
import com.anyex.apps.news.mapper.NewsMapper;

/**
 * 资讯 服务实现类
 * <p>File：NewsServiceImpl.java </p>
 * <p>Title: NewsServiceImpl </p>
 * <p>Description:NewsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class NewsServiceImpl extends GenericServiceImpl<News> implements NewsService
{
    protected NewsMapper newsMapper;

    @Autowired(required = false)
    public NewsServiceImpl(NewsMapper newsMapper)
    {
        super(newsMapper);
        this.newsMapper = newsMapper;
    }
}
