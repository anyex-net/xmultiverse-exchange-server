/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaInstSpvProductPurchase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * RWA机构SPV产品申购记录 持久层接口
 * <p>File：RwaInstSpvProductPurchaseMapper.java </p>
 * <p>Title: RwaInstSpvProductPurchaseMapper </p>
 * <p>Description:RwaInstSpvProductPurchaseMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaInstSpvProductPurchaseMapper extends GenericMapper<RwaInstSpvProductPurchase>
{
    List<RwaInstSpvProductPurchase> findListByRaiseUserId(@Param("query") RwaInstSpvProductPurchase rwaInstSpvProductPurchase,@Param("raiseUserId") Long raiseUserId);

    BigDecimal findTotalPurchaseAmountByUserIdAndProductId(RwaInstSpvProductPurchase rwaInstSpvProductPurchase);
}
