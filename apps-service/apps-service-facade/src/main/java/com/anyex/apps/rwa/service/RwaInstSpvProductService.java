/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;

import java.util.List;

/**
 * RWA机构SPV产品 服务接口
 * <p>File：RwaInstSpvProductService.java </p>
 * <p>Title: RwaInstSpvProductService </p>
 * <p>Description:RwaInstSpvProductService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RwaInstSpvProductService extends GenericService<RwaInstSpvProduct>
{
    PaginateResult<RwaInstSpvProduct> findListByState(Pagination pagin, RwaInstSpvProduct rwaInstSpvProduct);
}
