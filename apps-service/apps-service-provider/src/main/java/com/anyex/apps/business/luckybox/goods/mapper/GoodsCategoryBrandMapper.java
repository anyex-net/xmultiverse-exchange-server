/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.goods.entity.GoodsCategoryBrand;

/**
 * 商品分类与品牌关联表 持久层接口
 * <p>File：GoodsCategoryBrandMapper.java </p>
 * <p>Title: GoodsCategoryBrandMapper </p>
 * <p>Description:GoodsCategoryBrandMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GoodsCategoryBrandMapper extends GenericMapper<GoodsCategoryBrand>
{
    /**
     * 根据商品分类Id删除关联的商品品牌
     * @param categoryId
     * @return
     */
    int removeByGoodsCategoryId(Long categoryId);
}
