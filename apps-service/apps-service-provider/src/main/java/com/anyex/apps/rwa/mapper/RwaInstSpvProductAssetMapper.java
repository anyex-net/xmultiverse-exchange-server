/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaInstSpvProductAsset;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品资产信息 持久层接口
 * <p>File：RwaInstSpvProductAssetMapper.java </p>
 * <p>Title: RwaInstSpvProductAssetMapper </p>
 * <p>Description:RwaInstSpvProductAssetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaInstSpvProductAssetMapper extends GenericMapper<RwaInstSpvProductAsset>
{
    BigDecimal selectAmountSum(@Param("instSpvProductId") Long instSpvProductId);

    BigDecimal selectLastAmountSum(@Param("instSpvProductId") Long instSpvProductId);
}
