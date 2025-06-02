/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.user.model.InviteRebateSummaryModel;
import com.anyex.apps.user.model.UserInviteRebateModel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserInvite;
import com.anyex.apps.user.mapper.UserInviteMapper;

import java.util.List;

/**
 * 用户邀请关系 服务实现类
 * <p>File：UserInviteServiceImpl.java </p>
 * <p>Title: UserInviteServiceImpl </p>
 * <p>Description:UserInviteServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInviteServiceImpl extends GenericServiceImpl<UserInvite> implements UserInviteService
{
    protected UserInviteMapper userInviteMapper;

    @Autowired(required = false)
    public UserInviteServiceImpl(UserInviteMapper userInviteMapper)
    {
        super(userInviteMapper);
        this.userInviteMapper = userInviteMapper;
    }

    @Override
    public InviteRebateSummaryModel selectInviteRebateSummary(Long inviterId) throws BusinessException
    {
        return userInviteMapper.selectInviteRebateSummary(inviterId);
    }

    @Override
    public PaginateResult<UserInviteRebateModel> listInviteeRebatesByInviterId(Pagination pagin, Long inviterId) throws BusinessException{
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<UserInviteRebateModel> pageInfo = PageInfo.of(userInviteMapper.listInviteeRebatesByInviterId(inviterId));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<UserInviteRebateModel> result = pageInfo.getList();
        return new PaginateResult<>(pagin, result);
    }
}
