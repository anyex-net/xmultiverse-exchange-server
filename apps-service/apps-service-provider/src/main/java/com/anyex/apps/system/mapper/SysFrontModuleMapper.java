/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysFrontModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 前端功能模块表 持久层接口
 * <p>File：FrontModuleMapper.java </p>
 * <p>Title: FrontModuleMapper </p>
 * <p>Description:FrontModuleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysFrontModuleMapper extends GenericMapper<SysFrontModule>
{
    List<SysFrontModule> findByRoleId(Long roleId);
}
