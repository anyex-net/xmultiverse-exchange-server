/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.openim.entity.RegisterDefaultFriend;

/**
 * 注册默认好友 服务接口
 * <p>File：RegisterDefaultFriendService.java </p>
 * <p>Title: RegisterDefaultFriendService </p>
 * <p>Description:RegisterDefaultFriendService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface RegisterDefaultFriendService extends GenericService<RegisterDefaultFriend>
{

    RegisterDefaultFriend findByUserId(String userId);

}
