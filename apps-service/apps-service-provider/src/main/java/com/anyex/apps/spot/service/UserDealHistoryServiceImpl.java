/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.spot.entity.OrderHistory;
import com.anyex.apps.spot.entity.UserDealHistory;
import com.anyex.apps.spot.mapper.UserDealHistoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;

/**
 * user_deal_history_example 服务实现类
 * <p>File：UserDealHistoryExampleServiceImpl.java </p>
 * <p>Title: UserDealHistoryExampleServiceImpl </p>
 * <p>Description:UserDealHistoryExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserDealHistoryServiceImpl extends GenericServiceImpl<UserDealHistory> implements UserDealHistoryService
{
    protected UserDealHistoryMapper userDealHistoryExampleMapper;

    @Autowired(required = false)
    public UserDealHistoryServiceImpl(UserDealHistoryMapper userDealHistoryExampleMapper)
    {
        super(userDealHistoryExampleMapper);
        this.userDealHistoryExampleMapper = userDealHistoryExampleMapper;
    }

    @Override
    public PaginateResult<UserDealHistory> selectList(Pagination pagin, UserDealHistory userDealHistory, String tableName) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<UserDealHistory> pageInfo = PageInfo.of(userDealHistoryExampleMapper.selectList(userDealHistory,tableName));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
