/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

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
}
