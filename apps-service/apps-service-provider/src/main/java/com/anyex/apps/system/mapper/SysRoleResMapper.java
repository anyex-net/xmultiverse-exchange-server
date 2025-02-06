/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysRoleRes;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限信息表 持久层接口
 * <p>File：RoleResDao.java </p>
 * <p>Title: RoleResDao </p>
 * <p>Description:RoleResDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysRoleResMapper extends GenericMapper<SysRoleRes>
{
    /**
     * 根据角色编号删除权限
     * @param roleId
     * @return
     */
    int removeByRoleId(Long roleId);
}
