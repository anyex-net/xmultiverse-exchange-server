/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysAppDevice;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppDevice 持久层接口
 * <p>File：AppDeviceMapper.java </p>
 * <p>Title: AppDeviceMapper </p>
 * <p>Description:AppDeviceMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysAppDeviceMapper extends GenericMapper<SysAppDevice>
{

}
