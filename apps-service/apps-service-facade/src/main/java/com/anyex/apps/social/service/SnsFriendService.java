/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.model.AccountInfoModel;

/**
 * 社交好友 服务接口
 * <p>File：SnsFriendService.java </p>
 * <p>Title: SnsFriendService </p>
 * <p>Description:SnsFriendService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsFriendService extends GenericService<SnsFriend>
{
    /**
     * 根据USERID获取好友数量
     * @param userId
     * @return
     */
    Integer getFriendsCntByUserId(String userId);


    PaginateResult<AccountInfoModel> myFriends(Pagination pagin, SnsFriend entity) throws BusinessException;

}
