/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;

import java.util.List;

/**
 * RWA机构SPV产品 服务实现类
 * <p>File：RwaInstSpvProductServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductServiceImpl </p>
 * <p>Description:RwaInstSpvProductServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductServiceImpl extends GenericServiceImpl<RwaInstSpvProduct> implements RwaInstSpvProductService
{
    protected RwaInstSpvProductMapper rwaInstSpvProductMapper;

    @Autowired(required = false)
    public RwaInstSpvProductServiceImpl(RwaInstSpvProductMapper rwaInstSpvProductMapper)
    {
        super(rwaInstSpvProductMapper);
        this.rwaInstSpvProductMapper = rwaInstSpvProductMapper;
    }

    @Override
    public List<RwaInstSpvProduct> findListByState(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        return rwaInstSpvProductMapper.findListByState(rwaInstSpvProduct);
    }
}
