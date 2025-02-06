/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.common.entity.SysAppDevice;

/**
 * AppDevice 服务接口
 * <p>File：AppDeviceService.java </p>
 * <p>Title: AppDeviceService </p>
 * <p>Description:AppDeviceService </p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysAppDeviceService extends GenericService<SysAppDevice>
{

    /**
     * 记录登录设备
     * @param appDevice
     */
    void addAppDevice(SysAppDevice appDevice);
}
