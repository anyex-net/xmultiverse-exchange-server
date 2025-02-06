/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.AccountReceivingBank;
import org.apache.ibatis.annotations.Param;

/**
 * 账户收款银行表 持久层接口
 * <p>File：AccountReceivingBankMapper.java </p>
 * <p>Title: AccountReceivingBankMapper </p>
 * <p>Description:AccountReceivingBankMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountReceivingBankMapper extends GenericMapper<AccountReceivingBank>
{
    AccountReceivingBank findByAccountIdAndAccountNo(@Param("accountId") Long accountId, @Param("accountNo")String accountNo);

    AccountReceivingBank findByBankName(@Param("accountId")Long accountId,@Param("accountType")String accountType,@Param("bankName")String bankName);

}
