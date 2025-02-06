/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.AccountAddress;
import com.anyex.apps.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.account.mapper.AccountAddressMapper;

/**
 * 账户地址表 服务实现类
 * <p>File：AccountAddressServiceImpl.java </p>
 * <p>Title: AccountAddressServiceImpl </p>
 * <p>Description:AccountAddressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountAddressServiceImpl extends GenericServiceImpl<AccountAddress> implements AccountAddressService
{
    protected AccountAddressMapper accountAddressMapper;

    @Autowired(required = false)
    public AccountAddressServiceImpl(AccountAddressMapper accountAddressMapper)
    {
        super(accountAddressMapper);
        this.accountAddressMapper = accountAddressMapper;
    }
}
