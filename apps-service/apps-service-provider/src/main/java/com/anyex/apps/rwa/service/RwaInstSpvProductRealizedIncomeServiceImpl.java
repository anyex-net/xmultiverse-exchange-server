/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductRealizedIncome;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductRealizedIncomeMapper;

/**
 * RWA机构SPV产品实际收入 服务实现类
 * <p>File：RwaInstSpvProductRealizedIncomeServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductRealizedIncomeServiceImpl </p>
 * <p>Description:RwaInstSpvProductRealizedIncomeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductRealizedIncomeServiceImpl extends GenericServiceImpl<RwaInstSpvProductRealizedIncome> implements RwaInstSpvProductRealizedIncomeService
{
    protected RwaInstSpvProductRealizedIncomeMapper rwaInstSpvProductRealizedIncomeMapper;

    @Autowired(required = false)
    public RwaInstSpvProductRealizedIncomeServiceImpl(RwaInstSpvProductRealizedIncomeMapper rwaInstSpvProductRealizedIncomeMapper)
    {
        super(rwaInstSpvProductRealizedIncomeMapper);
        this.rwaInstSpvProductRealizedIncomeMapper = rwaInstSpvProductRealizedIncomeMapper;
    }
}
