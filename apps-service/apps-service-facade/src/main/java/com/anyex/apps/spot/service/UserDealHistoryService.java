/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.UserDealHistory;

/**
 * user_deal_history_example 服务接口
 * <p>File：UserDealHistoryExampleService.java </p>
 * <p>Title: UserDealHistoryExampleService </p>
 * <p>Description:UserDealHistoryExampleService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserDealHistoryService extends GenericService<UserDealHistory>
{
    PaginateResult<UserDealHistory> selectList(Pagination pagin, UserDealHistory userDealHistory, String tableName);
}
