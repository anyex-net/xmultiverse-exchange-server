/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;
import com.anyex.apps.rwa.mapper.RwaBalancesTransHistoryMapper;

/**
 * RWA账户交易历史 服务实现类
 * <p>File：RwaBalancesTransHistoryServiceImpl.java </p>
 * <p>Title: RwaBalancesTransHistoryServiceImpl </p>
 * <p>Description:RwaBalancesTransHistoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaBalancesTransHistoryServiceImpl extends GenericServiceImpl<RwaBalancesTransHistory> implements RwaBalancesTransHistoryService
{
    protected RwaBalancesTransHistoryMapper rwaBalancesTransHistoryMapper;

    @Autowired(required = false)
    public RwaBalancesTransHistoryServiceImpl(RwaBalancesTransHistoryMapper rwaBalancesTransHistoryMapper)
    {
        super(rwaBalancesTransHistoryMapper);
        this.rwaBalancesTransHistoryMapper = rwaBalancesTransHistoryMapper;
    }
}
