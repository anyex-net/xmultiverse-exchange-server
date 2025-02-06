/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.activity.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.activity.entity.ActivityHotDeals;

/**
 * 活动半价购买表 持久层接口
 * <p>File：ActivityHotDealsMapper.java </p>
 * <p>Title: ActivityHotDealsMapper </p>
 * <p>Description:ActivityHotDealsMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ActivityHotDealsMapper extends GenericMapper<ActivityHotDeals>
{

}
