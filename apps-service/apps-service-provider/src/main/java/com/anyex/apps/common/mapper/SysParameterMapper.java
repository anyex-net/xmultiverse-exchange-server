/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.common.entity.SysParameter;
import org.apache.ibatis.annotations.Param;

/**
 * 参数配置 持久层接口
 * <p>File：ParameterMapper.java </p>
 * <p>Title: ParameterMapper </p>
 * <p>Description:ParameterMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysParameterMapper extends GenericMapper<SysParameter>
{
    SysParameter getParameterByName(@Param("parameterName") String parameterName);

}
