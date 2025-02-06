/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.asset.entity.WalletAssetAdjust;
import com.anyex.apps.asset.mapper.WalletAssetAdjustMapper;

/**
 * 钱包资产调整记录表 服务实现类
 * <p>File：WalletAssetAdjustServiceImpl.java </p>
 * <p>Title: WalletAssetAdjustServiceImpl </p>
 * <p>Description:WalletAssetAdjustServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WalletAssetAdjustServiceImpl extends GenericServiceImpl<WalletAssetAdjust> implements WalletAssetAdjustService
{
    protected WalletAssetAdjustMapper walletAssetAdjustMapper;

    @Autowired(required = false)
    public WalletAssetAdjustServiceImpl(WalletAssetAdjustMapper walletAssetAdjustMapper)
    {
        super(walletAssetAdjustMapper);
        this.walletAssetAdjustMapper = walletAssetAdjustMapper;
    }
}
