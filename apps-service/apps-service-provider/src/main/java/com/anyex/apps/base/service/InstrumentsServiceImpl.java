/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.Instruments;
import com.anyex.apps.base.mapper.InstrumentsMapper;

/**
 * 平台交易产品 服务实现类
 * <p>File：InstrumentsServiceImpl.java </p>
 * <p>Title: InstrumentsServiceImpl </p>
 * <p>Description:InstrumentsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class InstrumentsServiceImpl extends GenericServiceImpl<Instruments> implements InstrumentsService
{
    protected InstrumentsMapper instrumentsMapper;

    @Autowired(required = false)
    public InstrumentsServiceImpl(InstrumentsMapper instrumentsMapper)
    {
        super(instrumentsMapper);
        this.instrumentsMapper = instrumentsMapper;
    }
}
