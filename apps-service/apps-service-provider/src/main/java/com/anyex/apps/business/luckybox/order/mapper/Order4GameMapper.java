/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import org.apache.ibatis.annotations.Mapper;

/**
 * 游戏订单记录表 持久层接口
 * <p>File：Order4GameMapper.java </p>
 * <p>Title: Order4GameMapper </p>
 * <p>Description:Order4GameMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface Order4GameMapper extends GenericMapper<Order4Game>
{
    /**
     * 查询游戏订单参与的账户人数
     * @return
     */
    Long getOrder4GameAccountNum();

    /**
     * 查询游戏订单参与并中奖的账户人数
     * @return
     */
    Long getOrder4GameIsWinningAccountNum();
}
