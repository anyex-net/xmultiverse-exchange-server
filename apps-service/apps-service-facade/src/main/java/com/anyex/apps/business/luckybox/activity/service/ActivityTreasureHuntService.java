/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityTreasureHunt;
import com.anyex.apps.bean.GenericService;

/**
 * 活动一元夺宝表 服务接口
 * <p>File：ActivityTreasureHuntService.java </p>
 * <p>Title: ActivityTreasureHuntService </p>
 * <p>Description:ActivityTreasureHuntService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface ActivityTreasureHuntService extends GenericService<ActivityTreasureHunt>
{
    /**
     * 参加活动 满足条件即可开奖
     *
     * @param accountId
     * @param activityId
     * @param activityPurchaseNum
     */
    void attendActivityTreasureHunt(Long accountId, Long activityId, Integer activityPurchaseNum);
}
