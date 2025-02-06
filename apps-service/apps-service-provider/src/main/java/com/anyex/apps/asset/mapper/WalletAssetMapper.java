/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.asset.entity.WalletAsset;

/**
 * 钱包资产表 持久层接口
 * <p>File：WalletAssetMapper.java </p>
 * <p>Title: WalletAssetMapper </p>
 * <p>Description:WalletAssetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface WalletAssetMapper extends GenericMapper<WalletAsset>
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
