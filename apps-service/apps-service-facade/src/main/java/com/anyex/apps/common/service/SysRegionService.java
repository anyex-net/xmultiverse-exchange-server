/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.common.entity.SysRegion;

import java.util.List;

/**
 * 区域代码 服务接口
 * <p>File：RegionService.java </p>
 * <p>Title: RegionService </p>
 * <p>Description:RegionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysRegionService extends GenericService<SysRegion>
{
    /**
     * 查询常用的区域
     * @return
     */
    List<SysRegion> selectFrequentUsed();
    
    /**
     * 将区域按照中文名首字母进行分组
     * @return
     */
    List<SysRegion> selectRegionGruop();
}
