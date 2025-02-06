/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsPostLike;
import com.anyex.apps.social.model.SnsPostLikeModel;

import java.util.List;

/**
 * 社交帖子点赞 服务接口
 * <p>File：SnsPostLikeService.java </p>
 * <p>Title: SnsPostLikeService </p>
 * <p>Description:SnsPostLikeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsPostLikeService extends GenericService<SnsPostLike>
{

    List<SnsPostLikeModel> findListForRead(String userId, Boolean isRead);

    PaginateResult<SnsPostLikeModel> findListForRead(Pagination pagin, String userId, Boolean isRead) throws BusinessException;

}
