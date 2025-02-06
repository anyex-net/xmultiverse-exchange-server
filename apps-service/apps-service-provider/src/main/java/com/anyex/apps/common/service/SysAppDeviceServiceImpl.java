/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysAppDevice;
import com.anyex.apps.common.mapper.SysAppDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AppDevice 服务实现类
 * <p>File：AppDeviceServiceImpl.java </p>
 * <p>Title: AppDeviceServiceImpl </p>
 * <p>Description:AppDeviceServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysAppDeviceServiceImpl extends GenericServiceImpl<SysAppDevice> implements SysAppDeviceService
{
    protected SysAppDeviceMapper appDeviceMapper;

    @Autowired(required = false)
    public SysAppDeviceServiceImpl(SysAppDeviceMapper appDeviceMapper)
    {
        super(appDeviceMapper);
        this.appDeviceMapper = appDeviceMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addAppDevice(SysAppDevice appDevice)
    {
        SysAppDevice appDeviceDB = new SysAppDevice();
        List<SysAppDevice> accountDeviceList = appDeviceMapper.findList(appDevice);
        if (accountDeviceList.size() != 0)
        {
            appDeviceDB = accountDeviceList.get(0);
        } else {
            appDeviceDB.setCreateDate(System.currentTimeMillis()); //
        }
        appDeviceDB.setAccountId(appDevice.getAccountId());
        appDeviceDB.setAppVersion(appDevice.getAppVersion());
        appDeviceDB.setBuildVersion(appDevice.getBuildVersion());
        appDeviceDB.setDeviceName(appDevice.getDeviceName());
        appDeviceDB.setDeviceNumber(appDevice.getDeviceNumber());
        appDeviceDB.setDeviceType(appDevice.getDeviceType());
        appDeviceDB.setIpAddress(appDevice.getIpAddress());
        appDeviceDB.setLastLoginDate(System.currentTimeMillis()); //
        //
        this.save(appDeviceDB);
    }
}
