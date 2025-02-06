/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 钱包资产转账记录表 持久层接口
 * <p>File：WalletAssetTransactionsMapper.java </p>
 * <p>Title: WalletAssetTransactionsMapper </p>
 * <p>Description:WalletAssetTransactionsMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface WalletAssetTransactionsMapper extends GenericMapper<WalletAssetTransactions>
{
    WalletAssetTransactions findByTrxNo(@Param("trxNo") String trxNo);

    /**
     * 查询当日提现总额
     * @param accountId 账户ID（2选1）
     * @param accountNo 收款账号(2选1）
     * @return
     */
    BigDecimal getTotalWithDrawCurrDay(@Param("accountId") Long accountId, @Param("accountNo")String accountNo);

}
