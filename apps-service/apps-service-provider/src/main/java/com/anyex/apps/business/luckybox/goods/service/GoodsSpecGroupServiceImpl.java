/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSpecGroup;
import com.anyex.apps.business.luckybox.goods.mapper.GoodsSpecGroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品品类表 服务实现类
 * <p>File：GoodsSpecGroupServiceImpl.java </p>
 * <p>Title: GoodsSpecGroupServiceImpl </p>
 * <p>Description:GoodsSpecGroupServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsSpecGroupServiceImpl extends GenericServiceImpl<GoodsSpecGroup> implements GoodsSpecGroupService
{
    protected GoodsSpecGroupMapper goodsSpecGroupMapper;

    @Autowired(required = false)
    public GoodsSpecGroupServiceImpl(GoodsSpecGroupMapper goodsSpecGroupMapper)
    {
        super(goodsSpecGroupMapper);
        this.goodsSpecGroupMapper = goodsSpecGroupMapper;
    }
}
