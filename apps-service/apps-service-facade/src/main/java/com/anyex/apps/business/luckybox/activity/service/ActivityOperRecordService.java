/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityOperRecord;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;

/**
 * 活动操作记录表 服务接口
 * <p>File：ActivityOperRecordService.java </p>
 * <p>Title: ActivityOperRecordService </p>
 * <p>Description:ActivityOperRecordService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface ActivityOperRecordService extends GenericService<ActivityOperRecord>
{
    /**
     * 查询活动操作记录数量
     *
     * @return
     */
    Long getActivityOperRecordNum(String activityType, String operType) throws BusinessException;
}
