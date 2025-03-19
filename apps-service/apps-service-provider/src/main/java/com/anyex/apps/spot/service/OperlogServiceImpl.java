/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.spot.entity.Operlog;
import com.anyex.apps.spot.mapper.OperlogMapper;

/**
 * operlog_example 服务实现类
 * <p>File：OperlogExampleServiceImpl.java </p>
 * <p>Title: OperlogExampleServiceImpl </p>
 * <p>Description:OperlogExampleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OperlogServiceImpl extends GenericServiceImpl<Operlog> implements OperlogService
{
    protected OperlogMapper operlogExampleMapper;

    @Autowired(required = false)
    public OperlogServiceImpl(OperlogMapper operlogExampleMapper)
    {
        super(operlogExampleMapper);
        this.operlogExampleMapper = operlogExampleMapper;
    }
}
