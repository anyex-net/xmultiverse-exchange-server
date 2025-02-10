/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvCompany;
import com.anyex.apps.rwa.mapper.RwaInstSpvCompanyMapper;

/**
 * RWA机构SPV公司 服务实现类
 * <p>File：RwaInstSpvCompanyServiceImpl.java </p>
 * <p>Title: RwaInstSpvCompanyServiceImpl </p>
 * <p>Description:RwaInstSpvCompanyServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvCompanyServiceImpl extends GenericServiceImpl<RwaInstSpvCompany> implements RwaInstSpvCompanyService
{
    protected RwaInstSpvCompanyMapper rwaInstSpvCompanyMapper;

    @Autowired(required = false)
    public RwaInstSpvCompanyServiceImpl(RwaInstSpvCompanyMapper rwaInstSpvCompanyMapper)
    {
        super(rwaInstSpvCompanyMapper);
        this.rwaInstSpvCompanyMapper = rwaInstSpvCompanyMapper;
    }
}
