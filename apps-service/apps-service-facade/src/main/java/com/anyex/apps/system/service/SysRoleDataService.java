/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.system.entity.SysRoleData;

import java.util.List;

/**
 * RoleData 服务接口
 * <p>File：RoleDataService.java </p>
 * <p>Title: RoleDataService </p>
 * <p>Description:RoleDataService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysRoleDataService extends GenericService<SysRoleData>
{
    /**
     * 根据角色ID取角色数据
     * @param roleId
     * @return
     */
    List<SysRoleData> findByRoleId(Long roleId);
}
