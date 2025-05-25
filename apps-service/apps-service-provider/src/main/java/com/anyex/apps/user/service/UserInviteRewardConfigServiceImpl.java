/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserInviteRewardConfig;
import com.anyex.apps.user.mapper.UserInviteRewardConfigMapper;

/**
 * 用户邀请返佣奖励配置 服务实现类
 * <p>File：UserInviteRewardConfigServiceImpl.java </p>
 * <p>Title: UserInviteRewardConfigServiceImpl </p>
 * <p>Description:UserInviteRewardConfigServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInviteRewardConfigServiceImpl extends GenericServiceImpl<UserInviteRewardConfig> implements UserInviteRewardConfigService
{
    protected UserInviteRewardConfigMapper userInviteRewardConfigMapper;

    @Autowired(required = false)
    public UserInviteRewardConfigServiceImpl(UserInviteRewardConfigMapper userInviteRewardConfigMapper)
    {
        super(userInviteRewardConfigMapper);
        this.userInviteRewardConfigMapper = userInviteRewardConfigMapper;
    }
}
