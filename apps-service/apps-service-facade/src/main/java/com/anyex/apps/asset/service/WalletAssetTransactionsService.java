/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.account.entity.AccountReceivingBank;
import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.model.AssetDepositApplyResultModel;
import com.anyex.apps.asset.model.AssetWithdrawApplyResultModel;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;

import java.math.BigDecimal;

/**
 * 钱包资产转账记录表 服务接口
 * <p>File：WalletAssetTransactionsService.java </p>
 * <p>Title: WalletAssetTransactionsService </p>
 * <p>Description:WalletAssetTransactionsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface WalletAssetTransactionsService extends GenericService<WalletAssetTransactions>
{
    /**
     * 交易编码查询记录
     * @param trxNo 交易编码
     * @return
     */
    WalletAssetTransactions findByTrxNo(String trxNo);

    /**
     * 充值申请
     * @Param channelAliasName 支付通道
     * @Param walletType
     * @param cnic 身份证号码
     * @param realName 真实姓名
     * @param mobile 手机号
     * @param email 邮箱
     * @param amount 金额
     * @param accountId 账户id
     * @return
     * @throws BusinessException
     */
    AssetDepositApplyResultModel depositApply(String trxChannel,String walletType,String cnic, String realName, String mobile, String email, Double amount, Long accountId) throws BusinessException;

    /**
     * 充值回调
     * @param trxNo 交易编码
     * @param status 状态
     * @return
     * @throws BusinessException
     */
    String depositNotify(String trxNo, String status,String reqJsonStr) throws BusinessException;

    /**
     * 查询状态并更新
     * @param id 记录id
     * @param accountId 账户id
     * @throws BusinessException
     */
    void depositQueryAndUpdate(Long id,Long accountId) throws BusinessException;

    /**
     * 提现申请
     * @Param trxChannel 支付通道
     * @param bank 银行卡信息
     * @param amount 提现金额
     * @return
     */
    AssetWithdrawApplyResultModel withdrawApply(AccountReceivingBank bank,BigDecimal amount,String trxChannel);

    /**
     * 提现结果回调
     * @param trxNo 交易编码
     * @param status 状态
     * @return
     * @throws BusinessException
     */
    String withdrawNotify(String trxNo, String status ,String reqJsonStr) throws BusinessException;


    /**
     * 查询状态并更新
     * @param id 记录id
     * @param accountId 账户id
     * @throws BusinessException
     */
    void withdrawQueryAndUpdate(Long id,Long accountId) throws BusinessException;

    /**
     * 充值状态查询调度服务
     */
    void queryDepositStatusTask();

    /**
     * 提现状态查询调度服务
     */
    void queryWithdrawStatusTask();


    void walletAssetAdjust(Long accountId,Integer direction,BigDecimal amount, String attachment, String remark) throws BusinessException;

    /**
     * 查询当日提现总额
     * @param accountId 账户ID（2选1）
     * @param accountNo 收款账号(2选1）
     * @return
     */
    BigDecimal getTotalWithDrawCurrDay(Long accountId, String accountNo);
}
