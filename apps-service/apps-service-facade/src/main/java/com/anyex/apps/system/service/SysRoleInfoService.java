/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.system.entity.SysRoleInfo;

import java.util.List;

/**
 * 角色信息表 服务接口
 * <p>File：RoleInfoService.java </p>
 * <p>Title: RoleInfoService </p>
 * <p>Description:RoleInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysRoleInfoService extends GenericService<SysRoleInfo>
{
    /**
     * 根据用户ID取角色
     * @param userId
     * @return
     */
    List<SysRoleInfo> findByUserId(Long userId);
    
    /**
     * 保存角色授权
     * @param id
     * @param resourceIds
     * @throws BusinessException
     */
    void saveGrant(Long id, String resourceIds) throws BusinessException;

    /**
     * 保存角色数据
     * @param id
     * @param orgIds
     * @throws BusinessException
     */
    void saveRoleData(Long id, String orgIds) throws BusinessException;

    /**
     * 根据角色Id查询角色和数据权限
     * @param roleId
     * @throws BusinessException
     */
    SysRoleInfo findByRoleId(Long roleId);
}
