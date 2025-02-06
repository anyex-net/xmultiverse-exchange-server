/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.service;

import com.anyex.apps.business.luckybox.activity.entity.ActivityOperRecord;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.activity.mapper.ActivityOperRecordMapper;
import com.anyex.apps.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品活动操作记录表 服务实现类
 * <p>File：ActivityOperRecordServiceImpl.java </p>
 * <p>Title: ActivityOperRecordServiceImpl </p>
 * <p>Description:ActivityOperRecordServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ActivityOperRecordServiceImpl extends GenericServiceImpl<ActivityOperRecord> implements ActivityOperRecordService
{
    protected ActivityOperRecordMapper activityOperRecordMapper;

    @Autowired(required = false)
    public ActivityOperRecordServiceImpl(ActivityOperRecordMapper activityOperRecordMapper)
    {
        super(activityOperRecordMapper);
        this.activityOperRecordMapper = activityOperRecordMapper;
    }

    @Override
    public Long getActivityOperRecordNum(String activityType, String operType) throws BusinessException
    {
        return activityOperRecordMapper.getActivityOperRecordNum(activityType, operType);
    }
}
