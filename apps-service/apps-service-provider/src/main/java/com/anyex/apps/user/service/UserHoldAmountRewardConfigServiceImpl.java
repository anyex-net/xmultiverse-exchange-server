/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.UserHoldAmountRewardConfig;
import com.anyex.apps.user.mapper.UserHoldAmountRewardConfigMapper;

/**
 * 用户持有数量奖励配置 服务实现类
 * <p>File：UserHoldAmountRewardConfigServiceImpl.java </p>
 * <p>Title: UserHoldAmountRewardConfigServiceImpl </p>
 * <p>Description:UserHoldAmountRewardConfigServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserHoldAmountRewardConfigServiceImpl extends GenericServiceImpl<UserHoldAmountRewardConfig> implements UserHoldAmountRewardConfigService
{
    protected UserHoldAmountRewardConfigMapper userHoldAmountRewardConfigMapper;

    @Autowired(required = false)
    public UserHoldAmountRewardConfigServiceImpl(UserHoldAmountRewardConfigMapper userHoldAmountRewardConfigMapper)
    {
        super(userHoldAmountRewardConfigMapper);
        this.userHoldAmountRewardConfigMapper = userHoldAmountRewardConfigMapper;
    }
}
