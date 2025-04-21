/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
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
    protected RwaBalancesMapper rwaBalancesMapper;

    @Autowired(required = false)
    public RwaBalancesServiceImpl(RwaBalancesMapper rwaBalancesMapper)
    {
        super(rwaBalancesMapper);
        this.rwaBalancesMapper = rwaBalancesMapper;
    }

    @Transactional
    public void purchaseFrozenBal(RwaInstSpvProductPurchase rwaInstSpvProductPurchase) throws BusinessException{
        RwaBalances rwaBalances = new RwaBalances();
        rwaBalances.setUserId(rwaInstSpvProductPurchase.getUserId());
        rwaBalances.setCurrency(rwaInstSpvProductPurchase.getPurchaseCurrency());
        RwaBalances rwaBalances1 = rwaBalancesMapper.selectOne(rwaBalances);
        BigDecimal purchaseBalance = rwaInstSpvProductPurchase.getPurchaseAmount().multiply(rwaInstSpvProductPurchase.getPurchasePrice());
        if (purchaseBalance.compareTo(rwaBalances1.getAvailBal()) > 0) {
            throw new BusinessException("Insufficient available balance.");
        }
        rwaBalances1.setAvailBal(rwaBalances1.getAvailBal().subtract(purchaseBalance));
        rwaBalances1.setFrozenBal(purchaseBalance.add(rwaBalances1.getFrozenBal()));
        rwaBalancesMapper.updateByPrimaryKeySelective(rwaBalances1);
    }
}
