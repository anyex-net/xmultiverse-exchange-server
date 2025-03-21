/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.Operlog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.spot.entity.SliceOrder;
import com.anyex.apps.spot.mapper.SliceOrderMapper;

/**
 * slice_order_example 服务实现类
 * <p>File：SliceOrderExampleServiceImpl.java </p>
 * <p>Title: SliceOrderExampleServiceImpl </p>
 * <p>Description:SliceOrderExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SliceOrderServiceImpl extends GenericServiceImpl<SliceOrder> implements SliceOrderService
{
    protected SliceOrderMapper sliceOrderExampleMapper;

    @Autowired(required = false)
    public SliceOrderServiceImpl(SliceOrderMapper sliceOrderExampleMapper)
    {
        super(sliceOrderExampleMapper);
        this.sliceOrderExampleMapper = sliceOrderExampleMapper;
    }

    @Override
    public PaginateResult<SliceOrder> selectList(Pagination pagin, SliceOrder sliceOrder, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SliceOrder> pageInfo = PageInfo.of(sliceOrderExampleMapper.selectList(sliceOrder,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

}
