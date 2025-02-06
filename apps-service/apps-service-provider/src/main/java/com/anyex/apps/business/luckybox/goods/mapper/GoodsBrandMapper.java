/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.goods.entity.GoodsBrand;

import java.util.List;

/**
 * 商品品牌表 持久层接口
 * <p>File：GoodsBrandMapper.java </p>
 * <p>Title: GoodsBrandMapper </p>
 * <p>Description:GoodsBrandMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GoodsBrandMapper extends GenericMapper<GoodsBrand>
{
    /**
     * 根据商品分类ID获取商品分类与品牌关联信息
     * @param categoryId
     * @return {@link GoodsBrand}
     */
    List<GoodsBrand> findByGoodsCategoryId(Long categoryId);
}
