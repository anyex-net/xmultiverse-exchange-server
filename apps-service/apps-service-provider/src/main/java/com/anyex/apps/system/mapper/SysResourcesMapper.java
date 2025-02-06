/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysResources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源菜单信息表 持久层接口
 * <p>File：ResourcesDao.java </p>
 * <p>Title: ResourcesDao </p>
 * <p>Description:ResourcesDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysResourcesMapper extends GenericMapper<SysResources>
{
    /**
     * 根据角色ID取资源菜单
     * @param roleId
     * @return {@link SysResources}
     */
    List<SysResources> findByRoleId(Long roleId);

    /**
     * 根据用户ID取资源菜单
     * @param userId
     * @return {@link SysResources}
     */
    List<SysResources> findByUserId(Long userId);
    
    /**
     * 根据上线ID取资源
     * @param parentId
     * @return
     */
    List<SysResources> findByParentId(@Param("parentId") Long parentId);
}
