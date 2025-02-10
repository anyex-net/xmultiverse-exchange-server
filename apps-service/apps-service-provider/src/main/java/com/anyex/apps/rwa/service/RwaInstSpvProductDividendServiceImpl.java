/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividend;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductDividendMapper;

/**
 * RWA机构SPV产品分红记录 服务实现类
 * <p>File：RwaInstSpvProductDividendServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductDividendServiceImpl </p>
 * <p>Description:RwaInstSpvProductDividendServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductDividendServiceImpl extends GenericServiceImpl<RwaInstSpvProductDividend> implements RwaInstSpvProductDividendService
{
    protected RwaInstSpvProductDividendMapper rwaInstSpvProductDividendMapper;

    @Autowired(required = false)
    public RwaInstSpvProductDividendServiceImpl(RwaInstSpvProductDividendMapper rwaInstSpvProductDividendMapper)
    {
        super(rwaInstSpvProductDividendMapper);
        this.rwaInstSpvProductDividendMapper = rwaInstSpvProductDividendMapper;
    }
}
