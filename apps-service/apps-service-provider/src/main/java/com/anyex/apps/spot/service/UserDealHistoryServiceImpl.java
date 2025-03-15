/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import com.anyex.apps.spot.entity.UserDealHistory;
import com.anyex.apps.spot.mapper.UserDealHistoryMapper;
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
}
