/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.UserInstrumentsFavorite;
import com.anyex.apps.base.mapper.UserInstrumentsFavoriteMapper;

/**
 * 用户交易产品收藏 服务实现类
 * <p>File：UserInstrumentsFavoriteServiceImpl.java </p>
 * <p>Title: UserInstrumentsFavoriteServiceImpl </p>
 * <p>Description:UserInstrumentsFavoriteServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInstrumentsFavoriteServiceImpl extends GenericServiceImpl<UserInstrumentsFavorite> implements UserInstrumentsFavoriteService
{
    protected UserInstrumentsFavoriteMapper userInstrumentsFavoriteMapper;

    @Autowired(required = false)
    public UserInstrumentsFavoriteServiceImpl(UserInstrumentsFavoriteMapper userInstrumentsFavoriteMapper)
    {
        super(userInstrumentsFavoriteMapper);
        this.userInstrumentsFavoriteMapper = userInstrumentsFavoriteMapper;
    }
}
