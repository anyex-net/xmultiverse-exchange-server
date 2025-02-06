/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.openim.entity.RegisterDefaultFriend;
import org.apache.ibatis.annotations.Param;

/**
 * 注册默认好友 持久层接口
 * <p>File：RegisterDefaultFriendMapper.java </p>
 * <p>Title: RegisterDefaultFriendMapper </p>
 * <p>Description:RegisterDefaultFriendMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RegisterDefaultFriendMapper extends GenericMapper<RegisterDefaultFriend>
{
    RegisterDefaultFriend findByUserId(@Param("userId") String userId);
}
