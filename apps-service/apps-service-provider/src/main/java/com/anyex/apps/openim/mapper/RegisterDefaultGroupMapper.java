/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.openim.entity.RegisterDefaultGroup;

/**
 * 注册默认群 持久层接口
 * <p>File：RegisterDefaultGroupMapper.java </p>
 * <p>Title: RegisterDefaultGroupMapper </p>
 * <p>Description:RegisterDefaultGroupMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RegisterDefaultGroupMapper extends GenericMapper<RegisterDefaultGroup>
{
    RegisterDefaultGroup findByGroupId(String groupId);
}
