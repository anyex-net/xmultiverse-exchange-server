/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSpecParam;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsSpecParamMapper;

/**
 * 商品品类参数表 服务实现类
 * <p>File：GoodsSpecParamServiceImpl.java </p>
 * <p>Title: GoodsSpecParamServiceImpl </p>
 * <p>Description:GoodsSpecParamServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsSpecParamServiceImpl extends GenericServiceImpl<GoodsSpecParam> implements GoodsSpecParamService
{
    protected GoodsSpecParamMapper goodsSpecParamMapper;

    @Autowired(required = false)
    public GoodsSpecParamServiceImpl(GoodsSpecParamMapper goodsSpecParamMapper)
    {
        super(goodsSpecParamMapper);
        this.goodsSpecParamMapper = goodsSpecParamMapper;
    }
}
