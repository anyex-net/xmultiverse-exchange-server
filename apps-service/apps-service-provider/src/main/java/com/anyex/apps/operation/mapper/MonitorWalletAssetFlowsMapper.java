/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.operation.model.MonitorWalletAssetFlowsResultModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.operation.entity.MonitorWalletAssetFlows;
import org.apache.ibatis.annotations.Param;

/**
 * 钱包资产流水监控 持久层接口
 * <p>File：MonitorWalletAssetFlowsMapper.java </p>
 * <p>Title: MonitorWalletAssetFlowsMapper </p>
 * <p>Description:MonitorWalletAssetFlowsMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface MonitorWalletAssetFlowsMapper extends GenericMapper<MonitorWalletAssetFlows>
{
    /**
     * 根据用户id和币种查询监控记录
     * @param accountId
     * @param currency
     * @return
     */
    MonitorWalletAssetFlows findByAccountIdAndCurrency(@Param("accountId") Long accountId, @Param("currency") String currency);

    /**
     * 监控用户资产流水
     * @param accountId 账户id
     * @param currency 币种
     * @param startTime 开始时间(上一次监控时间)
     * @param endTime 结束时间 (最后监控时间)
     * @return
     */
    MonitorWalletAssetFlowsResultModel monitorWalletAssetFlows(@Param("accountId") Long accountId,
                                                               @Param("currency") String currency,
                                                               @Param("startTime") Long startTime,
                                                               @Param("endTime") Long endTime);
}
