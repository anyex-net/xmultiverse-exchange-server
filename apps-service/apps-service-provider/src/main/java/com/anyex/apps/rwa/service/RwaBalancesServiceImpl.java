/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductMapper;
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
    public void purchaseFrozenBal(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        //申购者资金冻结
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
//        rwaBalances1.setFrozenBal(purchaseBalance.add(rwaBalances1.getFrozenBal()));
        rwaBalances1.setBalance(rwaBalances1.getBalance().subtract(purchaseBalance));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
        //募集者资金冻结,冻结增加，总余额相应增加
        RwaInstSpvProduct rwaInstSpvProduct = rwaInstSpvProductMapper.selectByPrimaryKey(rwaInstSpvProductPurchase.getInstSpvProductId());
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalancesRaise = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalancesRaise == null) {
            throw new BusinessException("User balance not found.");
        }
        rwaBalancesRaise.setFrozenBal(rwaBalancesRaise.getFrozenBal().add(purchaseBalance));
        rwaBalancesRaise.setBalance(rwaBalancesRaise.getBalance().add(purchaseBalance));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalancesRaise);
    }

    @Transactional
    public void raiseMarginFrozenBal(RwaInstSpvProduct rwaInstSpvProduct) throws BusinessException{
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProduct.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProduct.getRaiseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOneForUpdate(rwaBalances);
        if (rwaBalances1 == null) {
            throw new BusinessException("User balance not found.");
        }
        BigDecimal raiseMargin = rwaInstSpvProduct.getRaiseMarginRatio().multiply(rwaInstSpvProduct.getRaiseAmount());
        if (raiseMargin.compareTo(rwaBalances1.getAvailBal()) > 0) {
            throw new BusinessException("Insufficient available balance.");
        }
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().subtract(raiseMargin));
        rwaBalances1.setFrozenBal(raiseMargin.add(rwaBalances1.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }
}
