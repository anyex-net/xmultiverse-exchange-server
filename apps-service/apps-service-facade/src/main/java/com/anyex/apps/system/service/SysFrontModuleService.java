/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.system.entity.SysFrontModule;

import java.util.List;

/**
 * 前端功能模块表 服务接口
 * <p>File：FrontModuleService.java </p>
 * <p>Title: FrontModuleService </p>
 * <p>Description:FrontModuleService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysFrontModuleService extends GenericService<SysFrontModule>
{
    List<SysFrontModule> findByRoleId(Long roleId);
}
