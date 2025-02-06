/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysRegion;
import com.anyex.apps.common.mapper.SysRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域代码 服务实现类
 * <p>File：Region.java </p>
 * <p>Title: Region </p>
 * <p>Description:Region </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysRegionServiceImpl extends GenericServiceImpl<SysRegion> implements SysRegionService
{
    SysRegionMapper regionMapper;
    
    @Autowired
    public SysRegionServiceImpl(SysRegionMapper regionMapper)
    {
        super(regionMapper);
        this.regionMapper = regionMapper;
    }
    
    @Override
    public List<SysRegion> selectFrequentUsed()
    {
        return regionMapper.selectFrequentUsed();
    }
    
    @Override
    public List<SysRegion> selectRegionGruop()
    {
        return regionMapper.selectRegionGruop();
    }
}
