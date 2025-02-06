/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.social.model.SnsPostCommentModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsPostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交帖子评论 持久层接口
 * <p>File：SnsPostCommentMapper.java </p>
 * <p>Title: SnsPostCommentMapper </p>
 * <p>Description:SnsPostCommentMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsPostCommentMapper extends GenericMapper<SnsPostComment>
{

    List<SnsPostCommentModel> getComments(@Param("postId") Long postId, @Param("commentId") Long commentId,@Param("viewerUserId") String viewerUserId) throws BusinessException;

    List<SnsPostCommentModel> getAccountComments(@Param("userId") String userId, @Param("isRead") Integer isRead) throws BusinessException;
}
