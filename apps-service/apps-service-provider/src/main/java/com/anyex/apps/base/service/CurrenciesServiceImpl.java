/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.Currencies;
import com.anyex.apps.base.mapper.CurrenciesMapper;

/**
 * 平台币种 服务实现类
 * <p>File：CurrenciesServiceImpl.java </p>
 * <p>Title: CurrenciesServiceImpl </p>
 * <p>Description:CurrenciesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class CurrenciesServiceImpl extends GenericServiceImpl<Currencies> implements CurrenciesService
{
    protected CurrenciesMapper currenciesMapper;

    @Autowired(required = false)
    public CurrenciesServiceImpl(CurrenciesMapper currenciesMapper)
    {
        super(currenciesMapper);
        this.currenciesMapper = currenciesMapper;
    }
}
