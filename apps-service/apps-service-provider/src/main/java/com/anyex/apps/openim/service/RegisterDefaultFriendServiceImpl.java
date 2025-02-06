/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.openim.entity.RegisterDefaultFriend;
import com.anyex.apps.openim.mapper.RegisterDefaultFriendMapper;

/**
 * 注册默认好友 服务实现类
 * <p>File：RegisterDefaultFriendServiceImpl.java </p>
 * <p>Title: RegisterDefaultFriendServiceImpl </p>
 * <p>Description:RegisterDefaultFriendServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RegisterDefaultFriendServiceImpl extends GenericServiceImpl<RegisterDefaultFriend> implements RegisterDefaultFriendService
{
    protected RegisterDefaultFriendMapper registerDefaultFriendMapper;

    @Autowired(required = false)
    public RegisterDefaultFriendServiceImpl(RegisterDefaultFriendMapper registerDefaultFriendMapper)
    {
        super(registerDefaultFriendMapper);
        this.registerDefaultFriendMapper = registerDefaultFriendMapper;
    }

    @Override
    public RegisterDefaultFriend findByUserId(String userId) {
        return registerDefaultFriendMapper.findByUserId(userId);
    }
}
