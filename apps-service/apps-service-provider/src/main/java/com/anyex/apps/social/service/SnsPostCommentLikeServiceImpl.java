/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsPostCommentLike;
import com.anyex.apps.social.mapper.SnsPostCommentLikeMapper;

/**
 * 社交帖子评论点赞 服务实现类
 * <p>File：SnsPostCommentLikeServiceImpl.java </p>
 * <p>Title: SnsPostCommentLikeServiceImpl </p>
 * <p>Description:SnsPostCommentLikeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsPostCommentLikeServiceImpl extends GenericServiceImpl<SnsPostCommentLike> implements SnsPostCommentLikeService
{
    protected SnsPostCommentLikeMapper snsPostCommentLikeMapper;

    @Autowired(required = false)
    public SnsPostCommentLikeServiceImpl(SnsPostCommentLikeMapper snsPostCommentLikeMapper)
    {
        super(snsPostCommentLikeMapper);
        this.snsPostCommentLikeMapper = snsPostCommentLikeMapper;
    }
}
