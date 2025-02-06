/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysRoleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色信息表 持久层接口
 * <p>File：RoleInfoDao.java </p>
 * <p>Title: RoleInfoDao </p>
 * <p>Description:RoleInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysRoleInfoMapper extends GenericMapper<SysRoleInfo>
{
    /**
     * 根据用户ID取角色
     * @param userId
     * @return
     */
    List<SysRoleInfo> findByUserId(Long userId);

    /**
     * 根据角色ID取角色
     * @param roleId
     * @return
     */
    SysRoleInfo findByRoleId(Long roleId);
}
