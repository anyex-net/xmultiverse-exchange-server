/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.shop.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.shop.entity.Shop;

/**
 * 店铺表 持久层接口
 * <p>File：ShopMapper.java </p>
 * <p>Title: ShopMapper </p>
 * <p>Description:ShopMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ShopMapper extends GenericMapper<Shop>
{

}
