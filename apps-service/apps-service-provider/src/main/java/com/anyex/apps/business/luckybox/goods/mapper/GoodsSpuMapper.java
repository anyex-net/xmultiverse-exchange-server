/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSpu;

/**
 * 产品SPU表 持久层接口
 * <p>File：GoodsSpuMapper.java </p>
 * <p>Title: GoodsSpuMapper </p>
 * <p>Description:GoodsSpuMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GoodsSpuMapper extends GenericMapper<GoodsSpu>
{

}
