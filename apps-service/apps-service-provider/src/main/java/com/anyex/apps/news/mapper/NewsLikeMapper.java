/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.news.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.news.entity.NewsLike;

/**
 * 资讯点赞 持久层接口
 * <p>File：NewsLikeMapper.java </p>
 * <p>Title: NewsLikeMapper </p>
 * <p>Description:NewsLikeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface NewsLikeMapper extends GenericMapper<NewsLike>
{

}
