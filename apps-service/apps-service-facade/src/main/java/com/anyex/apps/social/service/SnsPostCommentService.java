/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsPostComment;
import com.anyex.apps.social.model.SnsPostCommentModel;

import java.util.List;

/**
 * 社交帖子评论 服务接口
 * <p>File：SnsPostCommentService.java </p>
 * <p>Title: SnsPostCommentService </p>
 * <p>Description:SnsPostCommentService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsPostCommentService extends GenericService<SnsPostComment>
{

    PaginateResult<SnsPostCommentModel> getComments(Pagination pagin, Long postId, Long commentId, String viewerUserId) throws BusinessException;

    PaginateResult<SnsPostCommentModel> getAccountComments(Pagination pagin, String userId,Integer isRead) throws BusinessException;

    List<SnsPostCommentModel> getAccountCommentsUnRead(String userId) throws BusinessException;

}
