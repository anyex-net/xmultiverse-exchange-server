/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.fund.entity.BalancesTransHistory;
import com.anyex.apps.fund.mapper.BalancesTransHistoryMapper;

/**
 * 资金账户交易历史 服务实现类
 * <p>File：BalancesTransHistoryServiceImpl.java </p>
 * <p>Title: BalancesTransHistoryServiceImpl </p>
 * <p>Description:BalancesTransHistoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BalancesTransHistoryServiceImpl extends GenericServiceImpl<BalancesTransHistory> implements BalancesTransHistoryService
{
    protected BalancesTransHistoryMapper balancesTransHistoryMapper;

    @Autowired(required = false)
    public BalancesTransHistoryServiceImpl(BalancesTransHistoryMapper balancesTransHistoryMapper)
    {
        super(balancesTransHistoryMapper);
        this.balancesTransHistoryMapper = balancesTransHistoryMapper;
    }
}
