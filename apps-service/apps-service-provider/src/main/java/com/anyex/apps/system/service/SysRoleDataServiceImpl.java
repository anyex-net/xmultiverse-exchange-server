/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.system.mapper.SysRoleDataMapper;
import com.anyex.apps.system.entity.SysRoleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RoleData 服务实现类
 * <p>File：RoleDataServiceImpl.java </p>
 * <p>Title: RoleDataServiceImpl </p>
 * <p>Description:RoleDataServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysRoleDataServiceImpl extends GenericServiceImpl<SysRoleData> implements SysRoleDataService
{
    protected SysRoleDataMapper roleDataMapper;

    @Autowired(required = false)
    public SysRoleDataServiceImpl(SysRoleDataMapper roleDataMapper)
    {
        super(roleDataMapper);
        this.roleDataMapper = roleDataMapper;
    }

    @Override
    public List<SysRoleData> findByRoleId(Long roleId)
    {
        return roleDataMapper.findByRoleId(roleId);
    }
}
