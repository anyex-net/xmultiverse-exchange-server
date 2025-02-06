/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.shop.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.shop.entity.Shop;
import com.anyex.apps.business.luckybox.shop.mapper.ShopMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 店铺表 服务实现类
 * <p>File：ShopServiceImpl.java </p>
 * <p>Title: ShopServiceImpl </p>
 * <p>Description:ShopServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ShopServiceImpl extends GenericServiceImpl<Shop> implements ShopService
{
    protected ShopMapper shopMapper;

    @Autowired(required = false)
    public ShopServiceImpl(ShopMapper shopMapper)
    {
        super(shopMapper);
        this.shopMapper = shopMapper;
    }
}
