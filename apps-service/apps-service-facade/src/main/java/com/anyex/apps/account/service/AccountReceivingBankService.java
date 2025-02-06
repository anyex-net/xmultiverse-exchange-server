/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.bean.GenericService;

/**
 * 账户收款银行表 服务接口
 * <p>File：AccountReceivingBankService.java </p>
 * <p>Title: AccountReceivingBankService </p>
 * <p>Description:AccountReceivingBankService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountReceivingBankService extends GenericService<AccountReceivingBank>
{
    AccountReceivingBank findByAccountIdAndAccountNo(Long accountId,String accountNo);

    AccountReceivingBank findByBankName(Long accountId,String accountType,String bankName);
}
