/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductAssetMapper;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品资产信息 服务实现类
 * <p>File：RwaInstSpvProductAssetServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductAssetServiceImpl </p>
 * <p>Description:RwaInstSpvProductAssetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductAssetServiceImpl extends GenericServiceImpl<RwaInstSpvProductAsset> implements RwaInstSpvProductAssetService
{
    protected RwaInstSpvProductAssetMapper rwaInstSpvProductAssetMapper;

    @Autowired(required = false)
    public RwaInstSpvProductAssetServiceImpl(RwaInstSpvProductAssetMapper rwaInstSpvProductAssetMapper)
    {
        super(rwaInstSpvProductAssetMapper);
        this.rwaInstSpvProductAssetMapper = rwaInstSpvProductAssetMapper;
    }

    @Override
    public BigDecimal selectAmountSum(Long instSpvProductId) throws BusinessException
    {
        return rwaInstSpvProductAssetMapper.selectAmountSum(instSpvProductId);
    }
}
