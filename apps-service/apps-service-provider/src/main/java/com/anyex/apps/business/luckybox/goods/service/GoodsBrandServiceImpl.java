/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.goods.entity.GoodsBrand;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsBrandMapper;

import java.util.List;

/**
 * 商品品牌表 服务实现类
 * <p>File：GoodsBrandServiceImpl.java </p>
 * <p>Title: GoodsBrandServiceImpl </p>
 * <p>Description:GoodsBrandServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsBrandServiceImpl extends GenericServiceImpl<GoodsBrand> implements GoodsBrandService
{
    protected GoodsBrandMapper goodsBrandMapper;

    @Autowired(required = false)
    public GoodsBrandServiceImpl(GoodsBrandMapper goodsBrandMapper)
    {
        super(goodsBrandMapper);
        this.goodsBrandMapper = goodsBrandMapper;
    }

    @Override
    public List<GoodsBrand> findByGoodsCategoryId(Long categoryId)
    {
        return goodsBrandMapper.findByGoodsCategoryId(categoryId);
    }
}
