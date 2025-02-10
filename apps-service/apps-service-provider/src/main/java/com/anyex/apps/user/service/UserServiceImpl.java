/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.mapper.UserMapper;

/**
 * 用户信息 服务实现类
 * <p>File：UserServiceImpl.java </p>
 * <p>Title: UserServiceImpl </p>
 * <p>Description:UserServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService
{
    protected UserMapper userMapper;

    @Autowired(required = false)
    public UserServiceImpl(UserMapper userMapper)
    {
        super(userMapper);
        this.userMapper = userMapper;
    }
}
