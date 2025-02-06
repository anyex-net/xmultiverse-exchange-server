/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import com.anyex.apps.exception.BusinessException;

/**
 * 游戏订单记录表 服务接口
 * <p>File：Order4GameService.java </p>
 * <p>Title: Order4GameService </p>
 * <p>Description:Order4GameService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface Order4GameService extends GenericService<Order4Game>
{
    /**
     * 查询游戏订单参与的账户人数
     *
     * @return
     */
    Long getOrder4GameAccountNum() throws BusinessException;

    /**
     * 查询游戏订单参与并中奖的账户人数
     *
     * @return
     */
    Long getOrder4GameIsWinningAccountNum() throws BusinessException;
}
