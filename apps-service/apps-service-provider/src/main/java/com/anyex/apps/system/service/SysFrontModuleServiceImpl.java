/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.system.entity.SysFrontModule;
import com.anyex.apps.system.mapper.SysFrontModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 前端功能模块表 服务实现类
 * <p>File：FrontModuleServiceImpl.java </p>
 * <p>Title: FrontModuleServiceImpl </p>
 * <p>Description:FrontModuleServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysFrontModuleServiceImpl extends GenericServiceImpl<SysFrontModule> implements SysFrontModuleService
{
    protected SysFrontModuleMapper frontModuleMapper;

    @Autowired(required = false)
    public SysFrontModuleServiceImpl(SysFrontModuleMapper frontModuleMapper)
    {
        super(frontModuleMapper);
        this.frontModuleMapper = frontModuleMapper;
    }

    @Override
    public List<SysFrontModule> findByRoleId(Long roleId) {
        List<SysFrontModule> frontModuleList = frontModuleMapper.findByRoleId(roleId);
        return frontModuleList;
    }
}
