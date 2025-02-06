/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.asset.mapper.WalletAssetMapper;

/**
 * 钱包资产表 服务实现类
 * <p>File：WalletAssetServiceImpl.java </p>
 * <p>Title: WalletAssetServiceImpl </p>
 * <p>Description:WalletAssetServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class WalletAssetServiceImpl extends GenericServiceImpl<WalletAsset> implements WalletAssetService
{
    protected WalletAssetMapper walletAssetMapper;

    @Autowired(required = false)
    public WalletAssetServiceImpl(WalletAssetMapper walletAssetMapper)
    {
        super(walletAssetMapper);
        this.walletAssetMapper = walletAssetMapper;
    }

    @Override
    public WalletAsset findByAccountIdAndCurrency(Long accountId, String currency) throws BusinessException
    {
        return walletAssetMapper.findByAccountIdAndCurrency(accountId, currency);
    }
}
