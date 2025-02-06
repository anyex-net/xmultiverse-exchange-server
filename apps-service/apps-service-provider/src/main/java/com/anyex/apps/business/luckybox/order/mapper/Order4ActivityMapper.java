/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;

/**
 * 活动订单记录表 持久层接口
 * <p>File：Order4ActivityMapper.java </p>
 * <p>Title: Order4ActivityMapper </p>
 * <p>Description:Order4ActivityMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface Order4ActivityMapper extends GenericMapper<Order4Activity>
{
    /**
     * 查询活动订单参与的账户人数
     * @return
     */
    Long getOrder4ActivityAccountNum(String activityType);

    /**
     * 查询活动订单参与并中奖的账户人数
     * @return
     */
    Long getOrder4ActivityIsWinningAccountNum(String activityType);
}
