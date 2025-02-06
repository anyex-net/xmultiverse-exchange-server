/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.order.mapper.Order4GameMapper;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 游戏订单记录表 服务实现类
 * <p>File：Order4GameServiceImpl.java </p>
 * <p>Title: Order4GameServiceImpl </p>
 * <p>Description:Order4GameServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class Order4GameServiceImpl extends GenericServiceImpl<Order4Game> implements Order4GameService
{
    protected Order4GameMapper order4GameMapper;

    @Autowired(required = false)
    public Order4GameServiceImpl(Order4GameMapper order4GameMapper)
    {
        super(order4GameMapper);
        this.order4GameMapper = order4GameMapper;
    }

    @Override
    public Long getOrder4GameAccountNum() throws BusinessException
    {
        return order4GameMapper.getOrder4GameAccountNum();
    }

    @Override
    public Long getOrder4GameIsWinningAccountNum() throws BusinessException
    {
        return order4GameMapper.getOrder4GameIsWinningAccountNum();
    }
}
