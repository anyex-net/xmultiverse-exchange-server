/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.operation.mapper.MonitorAccountProfitLossMapper;
import com.anyex.apps.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.operation.entity.MonitorAccountProfitLoss;

/**
 * 账户浮动盈亏监控 服务实现类
 * <p>File：MonitorAccountProfitLossServiceImpl.java </p>
 * <p>Title: MonitorAccountProfitLossServiceImpl </p>
 * <p>Description:MonitorAccountProfitLossServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class MonitorAccountProfitLossServiceImpl extends GenericServiceImpl<MonitorAccountProfitLoss> implements MonitorAccountProfitLossService
{
    protected MonitorAccountProfitLossMapper monitorAccountProfitLossMapper;

    @Autowired(required = false)
    public MonitorAccountProfitLossServiceImpl(MonitorAccountProfitLossMapper monitorAccountProfitLossMapper)
    {
        super(monitorAccountProfitLossMapper);
        this.monitorAccountProfitLossMapper = monitorAccountProfitLossMapper;
    }

    @Override
    public Long updateMonitorAllAccountProfit() throws BusinessException {
        return monitorAccountProfitLossMapper.updateMonitorAllAccountProfit();
    }

    @Override
    public MonitorAccountProfitLoss allAccountProfit() throws BusinessException {
        return monitorAccountProfitLossMapper.allAccountProfit();
    }
}
