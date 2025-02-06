/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;

/**
 * 商品SKU表 持久层接口
 * <p>File：GoodsSkuMapper.java </p>
 * <p>Title: GoodsSkuMapper </p>
 * <p>Description:GoodsSkuMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GoodsSkuMapper extends GenericMapper<GoodsSku>
{

}
