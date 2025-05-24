/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.InstrumentsCurrency;
import com.anyex.apps.base.mapper.InstrumentsCurrencyMapper;

/**
 * 平台交易产品币种信息 服务实现类
 * <p>File：InstrumentsCurrencyServiceImpl.java </p>
 * <p>Title: InstrumentsCurrencyServiceImpl </p>
 * <p>Description:InstrumentsCurrencyServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class InstrumentsCurrencyServiceImpl extends GenericServiceImpl<InstrumentsCurrency> implements InstrumentsCurrencyService
{
    protected InstrumentsCurrencyMapper instrumentsCurrencyMapper;

    @Autowired(required = false)
    public InstrumentsCurrencyServiceImpl(InstrumentsCurrencyMapper instrumentsCurrencyMapper)
    {
        super(instrumentsCurrencyMapper);
        this.instrumentsCurrencyMapper = instrumentsCurrencyMapper;
    }
}
