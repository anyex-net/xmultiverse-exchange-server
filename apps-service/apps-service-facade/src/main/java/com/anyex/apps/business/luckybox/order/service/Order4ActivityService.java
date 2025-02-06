/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.order.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.order.entity.Order4Activity;
import com.anyex.apps.exception.BusinessException;

/**
 * 活动订单记录表 服务接口
 * <p>File：Order4ActivityService.java </p>
 * <p>Title: Order4ActivityService </p>
 * <p>Description:Order4ActivityService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface Order4ActivityService extends GenericService<Order4Activity>
{
    /**
     * 查询活动订单参与的账户人数
     *
     * @return
     */
    Long getOrder4ActivityAccountNum(String activityType) throws BusinessException;

    /**
     * 查询活动订单参与并中奖的账户人数
     *
     * @return
     */
    Long getOrder4ActivityIsWinningAccountNum(String activityType) throws BusinessException;

    /**
     * 一元夺宝活动订单中奖未领取 进行领取中奖
     * @return
     * @throws BusinessException
     */
    void treasureHuntOrder4ActivityClaimLottery() throws BusinessException;

    /**
     * 一元夺宝活动订单中奖未领取 进行账户领奖处理
     * @param order4ActivityWinning
     * @throws BusinessException
     */
    void treasureHuntOrder4ActivityClaimLottery4SingalAccount(Order4Activity order4ActivityWinning) throws BusinessException;
}
