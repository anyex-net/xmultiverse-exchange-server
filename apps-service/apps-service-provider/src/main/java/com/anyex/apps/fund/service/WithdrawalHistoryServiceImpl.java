/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.fund.entity.WithdrawalHistory;
import com.anyex.apps.fund.mapper.WithdrawalHistoryMapper;

/**
 * 提现历史 服务实现类
 * <p>File：WithdrawalHistoryServiceImpl.java </p>
 * <p>Title: WithdrawalHistoryServiceImpl </p>
 * <p>Description:WithdrawalHistoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WithdrawalHistoryServiceImpl extends GenericServiceImpl<WithdrawalHistory> implements WithdrawalHistoryService
{
    protected WithdrawalHistoryMapper withdrawalHistoryMapper;

    @Autowired(required = false)
    public WithdrawalHistoryServiceImpl(WithdrawalHistoryMapper withdrawalHistoryMapper)
    {
        super(withdrawalHistoryMapper);
        this.withdrawalHistoryMapper = withdrawalHistoryMapper;
    }
}
