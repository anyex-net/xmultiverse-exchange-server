/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividend;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * RWA机构SPV产品分红记录 持久层接口
 * <p>File：RwaInstSpvProductDividendMapper.java </p>
 * <p>Title: RwaInstSpvProductDividendMapper </p>
 * <p>Description:RwaInstSpvProductDividendMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaInstSpvProductDividendMapper extends GenericMapper<RwaInstSpvProductDividend>
{
    BigDecimal selectDividendAmount(@Param("instSpvProductId") Long instSpvProductId);

}
