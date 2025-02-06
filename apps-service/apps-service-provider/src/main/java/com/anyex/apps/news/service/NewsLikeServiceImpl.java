/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.news.entity.NewsLike;
import com.anyex.apps.news.mapper.NewsLikeMapper;

/**
 * 资讯点赞 服务实现类
 * <p>File：NewsLikeServiceImpl.java </p>
 * <p>Title: NewsLikeServiceImpl </p>
 * <p>Description:NewsLikeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class NewsLikeServiceImpl extends GenericServiceImpl<NewsLike> implements NewsLikeService
{
    protected NewsLikeMapper newsLikeMapper;

    @Autowired(required = false)
    public NewsLikeServiceImpl(NewsLikeMapper newsLikeMapper)
    {
        super(newsLikeMapper);
        this.newsLikeMapper = newsLikeMapper;
    }
}
