/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserCertKyc;
import com.anyex.apps.user.mapper.UserCertKycMapper;

/**
 * 用户认证个人KYC 服务实现类
 * <p>File：UserCertKycServiceImpl.java </p>
 * <p>Title: UserCertKycServiceImpl </p>
 * <p>Description:UserCertKycServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserCertKycServiceImpl extends GenericServiceImpl<UserCertKyc> implements UserCertKycService
{
    protected UserCertKycMapper userCertKycMapper;

    @Autowired(required = false)
    public UserCertKycServiceImpl(UserCertKycMapper userCertKycMapper)
    {
        super(userCertKycMapper);
        this.userCertKycMapper = userCertKycMapper;
    }
}
