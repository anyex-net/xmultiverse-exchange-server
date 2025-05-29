/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.rwa.entity.*;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.mapper.RwaBalancesMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * RWA账户余额 服务实现类
 * <p>File：RwaBalancesServiceImpl.java </p>
 * <p>Title: RwaBalancesServiceImpl </p>
 * <p>Description:RwaBalancesServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class RwaBalancesServiceImpl extends GenericServiceImpl<RwaBalances> implements RwaBalancesService
{
    private final RwaInstSpvProductMapper rwaInstSpvProductMapper;

    protected RwaBalancesMapper rwaBalancesMapper;

    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @Autowired(required = false)
    public RwaBalancesServiceImpl(RwaBalancesMapper rwaBalancesMapper, RwaInstSpvProductMapper rwaInstSpvProductMapper)
    {
        super(rwaBalancesMapper);
        this.rwaBalancesMapper = rwaBalancesMapper;
        this.rwaInstSpvProductMapper = rwaInstSpvProductMapper;
    }

    @Override
    public void purchaseFrozenBalCheckBefore(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
//        申购前 申购者 总余额不变，冻结增加，可用余额减少
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesDB == null) {
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        if (purchaseBalance.compareTo(rwaBalancesDB.getAvailBal()) > 0) {
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_INSUFFICIENT_AVAILABLE_BALANCE);
        }
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().subtract(purchaseBalance));
        rwaBalancesDB.setFrozenBal(purchaseBalance.add(rwaBalancesDB.getFrozenBal()));
