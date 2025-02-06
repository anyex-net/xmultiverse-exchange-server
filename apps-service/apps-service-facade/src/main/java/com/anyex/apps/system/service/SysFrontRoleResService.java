/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;


import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.system.entity.SysFrontRoleRes;

/**
 * 前端角色权限信息表 服务接口
 * <p>File：FrontRoleResService.java </p>
 * <p>Title: FrontRoleResService </p>
 * <p>Description:FrontRoleResService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysFrontRoleResService extends GenericService<SysFrontRoleRes>
{
    /**
     * 保存角色授权
     * @param roleId
     * @param moduleIds
     * @throws BusinessException
     */
    void saveGrant(Long roleId, String moduleIds) throws BusinessException;
}
