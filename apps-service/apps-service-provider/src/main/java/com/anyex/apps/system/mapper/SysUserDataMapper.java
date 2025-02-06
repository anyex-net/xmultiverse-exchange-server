/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysUserData;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserData 持久层接口
 * <p>File：UserDataMapper.java </p>
 * <p>Title: UserDataMapper </p>
 * <p>Description:UserDataMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysUserDataMapper extends GenericMapper<SysUserData>
{
    /**
     * 根据用户编号删除权限
     * @param userId
     * @return
     */
    int removeByUserId(Long userId);
}
