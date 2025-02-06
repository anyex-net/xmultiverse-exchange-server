/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色权限表 持久层接口
 * <p>File：UserRoleDao.java </p>
 * <p>Title: UserRoleDao </p>
 * <p>Description:UserRoleDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysUserRoleMapper extends GenericMapper<SysUserRole>
{
    /**
     * 根据用户ID删除
     * @param userId
     */
    void removeByUser(Long userId);

//    /**
//     * 根据角色Id获取用户ID
//     * @param roleIds
//     * @return
//     */
//    List<UserRole> getUserListByRoleId(String roleIds);
}
