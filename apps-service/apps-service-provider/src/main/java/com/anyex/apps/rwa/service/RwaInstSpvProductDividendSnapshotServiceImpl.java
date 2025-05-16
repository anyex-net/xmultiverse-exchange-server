/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.rwa.model.RwaDividendSnapshotInfoResultModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductDividendSnapshot;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductDividendSnapshotMapper;

import java.util.List;

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

    @Override
    public PaginateResult<RwaDividendSnapshotInfoResultModel> selectGroupByUserId(Pagination pagin, RwaInstSpvProductDividendSnapshot rwaInstSpvProductDividendSnapshot) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<RwaDividendSnapshotInfoResultModel> pageInfo = PageInfo.of(rwaInstSpvProductDividendSnapshotMapper.selectGroupByUserId(rwaInstSpvProductDividendSnapshot));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<RwaDividendSnapshotInfoResultModel> result = pageInfo.getList();
        return new PaginateResult<>(pagin, result);
    }
}
