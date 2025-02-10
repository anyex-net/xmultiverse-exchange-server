/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaCertInstSpvPromoter;
import com.anyex.apps.rwa.mapper.RwaCertInstSpvPromoterMapper;

/**
 * RWA认证机构SPV发起人 服务实现类
 * <p>File：RwaCertInstSpvPromoterServiceImpl.java </p>
 * <p>Title: RwaCertInstSpvPromoterServiceImpl </p>
 * <p>Description:RwaCertInstSpvPromoterServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaCertInstSpvPromoterServiceImpl extends GenericServiceImpl<RwaCertInstSpvPromoter> implements RwaCertInstSpvPromoterService
{
    protected RwaCertInstSpvPromoterMapper rwaCertInstSpvPromoterMapper;

    @Autowired(required = false)
    public RwaCertInstSpvPromoterServiceImpl(RwaCertInstSpvPromoterMapper rwaCertInstSpvPromoterMapper)
    {
        super(rwaCertInstSpvPromoterMapper);
        this.rwaCertInstSpvPromoterMapper = rwaCertInstSpvPromoterMapper;
    }
}
