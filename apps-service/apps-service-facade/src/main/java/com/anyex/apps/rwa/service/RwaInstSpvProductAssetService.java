/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品资产信息 服务接口
 * <p>File：RwaInstSpvProductAssetService.java </p>
 * <p>Title: RwaInstSpvProductAssetService </p>
 * <p>Description:RwaInstSpvProductAssetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RwaInstSpvProductAssetService extends GenericService<RwaInstSpvProductAsset>
{
    BigDecimal selectAmountSum(Long instSpvProductId);
}
