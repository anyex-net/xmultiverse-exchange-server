/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.common.entity.SysParameter;

/**
 * 参数配置 服务接口
 * <p>File：ParameterService.java </p>
 * <p>Title: ParameterService </p>
 * <p>Description:ParameterService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysParameterService extends GenericService<SysParameter>
{
    SysParameter getParameterByName(String parameterName);
}
