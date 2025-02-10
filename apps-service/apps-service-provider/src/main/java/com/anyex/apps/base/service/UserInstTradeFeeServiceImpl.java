/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.UserInstTradeFee;
import com.anyex.apps.base.mapper.UserInstTradeFeeMapper;

/**
 * 用户交易手续费费率 服务实现类
 * <p>File：UserInstTradeFeeServiceImpl.java </p>
 * <p>Title: UserInstTradeFeeServiceImpl </p>
 * <p>Description:UserInstTradeFeeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInstTradeFeeServiceImpl extends GenericServiceImpl<UserInstTradeFee> implements UserInstTradeFeeService
{
    protected UserInstTradeFeeMapper userInstTradeFeeMapper;

    @Autowired(required = false)
    public UserInstTradeFeeServiceImpl(UserInstTradeFeeMapper userInstTradeFeeMapper)
    {
        super(userInstTradeFeeMapper);
        this.userInstTradeFeeMapper = userInstTradeFeeMapper;
    }
}
