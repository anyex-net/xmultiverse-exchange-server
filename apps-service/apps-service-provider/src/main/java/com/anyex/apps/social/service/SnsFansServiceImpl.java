/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.AccountInfoModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsFans;
import com.anyex.apps.social.mapper.SnsFansMapper;

/**
 * 社交粉丝(关注我的) 服务实现类
 * <p>File：SnsFansServiceImpl.java </p>
 * <p>Title: SnsFansServiceImpl </p>
 * <p>Description:SnsFansServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsFansServiceImpl extends GenericServiceImpl<SnsFans> implements SnsFansService
{
    protected SnsFansMapper snsFansMapper;

    @Autowired(required = false)
    public SnsFansServiceImpl(SnsFansMapper snsFansMapper)
    {
        super(snsFansMapper);
        this.snsFansMapper = snsFansMapper;
    }

    @Override
    public Integer cntFans(String userId) {
        return snsFansMapper.cntFans(userId);
    }

    @Override
    public PaginateResult<AccountInfoModel> listFans(Pagination pagin, String userId) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountInfoModel> pageInfo = PageInfo.of(snsFansMapper.listFans(userId));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
