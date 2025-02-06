/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsPostCommentLike;

/**
 * 社交帖子评论点赞 持久层接口
 * <p>File：SnsPostCommentLikeMapper.java </p>
 * <p>Title: SnsPostCommentLikeMapper </p>
 * <p>Description:SnsPostCommentLikeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsPostCommentLikeMapper extends GenericMapper<SnsPostCommentLike>
{

}
