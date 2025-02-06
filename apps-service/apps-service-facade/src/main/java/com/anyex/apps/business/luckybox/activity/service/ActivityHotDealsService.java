/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityHotDeals;
import com.anyex.apps.bean.GenericService;

/**
 * 活动半价购买表 服务接口
 * <p>File：ActivityHotDealsService.java </p>
 * <p>Title: ActivityHotDealsService </p>
 * <p>Description:ActivityHotDealsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface ActivityHotDealsService extends GenericService<ActivityHotDeals>
{
    /**
     * 参加活动 满足条件即可开奖
     *
     * @param accountId
     * @param activityId
     * @param activityPurchaseNum
     */
    void attendActivityHotDeals(Long accountId, Long activityId, Integer activityPurchaseNum);

    /**
     * 尾款支付
     *
     * @param accountId
     * @param order4ActivityId
     */
    void balancePayment(Long accountId, Long order4ActivityId);

    /**
     * 抵扣返现
     *
     * @param accountId
     * @param order4ActivityId
     */
    void refund(Long accountId, Long order4ActivityId);
}
