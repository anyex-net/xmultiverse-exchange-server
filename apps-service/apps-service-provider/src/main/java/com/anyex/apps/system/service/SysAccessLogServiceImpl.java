/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.system.mapper.SysAccessLogMapper;
import com.anyex.apps.system.entity.SysAccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AccessLog 服务实现类
 * <p>File：AccessLogServiceImpl.java </p>
 * <p>Title: AccessLogServiceImpl </p>
 * <p>Description:AccessLogServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysAccessLogServiceImpl extends GenericServiceImpl<SysAccessLog> implements SysAccessLogService
{
    protected SysAccessLogMapper accessLogMapper;

    @Autowired(required = false)
    public SysAccessLogServiceImpl(SysAccessLogMapper accessLogMapper)
    {
        super(accessLogMapper);
        this.accessLogMapper = accessLogMapper;
    }
}
