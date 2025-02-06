/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.entity.GoodsCategoryBrand;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsCategoryBrandMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类与品牌关联表 服务实现类
 * <p>File：GoodsCategoryBrandServiceImpl.java </p>
 * <p>Title: GoodsCategoryBrandServiceImpl </p>
 * <p>Description:GoodsCategoryBrandServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GoodsCategoryBrandServiceImpl extends GenericServiceImpl<GoodsCategoryBrand> implements GoodsCategoryBrandService
{
    protected GoodsCategoryBrandMapper goodsCategoryBrandMapper;

    @Autowired(required = false)
    public GoodsCategoryBrandServiceImpl(GoodsCategoryBrandMapper goodsCategoryBrandMapper)
    {
        super(goodsCategoryBrandMapper);
        this.goodsCategoryBrandMapper = goodsCategoryBrandMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGoodsCategoryBrand(Long id, String goodsBrandIds) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("商品分类不可为空"); }
        if (StringUtils.isBlank(goodsBrandIds))
        { throw new BusinessException("商品品牌Ids不可为空"); }
        goodsCategoryBrandMapper.removeByGoodsCategoryId(id);// 先删除原有的商品品牌Ids数据
        String[] brandIds = goodsBrandIds.split(",");
        List<GoodsCategoryBrand> goodsCategoryBrandList = Lists.newArrayList();
        GoodsCategoryBrand goodsCategoryBrand;
        for (String brandId : brandIds)
        {
            goodsCategoryBrand = new GoodsCategoryBrand(id, Long.parseLong(brandId));
            goodsCategoryBrand.setId(SerialnoUtils.buildPrimaryKey());
            goodsCategoryBrandList.add(goodsCategoryBrand);
        }
        goodsCategoryBrandMapper.insertBatch(goodsCategoryBrandList);
    }
}
