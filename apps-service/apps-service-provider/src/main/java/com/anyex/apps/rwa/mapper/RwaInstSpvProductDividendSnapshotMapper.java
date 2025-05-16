/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.rwa.model.RwaDividendSnapshotInfoResultModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividendSnapshot;

import java.util.List;

/**
 * RWA机构SPV产品投资者分红快照 持久层接口
 * <p>File：RwaInstSpvProductDividendSnapshotMapper.java </p>
 * <p>Title: RwaInstSpvProductDividendSnapshotMapper </p>
 * <p>Description:RwaInstSpvProductDividendSnapshotMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaInstSpvProductDividendSnapshotMapper extends GenericMapper<RwaInstSpvProductDividendSnapshot>
{
    List<RwaDividendSnapshotInfoResultModel> selectGroupByUserId(RwaInstSpvProductDividendSnapshot rwaInstSpvProductDividendSnapshot);
}
