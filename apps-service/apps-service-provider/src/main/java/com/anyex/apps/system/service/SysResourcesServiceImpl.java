/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.google.common.collect.Lists;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.mapper.SysResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源菜单信息表 服务实现类
 * <p>File：Resources.java </p>
 * <p>Title: Resources </p>
 * <p>Description:Resources </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysResourcesServiceImpl extends GenericServiceImpl<SysResources> implements SysResourcesService
{
    private SysResourcesMapper resourcesMapper;
    
    @Autowired
    public SysResourcesServiceImpl(SysResourcesMapper resourcesMapper)
    {
        super(resourcesMapper);
        this.resourcesMapper = resourcesMapper;
    }
    
    @Override
    public List<SysResources> findByRoleId(Long roleId)
    {
        return resourcesMapper.findByRoleId(roleId);
    }

    @Override
    public List<SysResources> findByUserId(Long userId)
    {
        return resourcesMapper.findByUserId(userId);
    }
    
    @Override
    public List<SysResources> treeData() throws BusinessException
    {
        List<SysResources> data = Lists.newArrayList();
        List<SysResources> entitys = resourcesMapper.selectAll();
        for (SysResources parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (SysResources child : entitys)
            {
                if (parent.getId().equals(child.getParentId()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        return data;
    }
}
