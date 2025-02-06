/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.mapper.SnsPostMapper;

/**
 * 社交帖子 服务实现类
 * <p>File：SnsPostServiceImpl.java </p>
 * <p>Title: SnsPostServiceImpl </p>
 * <p>Description:SnsPostServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsPostServiceImpl extends GenericServiceImpl<SnsPost> implements SnsPostService
{
    protected SnsPostMapper snsPostMapper;

    @Autowired(required = false)
    public SnsPostServiceImpl(SnsPostMapper snsPostMapper)
    {
        super(snsPostMapper);
        this.snsPostMapper = snsPostMapper;
    }

    @Override
    public UserPostNumStatisticsModel getUserPostNumStatisticsModel(String userId) {
        return snsPostMapper.getUserPostNumStatisticsModel(userId);
    }

    @Override
    public PaginateResult<SnsPostModel> followPosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostModel> pageInfo = PageInfo.of(snsPostMapper.followPosts(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<SnsPostModel> publicPosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostModel> pageInfo = PageInfo.of(snsPostMapper.publicPosts(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<SnsPostModel> locationPosts(Pagination pagin, SnsPostLocationQueryModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostModel> pageInfo = PageInfo.of(snsPostMapper.locationPosts(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<SnsPostModel> cityPosts(Pagination pagin, SnsPostCityQueryModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostModel> pageInfo = PageInfo.of(snsPostMapper.cityPosts(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }


    @Override
    public SnsPostModel findById(Long id,String viewerUserId) throws BusinessException {
        return snsPostMapper.findById(id,viewerUserId);
    }

    @Override
    public PaginateResult<SnsPostModel> homePosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SnsPostModel> pageInfo = PageInfo.of(snsPostMapper.homePosts(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
