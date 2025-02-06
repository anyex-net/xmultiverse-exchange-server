/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysTradeDay;
import com.anyex.apps.common.mapper.SysTradeDayMapper;

/**
 * 交易日 服务实现类
 * <p>File：SysTradeDayServiceImpl.java </p>
 * <p>Title: SysTradeDayServiceImpl </p>
 * <p>Description:SysTradeDayServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysTradeDayServiceImpl extends GenericServiceImpl<SysTradeDay> implements SysTradeDayService
{
    protected SysTradeDayMapper sysTradeDayMapper;

    @Autowired(required = false)
    public SysTradeDayServiceImpl(SysTradeDayMapper sysTradeDayMapper)
    {
        super(sysTradeDayMapper);
        this.sysTradeDayMapper = sysTradeDayMapper;
    }
}
