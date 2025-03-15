/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.spot.entity.DealHistory;
import com.anyex.apps.spot.mapper.DealHistoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;

/**
 * deal_history_example 服务实现类
 * <p>File：DealHistoryExampleServiceImpl.java </p>
 * <p>Title: DealHistoryExampleServiceImpl </p>
 * <p>Description:DealHistoryExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealHistoryServiceImpl extends GenericServiceImpl<DealHistory> implements DealHistoryService
{
    protected DealHistoryMapper dealHistoryExampleMapper;

    @Autowired(required = false)
    public DealHistoryServiceImpl(DealHistoryMapper dealHistoryExampleMapper)
    {
        super(dealHistoryExampleMapper);
        this.dealHistoryExampleMapper = dealHistoryExampleMapper;
    }
}
