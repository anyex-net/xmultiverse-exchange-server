/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserRebate;
import com.anyex.apps.user.mapper.UserRebateMapper;

/**
 * 用户返佣记录 服务实现类
 * <p>File：UserRebateServiceImpl.java </p>
 * <p>Title: UserRebateServiceImpl </p>
 * <p>Description:UserRebateServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserRebateServiceImpl extends GenericServiceImpl<UserRebate> implements UserRebateService
{
    protected UserRebateMapper userRebateMapper;

    @Autowired(required = false)
    public UserRebateServiceImpl(UserRebateMapper userRebateMapper)
    {
        super(userRebateMapper);
        this.userRebateMapper = userRebateMapper;
    }
}
