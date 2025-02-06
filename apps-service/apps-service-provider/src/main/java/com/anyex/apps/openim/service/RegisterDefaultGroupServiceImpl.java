/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.openim.entity.RegisterDefaultGroup;
import com.anyex.apps.openim.mapper.RegisterDefaultGroupMapper;

/**
 * 注册默认群 服务实现类
 * <p>File：RegisterDefaultGroupServiceImpl.java </p>
 * <p>Title: RegisterDefaultGroupServiceImpl </p>
 * <p>Description:RegisterDefaultGroupServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RegisterDefaultGroupServiceImpl extends GenericServiceImpl<RegisterDefaultGroup> implements RegisterDefaultGroupService
{
    protected RegisterDefaultGroupMapper registerDefaultGroupMapper;

    @Autowired(required = false)
    public RegisterDefaultGroupServiceImpl(RegisterDefaultGroupMapper registerDefaultGroupMapper)
    {
        super(registerDefaultGroupMapper);
        this.registerDefaultGroupMapper = registerDefaultGroupMapper;
    }

    @Override
    public RegisterDefaultGroup findByGroupId(String groupId) {
        return registerDefaultGroupMapper.findByGroupId(groupId);
    }
}
