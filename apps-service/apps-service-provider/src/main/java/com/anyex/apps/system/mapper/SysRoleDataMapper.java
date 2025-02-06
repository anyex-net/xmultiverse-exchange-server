/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysRoleData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * RoleData 持久层接口
 * <p>File：RoleDataMapper.java </p>
 * <p>Title: RoleDataMapper </p>
 * <p>Description:RoleDataMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysRoleDataMapper extends GenericMapper<SysRoleData>
{
    /**
     * 根据角色编号删除权限
     * @param roleId
     * @return
     */
    int removeByRoleId(Long roleId);

    /**
     * 根据角色ID取角色数据
     * @param roleId
     * @return
     */
    List<SysRoleData> findByRoleId(Long roleId);
}
