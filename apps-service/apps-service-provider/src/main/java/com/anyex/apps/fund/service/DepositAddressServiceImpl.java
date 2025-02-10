/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.fund.entity.DepositAddress;
import com.anyex.apps.fund.mapper.DepositAddressMapper;

/**
 * 充值地址 服务实现类
 * <p>File：DepositAddressServiceImpl.java </p>
 * <p>Title: DepositAddressServiceImpl </p>
 * <p>Description:DepositAddressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DepositAddressServiceImpl extends GenericServiceImpl<DepositAddress> implements DepositAddressService
{
    protected DepositAddressMapper depositAddressMapper;

    @Autowired(required = false)
    public DepositAddressServiceImpl(DepositAddressMapper depositAddressMapper)
    {
        super(depositAddressMapper);
        this.depositAddressMapper = depositAddressMapper;
    }
}
