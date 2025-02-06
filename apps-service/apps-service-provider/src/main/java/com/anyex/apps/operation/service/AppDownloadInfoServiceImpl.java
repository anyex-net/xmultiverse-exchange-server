/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.operation.mapper.AppDownloadInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.operation.entity.AppDownloadInfo;

/**
 * APP下载信息表 服务实现类
 * <p>File：AppDownloadInfoServiceImpl.java </p>
 * <p>Title: AppDownloadInfoServiceImpl </p>
 * <p>Description:AppDownloadInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AppDownloadInfoServiceImpl extends GenericServiceImpl<AppDownloadInfo> implements AppDownloadInfoService
{
    protected AppDownloadInfoMapper appDownloadInfoMapper;

    @Autowired(required = false)
    public AppDownloadInfoServiceImpl(AppDownloadInfoMapper appDownloadInfoMapper)
    {
        super(appDownloadInfoMapper);
        this.appDownloadInfoMapper = appDownloadInfoMapper;
    }
}
