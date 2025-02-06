/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.operation.entity.MonitorAccountProfitLoss;
import com.anyex.apps.exception.BusinessException;

/**
 * 账户浮动盈亏监控 服务接口
 * <p>File：MonitorAccountProfitLossService.java </p>
 * <p>Title: MonitorAccountProfitLossService </p>
 * <p>Description:MonitorAccountProfitLossService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface MonitorAccountProfitLossService extends GenericService<MonitorAccountProfitLoss>
{
    /**
     * 批量更新每个用户的盈亏
     * @return
     */
    Long updateMonitorAllAccountProfit() throws BusinessException;

    /**
     * 查询平台盈亏
     * @return
     */
    MonitorAccountProfitLoss allAccountProfit() throws BusinessException;
}
