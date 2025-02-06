/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.system.entity.SysOrganization;

import java.util.List;

/**
 * 机构信息表 服务接口
 * <p>File：OrganizationService.java </p>
 * <p>Title: OrganizationService </p>
 * <p>Description:OrganizationService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface SysOrganizationService extends GenericService<SysOrganization> {
    /**
     * 查询机构信息并返回树形对象
     *
     * @return {@link List}
     * @throws BusinessException
     */
    List<SysOrganization> treeData() throws BusinessException;
}
