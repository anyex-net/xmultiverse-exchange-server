/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividendSnapshot;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductDividendSnapshotMapper;

/**
 * RWA机构SPV产品投资者分红快照 服务实现类
 * <p>File：RwaInstSpvProductDividendSnapshotServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductDividendSnapshotServiceImpl </p>
 * <p>Description:RwaInstSpvProductDividendSnapshotServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductDividendSnapshotServiceImpl extends GenericServiceImpl<RwaInstSpvProductDividendSnapshot> implements RwaInstSpvProductDividendSnapshotService
{
    protected RwaInstSpvProductDividendSnapshotMapper rwaInstSpvProductDividendSnapshotMapper;

    @Autowired(required = false)
    public RwaInstSpvProductDividendSnapshotServiceImpl(RwaInstSpvProductDividendSnapshotMapper rwaInstSpvProductDividendSnapshotMapper)
    {
        super(rwaInstSpvProductDividendSnapshotMapper);
        this.rwaInstSpvProductDividendSnapshotMapper = rwaInstSpvProductDividendSnapshotMapper;
    }
}
