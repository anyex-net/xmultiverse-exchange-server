/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsFollow;
import com.anyex.apps.social.model.AccountInfoModel;

/**
 * 社交关注(我关注的) 服务接口
 * <p>File：SnsFollowService.java </p>
 * <p>Title: SnsFollowService </p>
 * <p>Description:SnsFollowService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsFollowService extends GenericService<SnsFollow>
{
    /**
     * userId的关注数量
     * @param userId
     * @return
     */
    Integer cntFollow(String userId);

    PaginateResult<AccountInfoModel> listFollows(Pagination pagin, String userId) throws BusinessException;

    void follow(String userId,String friendUserId);

    void unfollow(String userId,String friendUserId);
}
