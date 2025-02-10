/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductPurchaseMapper;

/**
 * RWA机构SPV产品申购记录 服务实现类
 * <p>File：RwaInstSpvProductPurchaseServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductPurchaseServiceImpl </p>
 * <p>Description:RwaInstSpvProductPurchaseServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductPurchaseServiceImpl extends GenericServiceImpl<RwaInstSpvProductPurchase> implements RwaInstSpvProductPurchaseService
{
    protected RwaInstSpvProductPurchaseMapper rwaInstSpvProductPurchaseMapper;

    @Autowired(required = false)
    public RwaInstSpvProductPurchaseServiceImpl(RwaInstSpvProductPurchaseMapper rwaInstSpvProductPurchaseMapper)
    {
        super(rwaInstSpvProductPurchaseMapper);
        this.rwaInstSpvProductPurchaseMapper = rwaInstSpvProductPurchaseMapper;
    }
}
