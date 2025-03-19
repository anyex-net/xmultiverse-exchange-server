/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserLog;
import com.anyex.apps.user.mapper.UserLogMapper;

/**
 * 用户日志 服务实现类
 * <p>File：UserLogServiceImpl.java </p>
 * <p>Title: UserLogServiceImpl </p>
 * <p>Description:UserLogServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserLogServiceImpl extends GenericServiceImpl<UserLog> implements UserLogService
{
    protected UserLogMapper userLogMapper;

    @Autowired(required = false)
    public UserLogServiceImpl(UserLogMapper userLogMapper)
    {
        super(userLogMapper);
        this.userLogMapper = userLogMapper;
    }
}