//        rwaBalances1.setBalance(rwaBalances1.getBalance().subtract(purchaseBalance));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
    }

    @Override
    public void purchaseFrozenBalUncheck(List<RwaInstSpvProductPurchase> rwaInstSpvProductPurchases) throws BusinessException{
        List<RwaBalances> rwaBalancesList = new java.util.ArrayList<>();
        List<RwaBalancesTransHistory> rwaBalancesTransHistoryList = new java.util.ArrayList<>();
        for (RwaInstSpvProductPurchase rwaInstSpvProductPurchase : rwaInstSpvProductPurchases) {
            //申购拒绝 申购者 总余额不变，冻结减少，可用余额增加
            RwaBalances rwaBalances = new RwaBalances();
            rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
            rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
            RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOne(rwaBalances);
            if (rwaBalancesDB == null) {
                throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
            }
            BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
            rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().add(purchaseBalance));
            rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(purchaseBalance));
            rwaBalancesList.add(rwaBalances);
            //
            RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
            rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
            rwaBalancesTransHistory.setUserId(rwaInstSpvProductPurchase.getUserId());
            rwaBalancesTransHistory.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
            rwaBalancesTransHistory.setType("unPurchase");
            rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
            rwaBalancesTransHistory.setChangeAmt(purchaseBalance);
            rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().add(purchaseBalance));
            rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
            rwaBalancesTransHistory.setState("success");
            rwaBalancesTransHistory.setTransDesc("申购退回");
            rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
            rwaBalancesTransHistoryList.add(rwaBalancesTransHistory);
        }
        rwaBalancesMapper.updateBatch(rwaBalancesList);
        rwaBalancesTransHistoryService.insertBatch(rwaBalancesTransHistoryList);
    }

    @Override
    public void purchaseFrozenBalCheckAfter(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        //申购审核后 申购者 总余额减少，冻结减少，可用余额不变
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (null == rwaBalancesDB) {
            log.error("User balance not found.");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().subtract(purchaseBalance));
        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(purchaseBalance));
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getBalance().subtract(rwaBalancesDB.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
        //资金历史表
        RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
        rwaBalancesTransHistory.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalancesTransHistory.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        rwaBalancesTransHistory.setType("purchase");
        rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
        rwaBalancesTransHistory.setChangeAmt(purchaseBalance);
        rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().subtract(purchaseBalance));
        rwaBalancesTransHistory.setState("success");
        rwaBalancesTransHistory.setTransDesc("申购");
        rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
        //申购审核后 募集者 总余额增加，冻结增加，可用余额不变
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductMapper.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
        if (null == rwaInstSpvProduct){
            log.error("InstSpvProduct not found.");
            throw new BusinessException(CommonEnums.ERROR_RWA_INST_SPV_PRODUCT_NOT_FOUND);
        }
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalancesRaise = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (null == rwaBalancesRaise) {
            log.error("User balance not found.");
            throw new BusinessException(CommonEnums.ERROR_RWA_RAISE_USER_BALANCE_NOT_FOUND);
        }
        rwaBalancesRaise.setBalance(rwaBalancesRaise.getBalance().add(purchaseBalance));
        rwaBalancesRaise.setFrozenBal(rwaBalancesRaise.getFrozenBal().add(purchaseBalance));
        rwaBalancesRaise.setAvailBal(rwaBalancesRaise.getBalance().subtract(rwaBalancesRaise.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesRaise);
//        //
//        RwaBalancesTransHistory rwaBalancesTransHistoryRaise = new RwaBalancesTransHistory();
//        rwaBalancesTransHistoryRaise.setUserId(rwaInstSpvProduct.getUserId());
//        rwaBalancesTransHistoryRaise.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
//        rwaBalancesTransHistoryRaise.setType("purchase");
//        rwaBalancesTransHistoryRaise.setBeforeBal(rwaBalancesRaise.getAvailBal());
//        rwaBalancesTransHistoryRaise.setChangeAmt(purchaseBalance);
//        rwaBalancesTransHistoryRaise.setAfterBal(rwaBalancesDB.getAvailBal().add(purchaseBalance));
//        rwaBalancesTransHistoryRaise.setState("success");
//        rwaBalancesTransHistoryRaise.setTransDesc("申购");
//        rwaBalancesTransHistoryRaise.setCreateTime(System.currentTimeMillis());
//        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
    }

    @Override
    public void raiseMarginFrozenBal(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        //保证金 缴纳 总余额不变，冻结增加，可用余额减少
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesDB == null) {
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMargin();
        if (raiseMargin.compareTo(rwaBalancesDB.getAvailBal()) > 0) {
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_INSUFFICIENT_AVAILABLE_BALANCE);
        }
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().subtract(raiseMargin));
        rwaBalancesDB.setFrozenBal(raiseMargin.add(rwaBalancesDB.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
        //资金历史表
        RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
        rwaBalancesTransHistory.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalancesTransHistory.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        rwaBalancesTransHistory.setType("raiseForzen");
        rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
        rwaBalancesTransHistory.setChangeAmt(raiseMargin);
        rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().subtract(raiseMargin));
        rwaBalancesTransHistory.setState("success");
        rwaBalancesTransHistory.setTransDesc("保证金冻结");
        rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
    }

    @Override
    public void raiseMarginFrozenBalUncheck(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        //保证金审核被拒绝 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOne(rwaBalances);
        if (rwaBalancesDB == null) {
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMargin();
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().add(raiseMargin));
        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(raiseMargin));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);

        //资金历史表
        RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
        rwaBalancesTransHistory.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalancesTransHistory.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        rwaBalancesTransHistory.setType("unRaiseForzen");
        rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
        rwaBalancesTransHistory.setChangeAmt(raiseMargin);
        rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().add(raiseMargin));
        rwaBalancesTransHistory.setState("success");
        rwaBalancesTransHistory.setTransDesc("保证金解冻");
        rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
    }

    @Override
    public void unFrozenBal(RwaInstSpvProductAsset rwaInstSpvProductAsset) throws BusinessException {
        //申请资产解冻 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductAsset.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductAsset.getCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesDB == null) {
            log.error("RWA用户资产不存在");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductMapper.selectByPrimaryKey(rwaInstSpvProductAsset.getInstSpvProductId());
        if (rwaInstSpvProduct == null) {
            log.error("RWA机构SPV产品不存在");
        }
        BigDecimal issuePrice = null;
        if (rwaInstSpvProduct != null) {
            issuePrice = rwaInstSpvProduct.getRaiseAmount().divide(rwaInstSpvProduct.getRaiseAmount(), 8, RoundingMode.HALF_UP);
        }
        BigDecimal lastAmount = (rwaInstSpvProductAsset.getLastAmount()).multiply(issuePrice);
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().add(lastAmount));
        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(lastAmount));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);

        //资金历史表
        RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
        rwaBalancesTransHistory.setUserId(rwaInstSpvProductAsset.getUserId());
        rwaBalancesTransHistory.setCurrency(rwaInstSpvProductAsset.getCurrency());
        rwaBalancesTransHistory.setType("unforzen");
        rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
        rwaBalancesTransHistory.setChangeAmt(lastAmount);
        rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().add(lastAmount));
        rwaBalancesTransHistory.setState("success");
        rwaBalancesTransHistory.setTransDesc("申请解冻");
        rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
    }

    @Override
    public void unDividendFrozenBal(RwaInstSpvProductDividend rwaInstSpvProductDividend) throws BusinessException {
        //执行失败 分红解冻 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductDividend.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductDividend.getDividendCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesDB== null) {
            log.error("RWA用户资产不存在");
            throw new BusinessException(CommonEnums.ERROR_RWA_USER_BALANCE_NOT_FOUND);
        }
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getAvailBal().add(rwaInstSpvProductDividend.getDividendAmount()));
        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(rwaInstSpvProductDividend.getDividendAmount()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);

        //
        RwaBalancesTransHistory rwaBalancesTransHistory = new RwaBalancesTransHistory();
        rwaBalancesTransHistory.setId(SerialnoUtils.buildPrimaryKey());
        rwaBalancesTransHistory.setUserId(rwaInstSpvProductDividend.getUserId());
        rwaBalancesTransHistory.setCurrency(rwaInstSpvProductDividend.getDividendCurrency());
        rwaBalancesTransHistory.setType("unDividend");
        rwaBalancesTransHistory.setBeforeBal(rwaBalancesDB.getAvailBal());
        rwaBalancesTransHistory.setChangeAmt(rwaInstSpvProductDividend.getDividendAmount());
        rwaBalancesTransHistory.setAfterBal(rwaBalancesDB.getAvailBal().add(rwaInstSpvProductDividend.getDividendAmount()));
        rwaBalancesTransHistory.setState("success");
        rwaBalancesTransHistory.setTransDesc("分红退回");
        rwaBalancesTransHistory.setCreateTime(System.currentTimeMillis());
        rwaBalancesTransHistoryService.insert(rwaBalancesTransHistory);
    }

}
