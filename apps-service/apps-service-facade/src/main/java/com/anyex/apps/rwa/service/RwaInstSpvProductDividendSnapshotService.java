/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividendSnapshot;
import com.anyex.apps.rwa.model.RwaDividendSnapshotInfoResultModel;

/**
 * RWA机构SPV产品投资者分红快照 服务接口
 * <p>File：RwaInstSpvProductDividendSnapshotService.java </p>
 * <p>Title: RwaInstSpvProductDividendSnapshotService </p>
 * <p>Description:RwaInstSpvProductDividendSnapshotService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RwaInstSpvProductDividendSnapshotService extends GenericService<RwaInstSpvProductDividendSnapshot>
{
    PaginateResult<RwaDividendSnapshotInfoResultModel> selectGroupByUserId(Pagination pagin, RwaInstSpvProductDividendSnapshot rwaInstSpvProductDividendSnapshot);
}
