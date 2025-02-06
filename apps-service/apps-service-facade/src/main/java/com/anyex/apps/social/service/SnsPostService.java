/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsPost;
import com.anyex.apps.social.model.*;

import java.util.List;

/**
 * 社交帖子 服务接口
 * <p>File：SnsPostService.java </p>
 * <p>Title: SnsPostService </p>
 * <p>Description:SnsPostService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsPostService extends GenericService<SnsPost>
{

    /**
     * 获取用户帖子数量统计
     * @param userId
     * @return
     */
    UserPostNumStatisticsModel getUserPostNumStatisticsModel(String userId);

    /**
     * 我关注的人的帖子
     * @param pagin
     * @param entity
     * @return
     * @throws BusinessException
     */
    PaginateResult<SnsPostModel> followPosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException;

    /**
     * 推荐的帖子  openness 1公开 0匿名
     * @param pagin
     * @param entity
     * @return
     * @throws BusinessException
     */
    PaginateResult<SnsPostModel> publicPosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException;

    /**
     * 附近 同城
     * @param pagin
     * @param entity
     * @return
     * @throws BusinessException
     */
    PaginateResult<SnsPostModel> locationPosts(Pagination pagin, SnsPostLocationQueryModel entity) throws BusinessException;


    PaginateResult<SnsPostModel> cityPosts(Pagination pagin,SnsPostCityQueryModel entity) throws BusinessException;


    SnsPostModel findById(Long id,String viewerUserId) throws BusinessException;


    PaginateResult<SnsPostModel> homePosts(Pagination pagin, SnsPostQueryModel entity) throws BusinessException;

}
