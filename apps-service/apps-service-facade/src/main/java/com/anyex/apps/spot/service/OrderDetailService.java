/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.OrderDetail;

/**
 * order_detail_example 服务接口
 * <p>File：OrderDetailExampleService.java </p>
 * <p>Title: OrderDetailExampleService </p>
 * <p>Description:OrderDetailExampleService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface OrderDetailService extends GenericService<OrderDetail>
{
    PaginateResult<OrderDetail> selectList(Pagination pagin, OrderDetail orderDetail, String tableName);
}
