/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.operation.entity.MonitorWalletAssetFlows;
import com.anyex.apps.operation.model.MonitorWalletAssetFlowsResultModel;

/**
 * 钱包资产流水监控 服务接口
 * <p>File：MonitorWalletAssetFlowsService.java </p>
 * <p>Title: MonitorWalletAssetFlowsService </p>
 * <p>Description:MonitorWalletAssetFlowsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface MonitorWalletAssetFlowsService extends GenericService<MonitorWalletAssetFlows>
{
    /**
     * 根据用户id和币种查询监控记录
     * @param accountId
     * @param currency
     * @return
     */
    MonitorWalletAssetFlows findByAccountIdAndCurrency(Long accountId, String currency);

    /**
     * 监控用户资产流水
     * @param accountId 账户id
     * @param currency 币种
     * @param startTime 开始时间(上一次监控时间)
     * @param endTime 结束时间 (最后监控时间)
     * @return
     */
    MonitorWalletAssetFlowsResultModel monitorWalletAssetFlows(Long accountId, String currency, Long startTime, Long endTime);

    /**
     * 监控资金流水
     */
    void monitorWalletAssetFlowsTask();
}
