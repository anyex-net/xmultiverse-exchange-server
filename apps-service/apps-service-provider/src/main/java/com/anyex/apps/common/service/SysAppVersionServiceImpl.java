/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysAppVersion;
import com.anyex.apps.common.mapper.SysAppVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手机端版本表 服务实现类
 * <p>File：AppVersionServiceImpl.java </p>
 * <p>Title: AppVersionServiceImpl </p>
 * <p>Description:AppVersionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author yukai
 * @version 1.0
 */
@Service
public class SysAppVersionServiceImpl extends GenericServiceImpl<SysAppVersion> implements SysAppVersionService
{
    protected SysAppVersionMapper appVersionMapper;
    
    @Autowired
    public SysAppVersionServiceImpl(SysAppVersionMapper appVersionMapper)
    {
        super(appVersionMapper);
        this.appVersionMapper = appVersionMapper;
    }
}
