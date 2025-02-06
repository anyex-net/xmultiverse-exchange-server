/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.Attribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户属性表 持久层接口
 * <p>File：AttributeMapper.java </p>
 * <p>Title: AttributeMapper </p>
 * <p>Description:AttributeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AttributeMapper extends GenericMapper<Attribute>
{
    Attribute findByUserId(@Param("userId") String userId);

    List<Attribute> findByUserIds(@Param("userIds") List<String> userIds);
}
