/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.bean.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.asset.mapper.WalletAssetFlowsMapper;

/**
 * 钱包资产流水表 服务实现类
 * <p>File：WalletAssetFlowsServiceImpl.java </p>
 * <p>Title: WalletAssetFlowsServiceImpl </p>
 * <p>Description:WalletAssetFlowsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WalletAssetFlowsServiceImpl extends GenericServiceImpl<WalletAssetFlows> implements WalletAssetFlowsService
{
    protected WalletAssetFlowsMapper walletAssetFlowsMapper;

    @Autowired(required = false)
    public WalletAssetFlowsServiceImpl(WalletAssetFlowsMapper walletAssetFlowsMapper)
    {
        super(walletAssetFlowsMapper);
        this.walletAssetFlowsMapper = walletAssetFlowsMapper;
    }
}
