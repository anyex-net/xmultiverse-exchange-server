/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSpu;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsSpuMapper;

/**
 * 产品SPU表 服务实现类
 * <p>File：GoodsSpuServiceImpl.java </p>
 * <p>Title: GoodsSpuServiceImpl </p>
 * <p>Description:GoodsSpuServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsSpuServiceImpl extends GenericServiceImpl<GoodsSpu> implements GoodsSpuService
{
    protected GoodsSpuMapper goodsSpuMapper;

    @Autowired(required = false)
    public GoodsSpuServiceImpl(GoodsSpuMapper goodsSpuMapper)
    {
        super(goodsSpuMapper);
        this.goodsSpuMapper = goodsSpuMapper;
    }
}
