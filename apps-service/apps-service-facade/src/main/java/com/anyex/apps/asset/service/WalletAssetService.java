/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericService;

/**
 * 钱包资产表 服务接口
 * <p>File：WalletAssetService.java </p>
 * <p>Title: WalletAssetService </p>
 * <p>Description:WalletAssetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface WalletAssetService extends GenericService<WalletAsset>
{
    /**
     * 根据账户ID与币种快速查找一条钱包资产记录
     *
     * @param accountId 账户ID
     * @param currency 币种
     * @return
     */
    WalletAsset findByAccountIdAndCurrency(Long accountId, String currency);
}
