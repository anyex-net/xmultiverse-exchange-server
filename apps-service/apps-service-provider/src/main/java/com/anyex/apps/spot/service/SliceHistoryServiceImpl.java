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
import com.anyex.apps.spot.entity.SliceHistory;
import com.anyex.apps.spot.mapper.SliceHistoryMapper;

/**
 * slice_history 服务实现类
 * <p>File：SliceHistoryServiceImpl.java </p>
 * <p>Title: SliceHistoryServiceImpl </p>
 * <p>Description:SliceHistoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SliceHistoryServiceImpl extends GenericServiceImpl<SliceHistory> implements SliceHistoryService
{
    protected SliceHistoryMapper sliceHistoryMapper;

    @Autowired(required = false)
    public SliceHistoryServiceImpl(SliceHistoryMapper sliceHistoryMapper)
    {
        super(sliceHistoryMapper);
        this.sliceHistoryMapper = sliceHistoryMapper;
    }

//    @Override
//    public PaginateResult<SliceHistory> selectList(Pagination pagin, SliceHistory sliceHistory, String tableName) throws BusinessException
//    {
//        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
//        PageInfo<SliceHistory> pageInfo = PageInfo.of(sliceHistoryMapper.selectList(sliceHistory,tableName));
//        pagin.setTotal(pageInfo.getTotal());
//        pagin.setCurrent(pageInfo.getPageNum());
//        return new PaginateResult<>(pagin, pageInfo.getList());
//    }
}
