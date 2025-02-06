/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.goods.entity.GoodsCategory;
import com.anyex.apps.exception.BusinessException;

import java.util.List;

/**
 * 商品分类表 服务接口
 * <p>File：GoodsCategoryService.java </p>
 * <p>Title: GoodsCategoryService </p>
 * <p>Description:GoodsCategoryService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface GoodsCategoryService extends GenericService<GoodsCategory>
{
    /**
     * 取商品分类数据并返回树形对象
     * @return {@link List<  GoodsCategory  >}
     * @throws BusinessException
     */
    List<GoodsCategory> treeData() throws BusinessException;
}
