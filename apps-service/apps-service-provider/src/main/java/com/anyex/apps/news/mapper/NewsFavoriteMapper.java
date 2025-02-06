/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.news.entity.NewsFavorite;

/**
 * 资讯收藏 持久层接口
 * <p>File：NewsFavoriteMapper.java </p>
 * <p>Title: NewsFavoriteMapper </p>
 * <p>Description:NewsFavoriteMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface NewsFavoriteMapper extends GenericMapper<NewsFavorite>
{

}
