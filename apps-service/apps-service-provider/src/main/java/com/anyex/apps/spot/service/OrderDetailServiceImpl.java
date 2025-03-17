/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.DealHistory;
import com.anyex.apps.spot.entity.OrderDetail;
import com.anyex.apps.spot.mapper.OrderDetailMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;

/**
 * order_detail_example 服务实现类
 * <p>File：OrderDetailExampleServiceImpl.java </p>
 * <p>Title: OrderDetailExampleServiceImpl </p>
 * <p>Description:OrderDetailExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrderDetailServiceImpl extends GenericServiceImpl<OrderDetail> implements OrderDetailService
{
    protected OrderDetailMapper orderDetailExampleMapper;

    @Autowired(required = false)
    public OrderDetailServiceImpl(OrderDetailMapper orderDetailExampleMapper)
    {
        super(orderDetailExampleMapper);
        this.orderDetailExampleMapper = orderDetailExampleMapper;
    }

    @Override
    public PaginateResult<OrderDetail> selectList(Pagination pagin, OrderDetail orderDetail, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<OrderDetail> pageInfo = PageInfo.of(orderDetailExampleMapper.selectList(orderDetail,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
