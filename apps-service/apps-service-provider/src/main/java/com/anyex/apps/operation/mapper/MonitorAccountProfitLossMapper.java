/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.operation.entity.MonitorAccountProfitLoss;

/**
 * 账户浮动盈亏监控 持久层接口
 * <p>File：MonitorAccountProfitLossMapper.java </p>
 * <p>Title: MonitorAccountProfitLossMapper </p>
 * <p>Description:MonitorAccountProfitLossMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface MonitorAccountProfitLossMapper extends GenericMapper<MonitorAccountProfitLoss>
{
    Long updateMonitorAllAccountProfit();

    MonitorAccountProfitLoss allAccountProfit();

}
