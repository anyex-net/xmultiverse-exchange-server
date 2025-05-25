/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserApi;
import com.anyex.apps.user.mapper.UserApiMapper;

/**
 * 用户API 服务实现类
 * <p>File：UserApiServiceImpl.java </p>
 * <p>Title: UserApiServiceImpl </p>
 * <p>Description:UserApiServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserApiServiceImpl extends GenericServiceImpl<UserApi> implements UserApiService
{
    protected UserApiMapper userApiMapper;

    @Autowired(required = false)
    public UserApiServiceImpl(UserApiMapper userApiMapper)
    {
        super(userApiMapper);
        this.userApiMapper = userApiMapper;
    }
}
