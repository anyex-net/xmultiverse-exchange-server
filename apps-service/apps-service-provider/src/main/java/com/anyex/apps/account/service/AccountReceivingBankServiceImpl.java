/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.account.mapper.AccountReceivingBankMapper;

/**
 * 账户收款银行表 服务实现类
 * <p>File：AccountReceivingBankServiceImpl.java </p>
 * <p>Title: AccountReceivingBankServiceImpl </p>
 * <p>Description:AccountReceivingBankServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountReceivingBankServiceImpl extends GenericServiceImpl<AccountReceivingBank> implements AccountReceivingBankService
{
    protected AccountReceivingBankMapper accountReceivingBankMapper;

    @Autowired(required = false)
    public AccountReceivingBankServiceImpl(AccountReceivingBankMapper accountReceivingBankMapper)
    {
        super(accountReceivingBankMapper);
        this.accountReceivingBankMapper = accountReceivingBankMapper;
    }

    @Override
    public AccountReceivingBank findByAccountIdAndAccountNo(Long accountId, String accountNo) {
        return accountReceivingBankMapper.findByAccountIdAndAccountNo(accountId, accountNo);
    }

    @Override
    public AccountReceivingBank findByBankName(Long accountId, String accountType, String bankName) {
        return accountReceivingBankMapper.findByBankName(accountId, accountType, bankName);
    }
}
