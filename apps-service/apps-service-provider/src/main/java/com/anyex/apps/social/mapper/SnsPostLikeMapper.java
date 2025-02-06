/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.social.model.SnsPostLikeModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsPostLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交帖子点赞 持久层接口
 * <p>File：SnsPostLikeMapper.java </p>
 * <p>Title: SnsPostLikeMapper </p>
 * <p>Description:SnsPostLikeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsPostLikeMapper extends GenericMapper<SnsPostLike>
{
    List<SnsPostLikeModel> findListForRead(@Param("userId") String userId, @Param("isRead") Boolean isRead);

}
