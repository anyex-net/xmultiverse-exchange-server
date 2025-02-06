/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表 持久层接口
 * <p>File：UserInfoDao.java </p>
 * <p>Title: UserInfoDao </p>
 * <p>Description:UserInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysUserInfoMapper extends GenericMapper<SysUserInfo>
{
    /**
     * 根据用户名找用户
     * @param userName
     * @return
     */
    SysUserInfo findByUserName(String userName);

    /**
     * 根据浙政钉OpenId找用户
     *
     * @param ZZDOpenId 浙政钉OpenId
     * @return UserInfo
     */
    SysUserInfo findByZZDOpenId(@Param("ZZDOpenId") String ZZDOpenId);
}
