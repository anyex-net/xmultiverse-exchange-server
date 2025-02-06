/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.goods.entity.GoodsCategoryBrand;

/**
 * 商品分类与品牌关联表 服务接口
 * <p>File：GoodsCategoryBrandService.java </p>
 * <p>Title: GoodsCategoryBrandService </p>
 * <p>Description:GoodsCategoryBrandService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface GoodsCategoryBrandService extends GenericService<GoodsCategoryBrand>
{
    /**
     * 保存商品分类与品牌关联
     * @param id
     * @param goodsBrandIds
     * @throws BusinessException
     */
    void saveGoodsCategoryBrand(Long id, String goodsBrandIds) throws BusinessException;
}
