/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductRedemption;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductRedemptionMapper;

/**
 * RWA机构SPV产品赎回记录 服务实现类
 * <p>File：RwaInstSpvProductRedemptionServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductRedemptionServiceImpl </p>
 * <p>Description:RwaInstSpvProductRedemptionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductRedemptionServiceImpl extends GenericServiceImpl<RwaInstSpvProductRedemption> implements RwaInstSpvProductRedemptionService
{
    protected RwaInstSpvProductRedemptionMapper rwaInstSpvProductRedemptionMapper;

    @Autowired(required = false)
    public RwaInstSpvProductRedemptionServiceImpl(RwaInstSpvProductRedemptionMapper rwaInstSpvProductRedemptionMapper)
    {
        super(rwaInstSpvProductRedemptionMapper);
        this.rwaInstSpvProductRedemptionMapper = rwaInstSpvProductRedemptionMapper;
    }
}
