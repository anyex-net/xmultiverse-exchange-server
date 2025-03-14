/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.BalanceHistory;

import java.util.List;

/**
 * balance_history_example 服务接口
 * <p>File：BalanceHistoryExampleService.java </p>
 * <p>Title: BalanceHistoryExampleService </p>
 * <p>Description:BalanceHistoryExampleService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface BalanceHistoryService extends GenericService<BalanceHistory>
{
    PaginateResult<BalanceHistory> selectList(Pagination pagination, BalanceHistory balanceHistory, String tableName);
}
