/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.mapper.RwaBalancesMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
    public RwaBalancesServiceImpl(RwaBalancesMapper rwaBalancesMapper, RwaInstSpvProductMapper rwaInstSpvProductMapper)
    {
        super(rwaBalancesMapper);
        this.rwaBalancesMapper = rwaBalancesMapper;
        this.rwaInstSpvProductMapper = rwaInstSpvProductMapper;
    }

    @Transactional
    public void purchaseFrozenBalCheckBefore(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        //申购前 申购者 总余额不变，冻结增加，可用余额减少
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        if (purchaseBalance.compareTo(rwaBalances1.getAvailBal()) > 0) {
            throw new BusinessException("Insufficient available balance.");
        }
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().subtract(purchaseBalance));
        rwaBalances1.setFrozenBal(purchaseBalance.add(rwaBalances1.getFrozenBal()));
//        rwaBalances1.setBalance(rwaBalances1.getBalance().subtract(purchaseBalance));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }

    @Transactional
    public void purchaseFrozenBalUncheck(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        //申购拒绝 申购者 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().add(purchaseBalance));
        rwaBalances1.setFrozenBal(rwaBalances1.getFrozenBal().subtract(purchaseBalance));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }

    @Transactional
    public void purchaseFrozenBalCheckAfter(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        //申购审核后 申购者 总余额减少，冻结减少，可用余额不变
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalancesDB = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (null == rwaBalancesDB) {
            log.error("User balance not found.");
            throw new BusinessException("User balance not found.");
        }
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        rwaBalancesDB.setBalance(rwaBalancesDB.getBalance().subtract(purchaseBalance));
        rwaBalancesDB.setFrozenBal(rwaBalancesDB.getFrozenBal().subtract(purchaseBalance));
        rwaBalancesDB.setAvailBal(rwaBalancesDB.getBalance().subtract(rwaBalancesDB.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesDB);
        //申购审核后 募集者 总余额增加，冻结增加，可用余额不变
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductMapper.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
        if (null == rwaInstSpvProduct){
            log.error("InstSpvProduct not found.");
            throw new BusinessException("InstSpvProduct not found.");
        }
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalancesRaise = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (null == rwaBalancesRaise) {
            log.error("User balance not found.");
            throw new BusinessException("User balance not found.");
        }
        rwaBalancesRaise.setBalance(rwaBalancesRaise.getBalance().add(purchaseBalance));
        rwaBalancesRaise.setFrozenBal(rwaBalancesRaise.getFrozenBal().add(purchaseBalance));
        rwaBalancesRaise.setAvailBal(rwaBalancesRaise.getBalance().subtract(rwaBalancesRaise.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesRaise);
    }

    @Transactional
    public void raiseMarginFrozenBal(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        //保证金 缴纳 总余额不变，冻结增加，可用余额减少
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMargin();
        if (raiseMargin.compareTo(rwaBalances1.getAvailBal()) > 0) {
            throw new BusinessException("Insufficient available balance.");
        }
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().subtract(raiseMargin));
        rwaBalances1.setFrozenBal(raiseMargin.add(rwaBalances1.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }

    @Transactional
    public void raiseMarginFrozenBalUncheck(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        //保证金审核被拒绝 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMargin();
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().add(raiseMargin));
        rwaBalances1.setFrozenBal(rwaBalances1.getFrozenBal().subtract(raiseMargin));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }

    @Transactional
    public void unFrozenBal(RwaInstSpvProductAsset rwaInstSpvProductAsset) throws BusinessException{
        //申请资产解冻 总余额不变，冻结减少，可用余额增加
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductAsset.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductAsset.getCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal lastAmount = rwaInstSpvProductAsset.getLastAmount();
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().add(lastAmount));
        rwaBalances1.setFrozenBal(rwaBalances1.getFrozenBal().subtract(lastAmount));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }
}
