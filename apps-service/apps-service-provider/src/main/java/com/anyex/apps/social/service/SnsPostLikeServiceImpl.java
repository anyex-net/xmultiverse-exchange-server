/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.SnsPostLikeModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsPostLike;
import com.anyex.apps.social.mapper.SnsPostLikeMapper;

import java.util.List;

/**
 * 社交帖子点赞 服务实现类
 * <p>File：SnsPostLikeServiceImpl.java </p>
 * <p>Title: SnsPostLikeServiceImpl </p>
 * <p>Description:SnsPostLikeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsPostLikeServiceImpl extends GenericServiceImpl<SnsPostLike> implements SnsPostLikeService
{
    protected SnsPostLikeMapper snsPostLikeMapper;

    @Autowired(required = false)
    public SnsPostLikeServiceImpl(SnsPostLikeMapper snsPostLikeMapper)
    {
        super(snsPostLikeMapper);
        this.snsPostLikeMapper = snsPostLikeMapper;
    }

    @Override
    public List<SnsPostLikeModel> findListForRead(String userId, Boolean isRead) {
        return snsPostLikeMapper.findListForRead(userId, isRead);
    }

    @Override
    public PaginateResult<SnsPostLikeModel> findListForRead(Pagination pagin, String userId, Boolean isRead) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostLikeModel> pageInfo = PageInfo.of(snsPostLikeMapper.findListForRead(userId,isRead));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
