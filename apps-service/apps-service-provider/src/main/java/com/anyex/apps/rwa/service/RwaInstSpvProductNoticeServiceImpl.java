/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.rwa.entity.RwaInstSpvProductNotice;
import com.anyex.apps.rwa.mapper.RwaInstSpvProductNoticeMapper;

/**
 * RWA机构SPV产品公告 服务实现类
 * <p>File：RwaInstSpvProductNoticeServiceImpl.java </p>
 * <p>Title: RwaInstSpvProductNoticeServiceImpl </p>
 * <p>Description:RwaInstSpvProductNoticeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RwaInstSpvProductNoticeServiceImpl extends GenericServiceImpl<RwaInstSpvProductNotice> implements RwaInstSpvProductNoticeService
{
    protected RwaInstSpvProductNoticeMapper rwaInstSpvProductNoticeMapper;

    @Autowired(required = false)
    public RwaInstSpvProductNoticeServiceImpl(RwaInstSpvProductNoticeMapper rwaInstSpvProductNoticeMapper)
    {
        super(rwaInstSpvProductNoticeMapper);
        this.rwaInstSpvProductNoticeMapper = rwaInstSpvProductNoticeMapper;
    }
}
