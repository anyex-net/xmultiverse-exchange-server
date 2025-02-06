/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.exception.BusinessException;

/**
 * 钱包资产打赏礼物记录 服务接口
 * <p>File：WalletAssetTipGiftService.java </p>
 * <p>Title: WalletAssetTipGiftService </p>
 * <p>Description:WalletAssetTipGiftService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface WalletAssetTipGiftService extends GenericService<WalletAssetTipGift>
{
    void sendGift(WalletAssetTipGift req) throws BusinessException;

    void getGift(WalletAssetTipGift req) throws BusinessException;

}
