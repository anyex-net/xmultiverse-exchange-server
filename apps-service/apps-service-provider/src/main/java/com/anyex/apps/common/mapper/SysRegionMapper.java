/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysRegion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 区域代码 持久层接口
 * <p>File：RegionDao.java </p>
 * <p>Title: RegionDao </p>
 * <p>Description:RegionDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysRegionMapper extends GenericMapper<SysRegion>
{
    /**
     * 查询常用的区域
     * @return
     */
    List<SysRegion> selectFrequentUsed();

    /**
     * 根据中文名将地区分组
     * @return
     */
    List<SysRegion> selectRegionGruop();
}
