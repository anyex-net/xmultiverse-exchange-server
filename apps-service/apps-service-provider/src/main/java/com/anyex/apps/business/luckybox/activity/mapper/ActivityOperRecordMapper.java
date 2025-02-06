/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.activity.entity.ActivityOperRecord;

/**
 * 商品活动操作记录表 持久层接口
 * <p>File：ActivityOperRecordMapper.java </p>
 * <p>Title: ActivityOperRecordMapper </p>
 * <p>Description:ActivityOperRecordMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ActivityOperRecordMapper extends GenericMapper<ActivityOperRecord>
{
    /**
     * 查询活动操作记录数量
     * @return
     */
    Long getActivityOperRecordNum(String activityType, String operType);
}
