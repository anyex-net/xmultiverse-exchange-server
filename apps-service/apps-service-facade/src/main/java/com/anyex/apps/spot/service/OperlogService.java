/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.Operlog;

/**
 * operlog_example 服务接口
 * <p>File：OperlogExampleService.java </p>
 * <p>Title: OperlogExampleService </p>
 * <p>Description:OperlogExampleService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface OperlogService extends GenericService<Operlog>
{
    PaginateResult<Operlog> selectList(Pagination pagin, Operlog operlog, String tableName);
}
