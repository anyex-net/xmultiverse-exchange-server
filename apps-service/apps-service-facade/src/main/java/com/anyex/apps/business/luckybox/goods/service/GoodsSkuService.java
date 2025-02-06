/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;

/**
 * 商品SKU表 服务接口
 * <p>File：GoodsSkuService.java </p>
 * <p>Title: GoodsSkuService </p>
 * <p>Description:GoodsSkuService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface GoodsSkuService extends GenericService<GoodsSku>
{
    /**
     * 随机调减商品SKU的库存信息
     */
    void adjustGoodsSkuStock();
}
