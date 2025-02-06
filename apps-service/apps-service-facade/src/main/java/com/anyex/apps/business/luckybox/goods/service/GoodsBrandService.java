/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.goods.entity.GoodsBrand;

import java.util.List;

/**
 * 商品品牌表 服务接口
 * <p>File：GoodsBrandService.java </p>
 * <p>Title: GoodsBrandService </p>
 * <p>Description:GoodsBrandService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface GoodsBrandService extends GenericService<GoodsBrand>
{
    /**
     * 根据商品分类ID获取商品分类与品牌关联信息
     * @param categoryId
     * @return {@link GoodsBrand}
     */
    List<GoodsBrand> findByGoodsCategoryId(Long categoryId);
}
