/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;

/**
 * RWA账户余额 服务接口
 * <p>File：RwaBalancesService.java </p>
 * <p>Title: RwaBalancesService </p>
 * <p>Description:RwaBalancesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RwaBalancesService extends GenericService<RwaBalances>
{
    /**
     * 申购冻结余额
     * @param rwaInstSpvProductPurchase
     */
    void purchaseFrozenBal(RwaInstSpvProductPurchase rwaInstSpvProductPurchase);

    /**
     * 保证金冻结余额
     * @param rwaInstSpvProduct
     */
    void raiseMarginFrozenBal(RwaInstSpvProduct rwaInstSpvProduct);
}
