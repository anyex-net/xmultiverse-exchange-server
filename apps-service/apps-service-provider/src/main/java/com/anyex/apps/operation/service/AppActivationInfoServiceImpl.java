/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.operation.service;

import com.anyex.apps.operation.mapper.AppActivationInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.operation.entity.AppActivationInfo;

/**
 * APP激活信息表 服务实现类
 * <p>File：AppActivationInfoServiceImpl.java </p>
 * <p>Title: AppActivationInfoServiceImpl </p>
 * <p>Description:AppActivationInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AppActivationInfoServiceImpl extends GenericServiceImpl<AppActivationInfo> implements AppActivationInfoService
{
    protected AppActivationInfoMapper appActivationInfoMapper;

    @Autowired(required = false)
    public AppActivationInfoServiceImpl(AppActivationInfoMapper appActivationInfoMapper)
    {
        super(appActivationInfoMapper);
        this.appActivationInfoMapper = appActivationInfoMapper;
    }
}
