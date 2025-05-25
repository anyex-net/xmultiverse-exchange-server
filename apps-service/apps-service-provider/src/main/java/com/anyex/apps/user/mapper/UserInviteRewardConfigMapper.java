/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.UserInviteRewardConfig;

/**
 * 用户邀请返佣奖励配置 持久层接口
 * <p>File：UserInviteRewardConfigMapper.java </p>
 * <p>Title: UserInviteRewardConfigMapper </p>
 * <p>Description:UserInviteRewardConfigMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserInviteRewardConfigMapper extends GenericMapper<UserInviteRewardConfig>
{

}
