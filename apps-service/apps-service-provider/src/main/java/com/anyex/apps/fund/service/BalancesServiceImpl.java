/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.fund.entity.Balances;
import com.anyex.apps.fund.mapper.BalancesMapper;

/**
 * 资金账户余额 服务实现类
 * <p>File：BalancesServiceImpl.java </p>
 * <p>Title: BalancesServiceImpl </p>
 * <p>Description:BalancesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BalancesServiceImpl extends GenericServiceImpl<Balances> implements BalancesService
{
    protected BalancesMapper balancesMapper;

    @Autowired(required = false)
    public BalancesServiceImpl(BalancesMapper balancesMapper)
    {
        super(balancesMapper);
        this.balancesMapper = balancesMapper;
    }
}
