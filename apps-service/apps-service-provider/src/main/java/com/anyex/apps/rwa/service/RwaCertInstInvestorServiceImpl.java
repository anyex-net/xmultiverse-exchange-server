/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaCertInstInvestor;
import com.anyex.apps.rwa.mapper.RwaCertInstInvestorMapper;

/**
 * RWA认证机构投资者 服务实现类
 * <p>File：RwaCertInstInvestorServiceImpl.java </p>
 * <p>Title: RwaCertInstInvestorServiceImpl </p>
 * <p>Description:RwaCertInstInvestorServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaCertInstInvestorServiceImpl extends GenericServiceImpl<RwaCertInstInvestor> implements RwaCertInstInvestorService
{
    protected RwaCertInstInvestorMapper rwaCertInstInvestorMapper;

    @Autowired(required = false)
    public RwaCertInstInvestorServiceImpl(RwaCertInstInvestorMapper rwaCertInstInvestorMapper)
    {
        super(rwaCertInstInvestorMapper);
        this.rwaCertInstInvestorMapper = rwaCertInstInvestorMapper;
    }
}
