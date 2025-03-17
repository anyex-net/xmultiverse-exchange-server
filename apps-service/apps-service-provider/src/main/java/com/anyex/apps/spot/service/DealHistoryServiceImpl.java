/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.BalanceHistory;
import com.anyex.apps.spot.entity.DealHistory;
import com.anyex.apps.spot.mapper.DealHistoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PaginateResult<DealHistory> selectList(Pagination pagin, DealHistory dealHistory, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<DealHistory> pageInfo = PageInfo.of(dealHistoryExampleMapper.selectList(dealHistory,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
