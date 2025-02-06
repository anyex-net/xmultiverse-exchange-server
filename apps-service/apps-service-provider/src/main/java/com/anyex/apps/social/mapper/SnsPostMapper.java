/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.social.model.*;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交帖子 持久层接口
 * <p>File：SnsPostMapper.java </p>
 * <p>Title: SnsPostMapper </p>
 * <p>Description:SnsPostMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsPostMapper extends GenericMapper<SnsPost>
{

    UserPostNumStatisticsModel getUserPostNumStatisticsModel(@Param("userId") String userId);

    /**
     * 我关注的人的帖子
     * @param entity
     * @return
     * @throws BusinessException
     */
    List<SnsPostModel> followPosts(SnsPostQueryModel entity) throws BusinessException;

    /**
     * 推荐的帖子  openness 1公开 0匿名
     * @param entity
     * @return
     * @throws BusinessException
     */
    List<SnsPostModel> publicPosts(SnsPostQueryModel entity) throws BusinessException;

    /**
     * 附近 同城
     * @param entity
     * @return
     * @throws BusinessException
     */
    List<SnsPostModel> locationPosts(SnsPostLocationQueryModel entity) throws BusinessException;

    List<SnsPostModel> cityPosts(SnsPostCityQueryModel entity) throws BusinessException;

    SnsPostModel findById(@Param("id") Long id,@Param("viewerUserId") String viewerUserId) throws BusinessException;

    List<SnsPostModel> homePosts(SnsPostQueryModel entity) throws BusinessException;

}
