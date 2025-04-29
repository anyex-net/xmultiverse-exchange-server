/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;

/**
 * RWA机构SPV产品申购记录 服务接口
 * <p>File：RwaInstSpvProductPurchaseService.java </p>
 * <p>Title: RwaInstSpvProductPurchaseService </p>
 * <p>Description:RwaInstSpvProductPurchaseService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RwaInstSpvProductPurchaseService extends GenericService<RwaInstSpvProductPurchase>
{
    PaginateResult<RwaInstSpvProductPurchase> findListByRaiseUserId(Pagination pagin, RwaInstSpvProductPurchase rwaInstSpvProductPurchase, Long raiseUserId);
}
