/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.spot.entity.BalanceHistory;
import com.anyex.apps.spot.mapper.BalanceHistoryMapper;


/**
 * balance_history_example 服务实现类
 * <p>File：BalanceHistoryExampleServiceImpl.java </p>
 * <p>Title: BalanceHistoryExampleServiceImpl </p>
 * <p>Description:BalanceHistoryExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BalanceHistoryServiceImpl extends GenericServiceImpl<BalanceHistory> implements BalanceHistoryService
{
    protected BalanceHistoryMapper balanceHistoryExampleMapper;

    @Autowired(required = false)
    public BalanceHistoryServiceImpl(BalanceHistoryMapper balanceHistoryExampleMapper)
    {
        super(balanceHistoryExampleMapper);
        this.balanceHistoryExampleMapper = balanceHistoryExampleMapper;
    }

    @Override
    public PaginateResult<BalanceHistory> selectList(Pagination pagin,BalanceHistory balanceHistory,String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<BalanceHistory> pageInfo = PageInfo.of(balanceHistoryExampleMapper.selectList(balanceHistory,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
