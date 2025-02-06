/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.system.entity.SysResources;

import java.util.List;

/**
 * 资源菜单信息表 服务接口
 * <p>File：ResourcesService.java </p>
 * <p>Title: ResourcesService </p>
 * <p>Description:ResourcesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysResourcesService extends GenericService<SysResources>
{
    /**
     * 根据角色ID取资源菜单
     * @param roleId
     * @return {@link SysResources}
     */
    List<SysResources> findByRoleId(Long roleId);

    /**
     * 根据用户ID取资源菜单
     * @param userId
     * @return {@link SysResources}
     */
    List<SysResources> findByUserId(Long userId);

    /**
     * 取资源数据并返回树形对象
     * @return {@link List<  SysResources  >}
     * @throws BusinessException
     */
    List<SysResources> treeData() throws BusinessException;
}
