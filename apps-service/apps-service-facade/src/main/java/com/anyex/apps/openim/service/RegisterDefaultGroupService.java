/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.openim.entity.RegisterDefaultGroup;

/**
 * 注册默认群 服务接口
 * <p>File：RegisterDefaultGroupService.java </p>
 * <p>Title: RegisterDefaultGroupService </p>
 * <p>Description:RegisterDefaultGroupService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RegisterDefaultGroupService extends GenericService<RegisterDefaultGroup>
{
    RegisterDefaultGroup findByGroupId(String groupId);
}
