/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.fund.entity.DepositTransHistory;
import com.anyex.apps.fund.mapper.DepositTransHistoryMapper;

/**
 * 充值交易历史 服务实现类
 * <p>File：DepositTransHistoryServiceImpl.java </p>
 * <p>Title: DepositTransHistoryServiceImpl </p>
 * <p>Description:DepositTransHistoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DepositTransHistoryServiceImpl extends GenericServiceImpl<DepositTransHistory> implements DepositTransHistoryService
{
    protected DepositTransHistoryMapper depositTransHistoryMapper;

    @Autowired(required = false)
    public DepositTransHistoryServiceImpl(DepositTransHistoryMapper depositTransHistoryMapper)
    {
        super(depositTransHistoryMapper);
        this.depositTransHistoryMapper = depositTransHistoryMapper;
    }
}
