/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.OrderDetail;
import com.anyex.apps.spot.entity.OrderHistory;
import com.anyex.apps.spot.mapper.OrderHistoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;

/**
 * order_history_example 服务实现类
 * <p>File：OrderHistoryExampleServiceImpl.java </p>
 * <p>Title: OrderHistoryExampleServiceImpl </p>
 * <p>Description:OrderHistoryExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderHistoryServiceImpl extends GenericServiceImpl<OrderHistory> implements OrderHistoryService
{
    protected OrderHistoryMapper orderHistoryExampleMapper;

    @Autowired(required = false)
    public OrderHistoryServiceImpl(OrderHistoryMapper orderHistoryExampleMapper)
    {
        super(orderHistoryExampleMapper);
        this.orderHistoryExampleMapper = orderHistoryExampleMapper;
    }

    @Override
    public PaginateResult<OrderHistory> selectList(Pagination pagin, OrderHistory orderHistory, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<OrderHistory> pageInfo = PageInfo.of(orderHistoryExampleMapper.selectList(orderHistory,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
