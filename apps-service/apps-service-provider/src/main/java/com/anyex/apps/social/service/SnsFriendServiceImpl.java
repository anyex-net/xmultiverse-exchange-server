/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.annotation.SlaveDataSource;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.AccountInfoModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.social.entity.SnsFriend;
import com.anyex.apps.social.mapper.SnsFriendMapper;

/**
 * 社交好友 服务实现类
 * <p>File：SnsFriendServiceImpl.java </p>
 * <p>Title: SnsFriendServiceImpl </p>
 * <p>Description:SnsFriendServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SnsFriendServiceImpl extends GenericServiceImpl<SnsFriend> implements SnsFriendService
{
    protected SnsFriendMapper snsFriendMapper;

    @Autowired(required = false)
    public SnsFriendServiceImpl(SnsFriendMapper snsFriendMapper)
    {
        super(snsFriendMapper);
        this.snsFriendMapper = snsFriendMapper;
    }

    @Override
    public Integer getFriendsCntByUserId(String userId) {
        return snsFriendMapper.getFriendsCntByUserId(userId);
    }

    @Override
    @SlaveDataSource()
    public PaginateResult<AccountInfoModel> myFriends(Pagination pagin, SnsFriend entity) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountInfoModel> pageInfo = PageInfo.of(snsFriendMapper.myFriends(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
