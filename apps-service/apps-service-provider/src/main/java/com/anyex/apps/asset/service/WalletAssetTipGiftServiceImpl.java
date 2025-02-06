/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetAdjust;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.asset.mapper.WalletAssetTipGiftMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 钱包资产打赏礼物记录 服务实现类
 * <p>File：WalletAssetTipGiftServiceImpl.java </p>
 * <p>Title: WalletAssetTipGiftServiceImpl </p>
 * <p>Description:WalletAssetTipGiftServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class WalletAssetTipGiftServiceImpl extends GenericServiceImpl<WalletAssetTipGift> implements WalletAssetTipGiftService
{
    protected WalletAssetTipGiftMapper walletAssetTipGiftMapper;

    @Autowired
    protected WalletAssetService walletAssetService;

    @Autowired
    WalletAssetFlowsService walletAssetFlowsService;

    @Autowired
    WalletAssetTipGiftService walletAssetTipGiftService;


    @Autowired(required = false)
    public WalletAssetTipGiftServiceImpl(WalletAssetTipGiftMapper walletAssetTipGiftMapper)
    {
        super(walletAssetTipGiftMapper);
        this.walletAssetTipGiftMapper = walletAssetTipGiftMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sendGift(WalletAssetTipGift req) throws BusinessException {
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(req.getFromAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("账户{} PKR 资产不存在", req.getFromAccountId());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        Long bizId = SerialnoUtils.buildPrimaryKey();
        String trxNo = SerialnoUtils.getOrderNum();

        WalletAssetTipGift gift = new WalletAssetTipGift();
        gift.setFromAccountId(req.getFromAccountId());
        gift.setToAccountId(req.getToAccountId());
        gift.setCurrency(GlobalConst.CURRENCY_PKR);
        gift.setTrxNo(trxNo);
        gift.setTrxBalance(req.getTrxBalance());
        gift.setTrxFee(req.getTrxFee());
        gift.setStatus(1);
        gift.setCreateTime(System.currentTimeMillis());
        gift.setUpdateTime(System.currentTimeMillis());
        gift.setId(bizId);
        gift.setRemark(req.getRemark());
        walletAssetTipGiftService.insert(gift);

        // 更新资产
        BigDecimal oldBalance = asset.getBalance();
        asset.setBalance(asset.getBalance().subtract(req.getTrxBalance()));
        asset.setUpdateTime(System.currentTimeMillis());
        walletAssetService.updateByPrimaryKey(asset);
        if(asset.getBalance().compareTo(BigDecimal.ZERO)<0)
        {
            log.error("资产购买礼物为负数");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }
        // 资金流水
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(req.getFromAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_ASSET_SENDGITF);
        flows.setBeforeBalance(oldBalance);
        flows.setDirection("-");
        flows.setBalance(req.getTrxBalance());
        flows.setFee(BigDecimal.ZERO);
        flows.setAfterBalance(asset.getBalance());
        flows.setOrgBusinessId(bizId);
        flows.setOrgBusinessNo(trxNo);
        flows.setStatus(true);
        flows.setCreateTime(System.currentTimeMillis());
        flows.setUpdateTime(System.currentTimeMillis());
        flows.setRemark("Asset-giftSend");
        walletAssetFlowsService.insert(flows);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void getGift(WalletAssetTipGift req) throws BusinessException {
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(req.getToAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("账户{} PKR 资产不存在", req.getToAccountId());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        WalletAssetTipGift gift = walletAssetTipGiftService.selectByPrimaryKey(req.getId());
        gift.setStatus(2);
        gift.setUpdateTime(System.currentTimeMillis());
        walletAssetTipGiftService.updateByPrimaryKey(gift);

        // 更新资产
        BigDecimal oldBalance = asset.getBalance();
        asset.setBalance(asset.getBalance().add(req.getTrxBalance()).subtract(req.getTrxFee()));
        asset.setUpdateTime(System.currentTimeMillis());
        walletAssetService.updateByPrimaryKey(asset);
        // 资金流水
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(req.getToAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_ASSET_GETGIFT);
        flows.setBeforeBalance(oldBalance);
        flows.setDirection("+");
        flows.setBalance(req.getTrxBalance().subtract(req.getTrxFee()));
        flows.setFee(BigDecimal.ZERO);
        flows.setAfterBalance(asset.getBalance());
        flows.setOrgBusinessId(gift.getId());
        flows.setOrgBusinessNo(gift.getTrxNo());
        flows.setStatus(true);
        flows.setCreateTime(System.currentTimeMillis());
        flows.setUpdateTime(System.currentTimeMillis());
        flows.setRemark("Asset-giftGet");
        walletAssetFlowsService.insert(flows);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());
    }
}
