/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysParameter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.common.mapper.SysParameterMapper;

/**
 * 参数配置 服务实现类
 * <p>File：ParameterServiceImpl.java </p>
 * <p>Title: ParameterServiceImpl </p>
 * <p>Description:ParameterServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysParameterServiceImpl extends GenericServiceImpl<SysParameter> implements SysParameterService
{
    protected SysParameterMapper parameterMapper;

    @Autowired(required = false)
    public SysParameterServiceImpl(SysParameterMapper parameterMapper)
    {
        super(parameterMapper);
        this.parameterMapper = parameterMapper;
    }

    @Override
    public SysParameter getParameterByName(String parameterName) {
        return parameterMapper.getParameterByName(parameterName);
    }
}
