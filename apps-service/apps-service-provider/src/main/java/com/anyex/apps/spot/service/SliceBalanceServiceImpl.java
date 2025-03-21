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
import com.anyex.apps.spot.entity.SliceBalance;
import com.anyex.apps.spot.mapper.SliceBalanceMapper;

/**
 * slice_balance_example 服务实现类
 * <p>File：SliceBalanceExampleServiceImpl.java </p>
 * <p>Title: SliceBalanceExampleServiceImpl </p>
 * <p>Description:SliceBalanceExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SliceBalanceServiceImpl extends GenericServiceImpl<SliceBalance> implements SliceBalanceService
{
    protected SliceBalanceMapper sliceBalanceExampleMapper;

    @Autowired(required = false)
    public SliceBalanceServiceImpl(SliceBalanceMapper sliceBalanceExampleMapper)
    {
        super(sliceBalanceExampleMapper);
        this.sliceBalanceExampleMapper = sliceBalanceExampleMapper;
    }

    @Override
    public PaginateResult<SliceBalance> selectList(Pagination pagin, SliceBalance sliceBalance, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SliceBalance> pageInfo = PageInfo.of(sliceBalanceExampleMapper.selectList(sliceBalance,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
