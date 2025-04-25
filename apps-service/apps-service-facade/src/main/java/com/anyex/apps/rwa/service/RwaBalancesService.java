/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.rwa.entity.RwaBalances;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;
import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;
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
     * 申购审核前 申购者 资产冻结
     * @param rwaInstSpvProductPurchase
     */
    void purchaseFrozenBalCheckBefore(RwaInstSpvProductPurchase rwaInstSpvProductPurchase);

    /**
     * 申购拒绝 申购者 资产还原
     * @param rwaInstSpvProductPurchase
     */
    void purchaseFrozenBalUncheck(RwaInstSpvProductPurchase rwaInstSpvProductPurchase);

    /**
     * 申购通过 申购者 资产减少 募集者  资产冻结
     * @param rwaInstSpvProductPurchase
     */
    void purchaseFrozenBalCheckAfter(RwaInstSpvProductPurchase rwaInstSpvProductPurchase);

    /**
     * 保证金冻结余额
     * @param rwaInstSpvProduct
     */
    void raiseMarginFrozenBal(RwaInstSpvProduct rwaInstSpvProduct);

    /**
     * 保证金解冻余额
     * @param rwaInstSpvProduct
     */
    void raiseMarginFrozenBalUncheck(RwaInstSpvProduct rwaInstSpvProduct);

    /**
     * 申请解冻 审核通过后
     * @param rwaInstSpvProductAsset
     */
    void unFrozenBal(RwaInstSpvProductAsset rwaInstSpvProductAsset);
}
