/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysFrontRoleRes;
import org.apache.ibatis.annotations.Mapper;

/**
 * 前端角色权限信息表 持久层接口
 * <p>File：FrontRoleResMapper.java </p>
 * <p>Title: FrontRoleResMapper </p>
 * <p>Description:FrontRoleResMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysFrontRoleResMapper extends GenericMapper<SysFrontRoleRes>
{
    /**
     * 根据角色编号删除权限 (手机端)
     * @param roleId
     * @return
     */
    int removeByRoleId(Long roleId);
}
