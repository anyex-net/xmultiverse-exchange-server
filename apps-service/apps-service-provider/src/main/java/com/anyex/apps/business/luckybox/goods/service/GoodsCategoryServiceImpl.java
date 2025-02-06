/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.entity.GoodsCategory;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsCategoryMapper;

import java.util.List;

/**
 * 商品分类表 服务实现类
 * <p>File：GoodsCategoryServiceImpl.java </p>
 * <p>Title: GoodsCategoryServiceImpl </p>
 * <p>Description:GoodsCategoryServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsCategoryServiceImpl extends GenericServiceImpl<GoodsCategory> implements GoodsCategoryService
{
    protected GoodsCategoryMapper goodsCategoryMapper;

    @Autowired(required = false)
    public GoodsCategoryServiceImpl(GoodsCategoryMapper goodsCategoryMapper)
    {
        super(goodsCategoryMapper);
        this.goodsCategoryMapper = goodsCategoryMapper;
    }

    @Override
    public List<GoodsCategory> treeData() throws BusinessException
    {
        List<GoodsCategory> entitys = goodsCategoryMapper.selectAll();
        //
        List<GoodsCategory> data = Lists.newArrayList();
        for (GoodsCategory parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (GoodsCategory child : entitys)
            {
                if (parent.getId().equals(child.getParentId()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        //
        return data;
    }
}
